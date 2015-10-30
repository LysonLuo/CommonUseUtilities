package util.use.common.asynapi.handler;

import util.use.common.model.APIError;

/**
 * Created by Lyson on 15/9/15.
 */
abstract public class RestApiHandler {

    abstract public void onSuccess(String responseString);

    abstract public void onFailure(APIError apiError);
}
