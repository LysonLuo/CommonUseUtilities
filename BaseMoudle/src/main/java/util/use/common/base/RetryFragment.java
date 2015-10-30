package util.use.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import util.use.common.R;

/**
 * 重试页面，可以点击重新加载
 * Created by Lyson on 15/8/1.
 */
public class RetryFragment extends BaseFragment {
    @InjectView(R.id.textview_retry)
    TextView retryTextView;
    private OnRetryListener mRetryListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retry, container, false);
        ButterKnife.inject(this, view);

        retryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRetryListener != null) {
                    mRetryListener.onRetry();
                    //点击重试之后移除当前页面
                    getFragmentManager().beginTransaction().remove(RetryFragment.this).commit();
                }
            }
        });
        return view;

    }

    public void setOnRetryListener(OnRetryListener listener) {
        mRetryListener = listener;
    }


    public interface OnRetryListener {
        void onRetry();
    }
}
