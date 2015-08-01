package util.use.common.commonuseutilities.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

/**
 * 所有Fragment的基类
 * Created by Lyson on 15/8/1.
 */
public class BaseFragment extends Fragment {
    private static final String LogTag = BaseActivity.class.getCanonicalName();
    private ProgressDialog mProgressDialog;
    private ProgressFragment progressFragment;
    private RetryFragment retryFragment;
    private NoDataFragment noDataFragment;

    private boolean isFragmentAlive = true;

    @Override
    public void onDestroy() {
        super.onDestroy();
        isFragmentAlive = false;
    }

    /**
     * 显示圆形进度条的Fragment，需要显示指定ViewGroup的id
     *
     * @param layoutId
     */
    protected void showProgressFragment(int layoutId) {
        if (!isFragmentAlive) {
            CommonLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (getActivity() != null && !((BaseActivity) getActivity()).isActivityAlive()) {
            CommonLog.d(LogTag, "activity is not alive");
            return;
        }
        if (progressFragment == null) {
            progressFragment = new ProgressFragment();
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!progressFragment.isAdded()) {
            ft.add(layoutId, progressFragment);
        }
        ft.show(progressFragment);

        if (retryFragment != null && retryFragment.isVisible()) {
            ft.remove(retryFragment);
        }

        ft.commitAllowingStateLoss();
    }

    /**
     * 移除圆形进度条的Fragment
     */
    protected void removeProgressFragment() {
        if (!isFragmentAlive) {
            CommonLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            CommonLog.d(LogTag, "activity is not alive");
            return;
        }
        if (progressFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(progressFragment);
        ft.commitAllowingStateLoss();
        progressFragment = null;
    }

    /**
     * 在数据加载失败或其他异常情况的情况下，调用该方法显示RetryFragment，需要显示指定ViewGroup的id
     * 因为在回调方法定义的结尾主动移除了自身，所以不再需要独立的方法去移除
     */
    protected void showRetryFragment(int layoutId, RetryFragment.OnRetryListener listener) {
        if (!isFragmentAlive) {
            CommonLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            CommonLog.d(LogTag, "activity is not alive");
            return;
        }
        if (retryFragment == null) {
            retryFragment = new RetryFragment();
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!retryFragment.isAdded()) {
            ft.add(layoutId, retryFragment, "");
        }
        ft.show(retryFragment);
        ft.commitAllowingStateLoss();
        retryFragment.setOnRetryListener(listener);
    }

    /**
     * 无数据时所显示的页面，需要显示指定ViewGroup的id
     *
     * @param layouId
     */
    protected void showNoDataFragment(int layouId) {
        if (!isFragmentAlive) {
            CommonLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            CommonLog.d(LogTag, "activity is not alive");
            return;
        }
        if (noDataFragment == null) {
            noDataFragment = new NoDataFragment();
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!noDataFragment.isAdded()) {
            ft.add(layouId, noDataFragment, "");
        }
        ft.show(noDataFragment);
        ft.commitAllowingStateLoss();
    }

    protected void showNoDataFragment(int layouId, String text) {
        showNoDataFragment(layouId);
        if (noDataFragment != null) {
            noDataFragment.setText(text);
        }
    }

    /**
     * 移除没有数据的Fragment
     */
    protected void removeNoDataFragment() {
        if (!isFragmentAlive) {
            CommonLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            CommonLog.d(LogTag, "activity is not alive");
            return;
        }
        if (noDataFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(noDataFragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 显示信息进度条
     *
     * @param message
     */
    protected void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    /**
     * 移除信息进度条
     */
    protected void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 段时间的提示
     *
     * @param message
     */
    protected void showShortToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间的提示
     *
     * @param message
     */
    protected void showLongToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


}
