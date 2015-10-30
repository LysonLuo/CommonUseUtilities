package util.use.common.asynapi;

import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import util.use.common.BuildConfig;
import util.use.common.CommonUtilApplication;
import util.use.common.asynapi.handler.RestApiHandler;
import util.use.common.asynapi.handler.RestApiTextHttpHandler;
import util.use.common.base.CommonLog;
import util.use.common.model.APIError;
import util.use.common.utilities.ConnectivityUtils;

/**
 * Created by Lyson on 15/9/14.
 */
public class RestApiClient {
    private static final String LOG_TAG = RestApiClient.class.getSimpleName();
    private static final String KEY = "";//这个渠道key应该是要的吧

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(final String path, RequestParams params, final RestApiHandler handler) {
        if (!checkNetWork()) {
            handler.onFailure(APIError.errorFromString("请求失败，请重试！", 0));
            return;
        }
        if (params == null) {
            params = new RequestParams();
        }
//        params.add("key", KEY);
        client.get(path, params, new RestApiTextHttpHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                dispatchSuccessResult(path, responseString, handler);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dispatchFailureResult(path, statusCode, responseString, handler);
            }
        });
    }


    public static void post(final String path, RequestParams params, final RestApiHandler handler) {
        if (!checkNetWork()) {
            handler.onFailure(APIError.errorFromString("请求失败，请重试！", 0));
            return;
        }
        if (params == null) {
            params = new RequestParams();
        }
//        params.add("key", KEY);
        client.post(path, params, new RestApiTextHttpHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                dispatchSuccessResult(path, responseString, handler);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dispatchFailureResult(path, statusCode, responseString, handler);
            }
        });
    }

    /**
     * 统一处理请求成功的结果
     *
     * @param path         路径
     * @param resultString json字符串
     * @param handler
     */
    private static void dispatchSuccessResult(String path, String resultString, RestApiHandler handler) {
        try {
            if (isDebug()) {
                CommonLog.d(LOG_TAG, "path = " + path + ", result= ");
                CommonLog.d(LOG_TAG, resultString);
            }
            handler.onSuccess(resultString);
        } catch (Exception e) {
            CommonLog.d(LOG_TAG, e.getMessage());
        }
    }

    /**
     * 统一处理请求失败的结果
     *
     * @param path         路径
     * @param statusCode   状态码
     * @param resultString json字符串
     * @param handler
     */
    private static void dispatchFailureResult(String path, int statusCode, String resultString, RestApiHandler handler) {
        try {
            if (isDebug()) {
                CommonLog.d(LOG_TAG, "path = " + path + ", result= ");
                CommonLog.d(LOG_TAG, resultString);
            }
            APIError apiError = APIError.errorFromString(resultString, statusCode);
            handler.onFailure(apiError);
        } catch (Exception e) {
            CommonLog.d(LOG_TAG, e.getMessage());
        }
    }

    /**
     * 检查网络状况
     *
     * @return
     */
    private static boolean checkNetWork() {
        if (!ConnectivityUtils.isConnected(CommonUtilApplication.getContext())) {
            Toast.makeText(CommonUtilApplication.getContext(), "网络不太给力，请重试！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 是否开启调试，输出日志
     *
     * @return true开启；false关闭；
     */
    private static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
