package util.use.common.asynapi.callback;

import util.use.common.model.APIError;

/**
 * Created by Lyson on 15/10/30.
 */
abstract public class RestApiCallback {

    abstract public void onSuccess(String responseString);

    abstract public void onFailure(APIError apiError);
}
