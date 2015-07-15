package prince.wifiselector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class WifiScanner extends BroadcastReceiver {


    private static final String TAG = "WifiScanReceiver";
    private final MainActivity2 main;

    public WifiScanner(MainActivity2 main) {
        super();
        this.main = main;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        List<android.net.wifi.ScanResult> results = main.wifi.getScanResults();
        ScanResult bestSignal = null;
        for (ScanResult result : results) {
            if (bestSignal!= null)
            if (WifiManager.compareSignalLevel(bestSignal.level, result.level) > 0)
                bestSignal = result;
        }

        String message = String.format("%s networks found.%s is the strongest.", results.size(), bestSignal.SSID);
        Toast.makeText(main, message, Toast.LENGTH_LONG).show();
        Log.d(TAG, "onReceive() message :" + message);
        //wifi.startScan();
    }
}