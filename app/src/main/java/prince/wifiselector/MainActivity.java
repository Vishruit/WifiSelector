package prince.wifiselector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {



    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    private static EditText et1;
    public StringBuilder sb = new StringBuilder();

    Button btnOneToTwo, btnOneToFour, btnOneToWifi, btnOneToScroll;
    Button btnOneToSplash, btnOneToActFour, btnOneToListView;
    TextView tv1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        receiverWifi = new WifiReceiver();
        //registerReceiver(receiverWifi, new IntentFilter(
               // WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        turnWifiOn();

        btnOneToTwo.setOnClickListener(this);
        btnOneToFour.setOnClickListener(this);
        btnOneToWifi.setOnClickListener(this);
        btnOneToScroll.setOnClickListener(this);
        btnOneToSplash.setOnClickListener(this);
        btnOneToActFour.setOnClickListener(this);
        btnOneToListView.setOnClickListener(this);

        populateListView();
        registerClickCallBack();
    }

    protected void initialize() {
        tv1 = (TextView) findViewById(R.id.textView);
        et1 = (EditText) findViewById(R.id.eT);
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        btnOneToTwo = (Button) findViewById(R.id.button);
        btnOneToFour = (Button) findViewById(R.id.button1_4);
        btnOneToWifi = (Button) findViewById(R.id.buttonToWifi);
        btnOneToScroll = (Button) findViewById(R.id.btnScroll);
        btnOneToSplash = (Button) findViewById(R.id.buttonSplash);
        btnOneToActFour = (Button) findViewById(R.id.buttonToActFour);
        btnOneToListView = (Button) findViewById(R.id.buttonToListView);
    }

    private void turnWifiOn() {
        if (!mainWifi.isWifiEnabled()) {
            mainWifi.setWifiEnabled(true);
            et1.setText("Wifi Enabled");
        } else
            tv1.setText("Wifi was On");
    }

    private void populateListView() {
        // Create list of items
        String[] myItems = {"Blue", "Green", "Red", "Purple", "Violet"};
        // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,                      // Context for Activity
                R.layout.layout2,           // Layout for use (create)
                myItems);                  // Items to be displayed
        // Configure the list view
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "You Clicked # " + position
                        + ", which is string:" + textView.getText().toString();

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressWarnings("JavaDoc")
    @Override
    protected void onResume() {
        //registerReceiver(receiverWifi, new IntentFilter(
            //    WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();


        if (!mainWifi.isWifiEnabled()) {
            mainWifi.setWifiEnabled(true);
            et1.setText("Wifi Enabled");
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case (R.id.button):
                intent = new Intent(getApplicationContext(), ActivityThree.class);
                startActivity(intent);
                break;
            case (R.id.buttonToWifi):
                intent = new Intent(getApplicationContext(), Wifi_list.class);
                startActivity(intent);
                break;
            case (R.id.button1_4):
                intent = new Intent(getApplicationContext(), WifiConnection.class);
                startActivity(intent);
                break;
            case (R.id.buttonToActFour):
                intent = new Intent(getApplicationContext(), ActivityFour.class);
                startActivity(intent);
                break;
            case (R.id.btnScroll):
                intent = new Intent(getApplicationContext(), Scrollable.class);
                startActivity(intent);
                break;
            case (R.id.buttonToListView):
                intent = new Intent(getApplicationContext(), CustomListViewAndroidExample.class);
                startActivity(intent);
                break;
            case (R.id.buttonSplash):
                intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
                break;
        }
    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {

            ArrayList<String> connections = new ArrayList<>();

            sb = new StringBuilder();
            List<ScanResult> wifiList;
            wifiList = mainWifi.getScanResults();
            for (int i = 0; i < wifiList.size(); i++) {
                connections.add(wifiList.get(i).SSID);
                tv1.append(connections.toString());
            }

        }
    }
}



