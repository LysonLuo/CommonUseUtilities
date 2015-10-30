package util.use.common.data;

import android.content.Context;
import android.content.SharedPreferences;

import util.use.common.CommonUtilApplication;

/**
 * 保存用户信息
 * Created by Lyson on 15/8/29.
 */
public class UserPreference {
    private static UserPreference mInstance;
    private SharedPreferences preferences;

    private UserPreference(){
        Context context = CommonUtilApplication.getContext();
        preferences = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
    }

    public  static UserPreference getInstance(){
        if (mInstance == null){
            synchronized (UserPreference.class){
                if (mInstance == null){
                    mInstance = new UserPreference();
                }
            }
        }
        return mInstance;
    }
}
