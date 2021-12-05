package ne.iot.merdandiploma;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class commands {
    static String ip_address;

    public static void led0(Context context) {
        ip_address = PrefConfig.loadIpPref(context);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://" + ip_address + "/led0";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Display the first 500 characters of the response string.
                    Log.e("RESPONSE => ", "LED 0 => " + response.substring(0,10));
                }, error -> Log.e("RESPONSE => ", "LED 0 ERROR"));
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public static void led1(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://" + ip_address + "/led1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.e("RESPONSE => ", "LED 1 => " + response.substring(0,10)), error -> Log.e("RESPONSE => ", "LED 1 ERROR"));
        queue.add(stringRequest);
    }
    public static void led2(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://" + ip_address + "/led2";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.e("RESPONSE => ", "LED 2 => " + response.substring(0,10)), error -> Log.e("RESPONSE => ", "LED 2 ERROR"));
        queue.add(stringRequest);
    }
    public static void led3(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://" + ip_address + "/led3";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.e("RESPONSE => ", "LED 3 => " + response.substring(0,10)), error -> Log.e("RESPONSE => ", "LED 3 ERROR"));
        queue.add(stringRequest);
    }
    public static void led4(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://" + ip_address + "/led4";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.e("RESPONSE => ", "LED 4 => " + response.substring(0,10)), error -> Log.e("RESPONSE => ", "LED 4 ERROR"));
        queue.add(stringRequest);
    }
    public static void ledCatch(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://" + ip_address + "/catch";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.e("RESPONSE => ", "LED CATCH => " + response.substring(0,10)), error -> Log.e("RESPONSE => ", "LED CATCH ERROR"));
        queue.add(stringRequest);
    }
}
