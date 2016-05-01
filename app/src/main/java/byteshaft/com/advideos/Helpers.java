package byteshaft.com.advideos;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;


public class Helpers {

    public static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public static void setScreenBrightness(Window window, float value) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = value;
        window.setAttributes(layoutParams);
    }

    public static Boolean isPasswordSet() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(AppGlobals.PASSWORD_STATUS, false);
    }

    public static void passwordStatus(boolean value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(AppGlobals.PASSWORD_STATUS, value).apply();
    }

    public static void savePassword(String password) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putString(AppGlobals.PASSWORD, password).apply();
    }

    public static String getPassword() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(AppGlobals.PASSWORD, "");

    }
}
