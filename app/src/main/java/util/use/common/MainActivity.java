package util.use.common;

import android.os.Bundle;

import util.use.common.base.BaseActivity;
import util.use.common.base.ToolbarSetter;

/**
 * 程序主界面
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolbarSetter.setupMainToolbar(getToolBar());
    }
}
