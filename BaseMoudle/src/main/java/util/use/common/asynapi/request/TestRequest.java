package util.use.common.asynapi.request;

import util.use.common.asynapi.RestApiClient;
import util.use.common.asynapi.callback.RestApiCallback;
import util.use.common.asynapi.handler.RestApiHandler;
import util.use.common.model.APIError;

/**
 * Created by Lyson on 15/10/30.
 */
public class TestRequest {
    public static void test(final RestApiCallback callback) {
        RestApiClient.get("", null, new RestApiHandler() {
            @Override
            public void onSuccess(String responseString) {
                callback.onSuccess(responseString);
            }

            @Override
            public void onFailure(APIError apiError) {
                callback.onFailure(apiError);
            }
        });
    }
}
