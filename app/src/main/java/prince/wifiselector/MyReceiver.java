package prince.wifiselector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyReceiver extends BroadcastReceiver {

    String[] myItems;
    WifiManager mainWifi;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");

        // Buggy code


//        List<ScanResult> wifiList = mainWifi.getScanResults();
//        //WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = mainWifi.getConnectionInfo();
//        String address = info.getMacAddress();
//        ArrayList<String> list = new ArrayList<>();
//        myItems = new String[wifiList.size()];
//        for (int i = 0; i < wifiList.size(); i++)
//            list.add(wifiList.get(i).toString());
//
//        for (int i = 0; i < wifiList.size(); i++) {
//            //myItems[i] = wifiList.get(i).toString();
//            //WifiInfo info = mainWifi.getConnectionInfo();
//            //String address = info.getMacAddress();
//
//            // myItems[i] = String.valueOf(wifiList.get(i).level);
//            myItems[i] = wifiList.get(i).SSID;
//
//            //myItems[i] = String.valueOf(wifiList.get(i).frequency);
//
//            mainText.setText(address);
//        }
    }
}
