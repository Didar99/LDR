package ne.iot.merdandiploma;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {
    private static final String MY_PREFERENCE_NAME = "com.iot.smart_home";
    private static final String PREF_IP_KEY = "pref_ip_key";
    private static final String PREF_LIGHT_STATE_KEY = "pref_light_state_key";
    private static final String PREF_WIFI_KEY = "pref_wifi_key";

    // for IP address
    public static void saveIpPref(Context context, String ip) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_IP_KEY, ip);
        editor.apply();
    }

    public static String loadIpPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_IP_KEY, "192.168.43.149");
    }

    // for LIGHT STATE
    public static void saveLightState(Context context, int state) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LIGHT_STATE_KEY, state);
        editor.apply();
    }

    public static int loadLightState(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_LIGHT_STATE_KEY, 0);
    }
    // for WIFI STATE
    public static void saveWifi(Context context, int wifi) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_WIFI_KEY, wifi);
        editor.apply();
    }

    public static int loadWifi(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_WIFI_KEY, 0);
    }
}