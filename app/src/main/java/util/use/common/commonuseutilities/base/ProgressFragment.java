package util.use.common.commonuseutilities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import util.use.common.commonuseutilities.R;

/**
 * 显示加载进度的页面
 * Created by Lyson on 15/8/1.
 */
public class ProgressFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        return view;
    }
}
