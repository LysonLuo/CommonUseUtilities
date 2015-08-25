package util.use.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.InjectView;
import util.use.common.R;

/**
 * 没有数据的时候显示的页面
 * Created by Lyson on 15/8/1.
 */
public class NoDataFragment extends BaseFragment {
    @InjectView(R.id.textview_nodata)
    TextView mNoDataTextView;
    private String mNoDataText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nodata, container, false);

        mNoDataTextView.setText(mNoDataText);
        return view;
    }

    public void setText(String text) {
        this.mNoDataText = text;
        if (mNoDataTextView != null) {
            mNoDataTextView.setText(text);
        }
    }
}
