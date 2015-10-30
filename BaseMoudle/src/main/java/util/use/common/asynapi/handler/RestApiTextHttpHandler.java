package util.use.common.asynapi.handler;

import com.loopj.android.http.TextHttpResponseHandler;

import util.use.common.base.CommonLog;

/**
 * Created by Lyson on 15/10/30.
 */
abstract public class RestApiTextHttpHandler extends TextHttpResponseHandler {
    private static final String LOG_TAG = RestApiTextHttpHandler.class.getSimpleName();

    @Override
    public void onStart() {
        super.onStart();
        CommonLog.d(LOG_TAG, "onStart");
    }

    @Override
    public void onFinish() {
        super.onFinish();
        CommonLog.d(LOG_TAG, "onFinish");
    }
}
