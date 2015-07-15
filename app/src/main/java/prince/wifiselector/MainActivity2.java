package prince.wifiselector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity2 extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = "WifiDemo";
    WifiManager wifi;
    private BroadcastReceiver receiver;

    private TextView text;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        // get wifi status
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        text.append("\n\nwifi status :" + info.toString());

        //list available networks
        List<WifiConfiguration> configurations = wifi.getConfiguredNetworks();

        for (WifiConfiguration configuration : configurations) {
            text.append("\n\n" + configuration.toString());
        }

        // register broadcast receiver
        if (receiver == null)
            receiver = new WifiScanner(this);
        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onStop() {

        unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getApplicationContext(), "All Network searched !!", Toast.LENGTH_LONG).show();
        if (v.getId() == R.id.btn) {
            Log.d(TAG, "onCreate() wifi.start");
            wifi.startScan();
        }
    }
}
