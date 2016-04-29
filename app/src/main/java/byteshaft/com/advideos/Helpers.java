package byteshaft.com.advideos;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Helpers {

    public static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }
}
