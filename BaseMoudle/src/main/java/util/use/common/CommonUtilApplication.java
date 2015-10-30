package util.use.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lyson on 15/8/26.
 */
public class CommonUtilApplication extends Application {
    private static CommonUtilApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * !不要在Activity或View里调用此方法!
     * <a href="http://stackoverflow.com/questions/987072/using-application-context-everywhere">参考说明</a>
     *
     * @return
     */
    public static Context getContext() {
        return mInstance;
    }
}
