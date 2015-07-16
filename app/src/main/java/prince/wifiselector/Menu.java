package prince.wifiselector;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

    String classes[] = {"SoftwareSwitch" ,"ActivityFour", "ActivityThree", "MainActivity",
            "Scrollable", "WifiConnection", "WifiScanner", "MainActivity2",
            "initialActivity", "ExpandableListView", "CustomAdapter",
            "CustomListViewAndroidExample", "Calibrate", "WirelessSwitch",
            "objectSender", "objectReceiver"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String classNameAtPosition = classes[position];
        try {
            Class ourClass = Class.forName("prince.wifiselector." + classNameAtPosition);
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}