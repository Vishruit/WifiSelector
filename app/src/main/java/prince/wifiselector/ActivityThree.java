package prince.wifiselector;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityThree extends ActionBarActivity {


    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    private StringBuilder sb = new StringBuilder();
    private TextView tv;
    private List<ScanResult> scanList;

    private final Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_three);

        //tv2DisplayWifi = (TextView) findViewById(R.id.txtWifiNetworks);
        tv = (TextView) findViewById(R.id.txtWifiNetworks);

        displayWifiResults();
    }


    private void displayWifiResults() {
        //Same as getWifiNetworkList


        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        final WifiManager wifiManager =
                (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        registerReceiver(new BroadcastReceiver() {

            @SuppressLint("UseValueOf")
            @Override
            public void onReceive(Context context, Intent intent) {
                sb = new StringBuilder();
                scanList = wifiManager.getScanResults();
                sb.append("\n  Number Of Wifi connections :" + " ").append(scanList.size()).append("\n\n");
                for (int i = 0; i < scanList.size(); i++) {
                    sb.append(Integer.toString(i + 1)).append(". ");
                    sb.append((scanList.get(i)).toString());
                    sb.append("\n\n");
                }

                tv.setText(sb);
            }

        }, filter);
        wifiManager.startScan();


    }


    void doInBack() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

                receiverWifi = new WifiReceiver();
                registerReceiver(receiverWifi, new IntentFilter(
                        WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                mainWifi.startScan();
                doInBack();
            }
        }, 1000);

    }

    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }


    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {

            ArrayList<String> connections = new ArrayList<>();
            ArrayList<Float> Signal_Strenth = new ArrayList<>();

            sb = new StringBuilder();
            List<ScanResult> wifiList;
            wifiList = mainWifi.getScanResults();
            for (int i = 0; i < wifiList.size(); i++) {

                connections.add(wifiList.get(i).SSID);
            }


        }
    }

}


/**

 public class CheckWifiNetworkActivity extends Activity {

 private StringBuilder sb = new StringBuilder();
 private TextView tv;
 List<ScanResult> scanList;

 @Override protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);
 tv= (TextView)findViewById(R.id.txtWifiNetworks);
 getWifiNetworksList();
 }

 private void getWifiNetworksList(){
 IntentFilter filter = new IntentFilter();
 filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
 final WifiManager wifiManager =
 (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);;
 registerReceiver(new BroadcastReceiver(){

 @SuppressLint("UseValueOf") @Override
 public void onReceive(Context context, Intent intent) {
 sb = new StringBuilder();
 scanList = wifiManager.getScanResults();
 sb.append("\n  Number Of Wifi connections :" + " " +scanList.size()+"\n\n");
 for(int i = 0; i < scanList.size(); i++){
 sb.append(new Integer(i+1).toString() + ". ");
 sb.append((scanList.get(i)).toString());
 sb.append("\n\n");
 }

 tv.setText(sb);
 }

 },filter);
 wifiManager.startScan();
 }
 }
 */