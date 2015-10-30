package util.use.common.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lyson on 15/8/26.
 */
public class APIError extends APIObject {
    public static final String API_TOKEN_WRONG_ERROR = "api_token_wrong";
    private static final String DEFAULT_ERROE_MESSAGE = "网络不太给力，请重试！";
    public int statusCode;
    public String errorMessage;

    /**
     * 默认错误
     *
     * @return
     */
    public static APIError defaultError() {
        APIError apiError = new APIError();
        apiError.statusCode = -1;
        apiError.errorMessage = DEFAULT_ERROE_MESSAGE;
        return apiError;
    }

    /**
     * 数据解析失败
     *
     * @return
     */
    public static APIError JSONParseError() {
        APIError apiError = new APIError();
        apiError.statusCode = -2;
        apiError.errorMessage = DEFAULT_ERROE_MESSAGE;
        return apiError;
    }


    public static APIError errorFromJSONObject(JSONObject jsonObject, int statusCode) {
        APIError error = new APIError();
        error.statusCode = statusCode;
        if (jsonObject != null && jsonObject.has("errors")) {
            try {
                error.errorMessage = jsonObject.getString("errors");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            error.errorMessage = "请求失败，请重试";
        }
        return error;
    }

    /**
     * api token 错误
     *
     * @return
     */
    public boolean isApiTokenWrongError() {
        if (errorMessage.equals(API_TOKEN_WRONG_ERROR)) {
            return true;
        }
        return false;
    }

    /**
     * json字符串错误
     *
     * @param responseString
     * @param statusCode
     * @return
     */
    public static APIError errorFromString(String responseString, int statusCode) {
        APIError apiError = new APIError();
        apiError.statusCode = statusCode;
        apiError.errorMessage = responseString;
        return apiError;
    }
}
