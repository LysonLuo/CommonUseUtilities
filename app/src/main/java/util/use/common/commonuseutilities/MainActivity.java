package util.use.common.commonuseutilities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import util.use.common.commonuseutilities.base.BaseActivity;
import util.use.common.commonuseutilities.base.ToolbarSetter;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolbarSetter.setupMainToolbar(this, getToolBar());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
