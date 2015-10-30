package util.use.common.ui;

import android.os.Bundle;

import util.use.common.R;
import util.use.common.asynapi.callback.RestApiCallback;
import util.use.common.asynapi.request.TestRequest;
import util.use.common.base.BaseActivity;
import util.use.common.base.ToolbarSetter;
import util.use.common.model.APIError;

/**
 * 程序主界面
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolbarSetter.setupMainToolbar(getToolBar());
        TestRequest.test(new RestApiCallback() {
            @Override
            public void onSuccess(String responseString) {

            }

            @Override
            public void onFailure(APIError apiError) {

            }
        });
    }
}
