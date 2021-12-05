package ne.iot.merdandiploma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import static ne.iot.merdandiploma.commands.led0;
import static ne.iot.merdandiploma.commands.led1;
import static ne.iot.merdandiploma.commands.led2;
import static ne.iot.merdandiploma.commands.led3;
import static ne.iot.merdandiploma.commands.led4;

public class MainActivity extends AppCompatActivity {

    ImageView changer;
    TextView intensity;
    SeekBar seekBar;
    int light_degree;
    String ip_address;
    private Menu menu;
    int wifi, strCatch;
    ConstraintLayout constraintLayout;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Seek_bar (change mode of lights)
        seekBar = findViewById(R.id.seekBar);
        changer = findViewById(R.id.changer);
        intensity = findViewById(R.id.intensity);
        constraintLayout = findViewById(R.id.constraint);

        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);
        light_degree = PrefConfig.loadLightState(this);
        wifi = PrefConfig.loadWifi(this);

        if (wifi == 0) {
            seekBar.setVisibility(View.INVISIBLE);
            handler = new Handler();
            handler.postDelayed(runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000); // this delay repeat CLASS every GIVEN seconds
                    LDRCatch();
                    Log.e("Main Response => ", "GETTING DATA...");
                }
            }, 3000); // this delay run first
        }

        if (light_degree == 1) {
            seekBar.setProgress(1);
            intensity.setText(R.string.percent25);
            changer.setImageResource(R.drawable.ic_lamp1);
        } else if (light_degree == 2) {
            seekBar.setProgress(2);
            intensity.setText(R.string.percent50);
            changer.setImageResource(R.drawable.ic_lamp2);
        } else if (light_degree == 3) {
            seekBar.setProgress(3);
            intensity.setText(R.string.percent75);
            changer.setImageResource(R.drawable.ic_lamp3);
        } else if (light_degree == 4) {
            seekBar.setProgress(4);
            intensity.setText(R.string.percent100);
            changer.setImageResource(R.drawable.ic_lamp4);
        } else if (light_degree == 0 ) {
            seekBar.setProgress(0);
            intensity.setText(R.string.percent0);
            changer.setImageResource(R.drawable.ic_lamp);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int degree, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                light_degree = seekBar.getProgress();
                if (light_degree == 0) {
                    led0(getApplicationContext());
                    intensity.setText(R.string.percent0);
                    changer.setImageResource(R.drawable.ic_lamp);
                } else if (light_degree == 1) {
                    led1(getApplicationContext());
                    intensity.setText(R.string.percent25);
                    changer.setImageResource(R.drawable.ic_lamp1);
                } else if (light_degree == 2) {
                    led2(getApplicationContext());
                    intensity.setText(R.string.percent50);
                    changer.setImageResource(R.drawable.ic_lamp2);
                } else if (light_degree == 3) {
                    led3(getApplicationContext());
                    intensity.setText(R.string.percent75);
                    changer.setImageResource(R.drawable.ic_lamp3);
                } else if (light_degree == 4) {
                    led4(getApplicationContext());
                    intensity.setText(R.string.percent100);
                    changer.setImageResource(R.drawable.ic_lamp4);
                }
                PrefConfig.saveLightState(getApplicationContext(), light_degree);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (wifi == 1) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_baseline_wifi_24));
        } else if (wifi == 0){
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_baseline_brightness_auto_24));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            case R.id.action_mode:
                if (wifi == 1) {
                    menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_baseline_brightness_auto_24));
                    wifi = 0;
                    PrefConfig.saveWifi(getApplicationContext(), wifi);
                    seekBar.setVisibility(View.INVISIBLE);
                    Snackbar.make(constraintLayout, R.string.internet, Snackbar.LENGTH_SHORT).show();
                } else {
                    menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_baseline_wifi_24));
                    wifi = 1;
                    PrefConfig.saveWifi(getApplicationContext(), wifi);
                    seekBar.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(runnable);
                    Snackbar.make(constraintLayout, R.string.brightness, Snackbar.LENGTH_SHORT).show();
                }
                recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void LDRCatch() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://" + ip_address + "/catch";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Log.e("RESPONSE => ", "LED CATCH => " + response.substring(0,10));
            strCatch = Integer.parseInt(response);

            if (strCatch >= 400) {
                led1(this);
            } else if (strCatch >= 550) {
                led2(this);
            } else if (strCatch >= 700) {
                led3(this);
            } else if (strCatch >= 850) {
                led4(this);
            } else if (strCatch <= 390) {
                led0(this);
            }
        },
                error -> Log.e("RESPONSE => ", "LED CATCH ERROR"));
        queue.add(stringRequest);
    }
}