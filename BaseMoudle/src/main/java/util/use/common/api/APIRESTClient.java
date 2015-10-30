package util.use.common.api;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import util.use.common.model.APIError;

/**
 * Created by Lyson on 15/8/27.
 */
public class APIRESTClient {
    private static final String LogTag = APIRESTClient.class.getSimpleName();
    private static APIRESTClient mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;
    private GetDelegate mGetDelegate;
    private PostDelegate mPostDelegate;


    private APIRESTClient() {
        mOkHttpClient = new OkHttpClient();
        //connect timeout
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        //read timeout
        mOkHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        mHandler = new Handler(Looper.myLooper());
        mGson = new Gson();
        mGetDelegate = new GetDelegate();
        mPostDelegate = new PostDelegate();
    }

    /**
     * 单例
     *
     * @return
     */
    public static APIRESTClient getInstance() {
        if (mInstance == null) {
            synchronized (APIRESTClient.class) {
                if (mInstance == null) {
                    mInstance = new APIRESTClient();
                }
            }
        }
        return mInstance;
    }

    /**
     * 异步get请求，不带参数
     *
     * @param url
     * @param callback
     */
    public static <T>  void getAsyn(String url, APICallback<T> callback) {
        getAsynMap(url, null, callback);
    }

    /**
     * 异步get请求，带Map对象的参数
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void getAsynMap(String url, Map<String, String> params, APICallback callback) {
        getInstance().getGetDelegate().getAsyn(url, params, callback);
    }

    /**
     * 异步get请求，带数组对象参数
     *
     * @param url
     * @param apiParams
     * @param callback
     */
    public static void getAsynArray(String url, APIParam[] apiParams, APICallback callback) {
        getInstance().getGetDelegate().getAsyn(url, apiParams, callback);
    }

    /**
     * 异步post请求，支持Map对象的参数
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void postAsynMap(String url, Map<String, String> params, final APICallback callback) {
        getInstance().getPostDelegate().postAsyn(url, params, callback);
    }

    /**
     * 异步post请求，支持数组对象的参数
     *
     * @param url
     * @param apiParams
     * @param callback
     */
    public static void postAsynArray(String url, APIParam[] apiParams, final APICallback callback) {
        getInstance().getPostDelegate().postAsyn(url, apiParams, callback);
    }

    /**
     * 分发请求结果
     *
     * @param callback
     * @param request
     */
    private void deliveryResult(final APICallback callback, final Request request) {
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(final Request request, IOException e) {
//                e.printStackTrace();
//                APIError apiError = APIError.errorFromString("请求失败，请重试");
//                sendFailureResultCallback(apiError, callback);
//
//            }
//
//            @Override
//            public void onResponse(final Response response) throws IOException {
//                try {
//                    //这里要做一个状态码的判断，非200的都是走失败的流程
//                    if (response.code() != 200){
//                        //错的
//                        sendFailureResultCallback(response, callback);
//                    }else {
//                        final String body = response.body().string();
//                        final Object object = mGson.fromJson(body, callback.mType);
//                        sendSuccessResultCallback(object, callback);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    sendFailureResultCallback(response, callback);
//                } catch (JsonParseException e) {
//                    e.printStackTrace();
//                    sendFailureResultCallback(response, callback);
//                }
//            }
//        });
    }

    /**
     * 成功的回调，因为不在UI线程，需要handler
     *
     * @param object
     * @param callback
     */
    private void sendSuccessResultCallback(final Object object, final APICallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(object);
            }
        });
    }

    /**
     * 失败的回调，因为不在UI线程，需要handler
     *
     * @param callback
     */
    private void sendFailureResultCallback(final APIError apiError, final APICallback callback) {
        //非UI线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(apiError);
            }
        });
    }

    /**
     * Map对象转数组
     *
     * @param params
     * @return
     */
    private APIParam[] map2APIParams(Map<String, String> params) {
        if (params == null) {
            return new APIParam[0];
        }
        int size = params.size();
        APIParam[] res = new APIParam[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new APIParam(entry.getKey(), entry.getValue());
        }
        return res;
    }

    /**
     * 参数数组转成字符串
     *
     * @param params
     * @return
     */
    private String arrayParams2String(APIParam[] params) {
        String url = "";
        for (APIParam param : params) {
            url += (param.getKey() + "=" + param.getKey()) + "&";
        }
        return url.substring(0, url.length() - 1);
    }


    /**
     * 从数组对象参数获取url
     *
     * @param params
     * @return
     */
    private HttpUrl getUrlFromArrayParams(APIParam[] params) {
//        HttpUrl.Builder builder = new HttpUrl.Builder();
//        for (APIParam apiParam : params) {
//            builder.addQueryParameter(apiParam.getKey(), apiParam.getValue());
//        }
//        HttpUrl httpUrl = builder.build();


        //区分两种写法的区别，上面的写法是不行的！
        HttpUrl.Builder builder = HttpUrl.parse("").newBuilder();
        for (APIParam apiParam : params) {
            builder.addQueryParameter(apiParam.getKey(), apiParam.getValue());
        }
        HttpUrl httpUrl = builder.build();


        return httpUrl;
    }


    /**
     * 构建请求参数
     *
     * @param url
     * @param params
     * @return
     */
    private Request buildPostFormRequest(String url, APIParam[] params) {
        if (params == null) {
            params = new APIParam[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (APIParam param : params) {
            builder.add(param.getKey(), param.getValue());
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }


    public GetDelegate getGetDelegate() {
        return mGetDelegate;
    }

    public PostDelegate getPostDelegate() {
        return mPostDelegate;
    }

    //====================GetDelegate=======================
    public class GetDelegate {
        /**
         * 异步get请求，Map对象参数
         *
         * @param url
         * @param params
         * @param callback
         */
        public void getAsyn(String url, Map<String, String> params, final APICallback callback) {
            APIParam[] apiParams = null;
            if (params != null && params.size() > 0) {
                apiParams = map2APIParams(params);
            }
            getAsyn(url, apiParams, callback);
        }

        /**
         * 异步get请求，数组对象参数
         *
         * @param url
         * @param params
         * @param callback
         */
        public void getAsyn(String url, APIParam[] params, final APICallback callback) {
            HttpUrl httpUrl = new HttpUrl.Builder().build();
            if (params != null && params.length > 0) {
                httpUrl = getUrlFromArrayParams(params);
            }
            final Request request = new Request.Builder().url(url).url(httpUrl).build();
            deliveryResult(callback, request);
        }
    }

    //====================PostDelegate，post=======================
    public class PostDelegate {

        /**
         * 异步post请求，Map对象参数
         *
         * @param url
         * @param params
         * @param callback
         */
        public void postAsyn(String url, Map<String, String> params, final APICallback callback) {
            APIParam[] paramsArr = map2APIParams(params);
            postAsyn(url, paramsArr, callback);
        }

        /**
         * 异步post请求，数组对象参数
         *
         * @param url
         * @param params
         * @param callback
         */
        public void postAsyn(String url, APIParam[] params, final APICallback callback) {
            Request request = buildPostFormRequest(url, params);
            deliveryResult(callback, request);
        }
    }

}
