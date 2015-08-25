package util.use.common.base;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;

import util.use.common.R;


/**
 * ToolBar设置
 * Created by Lyson on 15/8/1.
 */
public class ToolbarSetter {
    /**
     * 设置默认的Tollbar
     *
     * @param context
     * @param toolbar
     */
    public static void setupDefaultToolbar(final Context context, Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).finish();
                }
            }
        });
    }

    /**
     * 设置MainActivity的Toolbar
     *
     * @param toolbar
     */
    public static void setupMainToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(null);
    }
}
