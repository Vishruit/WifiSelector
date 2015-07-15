package prince.wifiselector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Wifi_list extends Activity implements View.OnClickListener {
    private WifiManager wifi;
    private ListView lv;
    TextView textStatus;
    private Button buttonScan;
    private Switch enable;
    private int size;
    private List<ScanResult> results;


    private final String ITEM_KEY = "key";
    private final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private SimpleAdapter adapter;

    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);

        initialize();

        enable.setChecked(wifi.isWifiEnabled());
        enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag) {
                wifi.setWifiEnabled(flag);
                if (flag) {
                    arrayList.clear();
                    lv.setVisibility(View.VISIBLE);
                    buttonScan.setEnabled(true);
                    wifi.startScan();
                } else {
                    lv.setVisibility(View.INVISIBLE);
                    buttonScan.setEnabled(false);
                }
            }
        });
        this.adapter = new SimpleAdapter(Wifi_list.this,
                arrayList,
                R.layout.layout2,
                new String[]{ITEM_KEY},
                new int[]{R.id.checkbox});

        lv.setAdapter(this.adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int a = lv.getSelectedItemPosition();
                buttonScan.setText(String.valueOf(a));
            }
        });

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                results = wifi.getScanResults();
                size = results.size();
                for (int i = size - 1; i >= 0; i--) {
                    HashMap<String, String> item = new HashMap<>();
                    item.put(ITEM_KEY, results.get(i).SSID);
                    arrayList.add(item);
                }
                if (size > 0)
                    adapter.notifyDataSetChanged();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void initialize() {
        buttonScan = (Button) findViewById(R.id.button1);
        buttonScan.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.listView1);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        size = 0;
        enable = (Switch)findViewById(R.id.switch1);

    }

    public void onClick(View view) {
        arrayList.clear();
        wifi.startScan();
        Toast.makeText(this, "Scanning....", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(android.content.BroadcastReceiver);
    }


}