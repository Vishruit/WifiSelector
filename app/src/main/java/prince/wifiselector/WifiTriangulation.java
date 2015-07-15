package prince.wifiselector;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ToggleButton;


public class WifiTriangulation extends Activity {

    Boolean i =true;
    private float RSSI1,RSSI2, RSSI3;
    private float RSSI1desired, RSSI2desired, RSSI3desired;
    ToggleButton Alohomora;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_triangulation);



        initialize();
        while(i){


            Alohomora();// Send command to open the door

            new Handler().postDelayed(new Runnable() {  // To run the code after every 5 seconds

                @Override
                public void run() {

                }
            }, 5000);
        }
    }

    private void initialize() {
        RSSI1=0;
        RSSI2=0;
        RSSI3=0;

        RSSI1desired=0;
        RSSI2desired=0;
        RSSI3desired=0;

        Alohomora= (ToggleButton) findViewById(R.id.toggleBtnOpen);
    }

    // function to calculate the signal strengths and take decisions

    protected void Alohomora() {
        if ((RSSI1-RSSI1desired <5 | RSSI1-RSSI1desired >-5)
                & (RSSI2-RSSI2desired <5 | RSSI2-RSSI2desired >-5)
                &(RSSI3-RSSI3desired <5 | RSSI3-RSSI3desired >-5)){

            //Todo switching on
        }

    }

    private int openDoor() {

        // keep the door open for 7 seconds

        // Send signal to ESP8266 AT commands
        // ToDo Install node Mcu in esp module
        // ToDo get the required module from Venkat Sir - FTDI and Logic Shifter

        return 0;
    }
}