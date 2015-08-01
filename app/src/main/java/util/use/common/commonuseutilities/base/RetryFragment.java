package util.use.common.commonuseutilities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import util.use.common.commonuseutilities.R;

/**
 * 重试页面，可以点击重新加载
 * Created by Lyson on 15/8/1.
 */
public class RetryFragment extends BaseFragment {
    @InjectView(R.id.textview_retry)
    private TextView retryTextView;
    private OnRetryListener retryListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retry, container, false);
        ButterKnife.inject(this, view);

        retryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retryListener != null) {
                    retryListener.onRetry();
                    getFragmentManager().beginTransaction().remove(RetryFragment.this).commit();
                }
            }
        });
        return view;

    }

    public void setOnRetryListener(OnRetryListener listener) {
        retryListener = listener;
    }


    public interface OnRetryListener {
        void onRetry();
    }
}
