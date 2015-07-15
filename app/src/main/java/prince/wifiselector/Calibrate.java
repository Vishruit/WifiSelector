package prince.wifiselector;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Calibrate extends ActionBarActivity implements View.OnClickListener {

    EditText eT;
    TextView tv;
    private Button btnStartScan, btnCalibrate, btnSaveValues, btnOverwrite, btnSaveNewNetworks;
    String FILENAME = "wifi data";
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate);

        initialize();

        btnCalibrate.setOnClickListener(this);
        btnOverwrite.setOnClickListener(this);
        btnSaveNewNetworks.setOnClickListener(this);
        btnSaveValues.setOnClickListener(this);
        btnStartScan.setOnClickListener(this);
    }

    private void initialize() {
        tv = (TextView) findViewById(R.id.textView3);
        eT = (EditText) findViewById(R.id.eT);
        btnStartScan = (Button) findViewById(R.id.btnStartScan);
        btnCalibrate = (Button) findViewById(R.id.btnCalibrate);
        btnSaveValues = (Button) findViewById(R.id.btnSaveValues);
        btnOverwrite = (Button) findViewById(R.id.btnOverwrite);
        btnSaveNewNetworks = (Button) findViewById(R.id.btnSaveNewNetworks);
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calibrate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnStartScan:
                startScan();
                break;
            case R.id.btnSaveNewNetworks:
                saveNetworks();
                break;
            case R.id.btnSaveValues:
                saveValues();
                break;
            case R.id.btnCalibrate:
                calibrate();
                break;
            case R.id.btnOverwrite:
                overwrite();
                break;
        }
    }

    private void overwrite() {
        String data = eT.getText().toString();
        File f = new File(FILENAME);
      /*  try {
            fos= new FileOutputStream(f);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calibrate() {
        String collected = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            byte[] dataArray = new byte[fis.available()];
            while (fis.read(dataArray) != -1){
                collected = new String(dataArray);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                tv.setText(collected);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void saveValues() {

    }

    private void saveNetworks() {

    }

    private void startScan() {

    }
}
