package ne.iot.merdandiploma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText editIP;
    String strIp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // declare variables
        editIP = findViewById(R.id.editIP);

        // load data from PrefConfig
        strIp = PrefConfig.loadIpPref(getApplicationContext());

        editIP.setText(strIp);
    }

    private void saveData() {
        // get user data and save into mobile phone
        strIp = editIP.getText().toString();

        PrefConfig.saveIpPref(getApplicationContext(), strIp);
        // show text for successfully create a new user
        Toast.makeText(SettingsActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_save) {// if place doesn't exist any characters
            if (editIP.getText().toString().length() <= 0) {
                editIP.setError(getResources().getString(R.string.fit));
            } else {
                saveData();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}