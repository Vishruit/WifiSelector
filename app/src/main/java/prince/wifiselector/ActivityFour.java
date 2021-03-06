package prince.wifiselector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityFour extends Activity {

    private WifiManager mainWifi;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_four);

        // Initiate wifi service manager
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mainText = (TextView) findViewById(R.id.mainText);
        // wifi scanned value broadcast receiver
        WifiReceiver receiverWifi = new WifiReceiver();

        // Register broadcast receiver
        // Broadcast receiver will automatically call when number of wifi connections changed
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();

        String[] myItems = {"Blue", "Green", "Red", "Purple", "Violet"};
        populateListView(myItems);
    }

    class WifiReceiver extends BroadcastReceiver {

        String[] myItems;

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {

            List<ScanResult> wifiList = mainWifi.getScanResults();
            //WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = mainWifi.getConnectionInfo();
            String address = info.getMacAddress();
            ArrayList<String> list = new ArrayList<>();
            myItems = new String[wifiList.size()];
            for (int i = 0; i < wifiList.size(); i++)
                list.add(wifiList.get(i).toString());

            for (int i = 0; i < wifiList.size(); i++) {
                //myItems[i] = wifiList.get(i).toString();
                //WifiInfo info = mainWifi.getConnectionInfo();
                //String address = info.getMacAddress();

                // myItems[i] = String.valueOf(wifiList.get(i).level);
                myItems[i] = wifiList.get(i).SSID;

                //myItems[i] = String.valueOf(wifiList.get(i).frequency);

                mainText.setText(address);
            }
            populateListView(myItems);
        }
    }

    private void populateListView(String[] myItems) {
        // Create list of items
        //String[] myItems = {"Blue", "Green", "Red", "Purple", "Violet"};

        // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,                      // Context for Activity
                R.layout.layout2,           // Layout for use (create)
                myItems);                  // Items to be displayed

        // Configure the list view
        ListView list = (ListView) findViewById(R.id.listViewActFour);
        list.setAdapter(adapter);
    }


}
