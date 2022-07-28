package comp3350.ims.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.ims.R;
import comp3350.ims.business.AccessInventory;


public class LocationActivity extends Activity {

    private ArrayList < String > locationList;
    private ArrayAdapter adapter;
    private Button createButton;
    private EditText userText;
    private AccessInventory accessInventory;
    ListView listView;
    Button deleteButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_location);
        locationList = new ArrayList < String > ();

        accessInventory = new AccessInventory();

        accessInventory.getLocations(locationList);

        adapter = new ArrayAdapter < String > (this, R.layout.activity_list_item, R.id.text_view, locationList);
        listView = (ListView) findViewById(R.id.LocationList);
        listView.setAdapter(adapter);
        createButton = (Button) findViewById(R.id.btnCreateLocation);
        deleteButton = (Button) findViewById(R.id.btnDeleteLocation);
        userText = (EditText) findViewById(R.id.txtLocationName);



    }




    public void buttonsCreateLocationOnClick(View v) {
        String name = userText.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            if (!accessInventory.isLocation(name)) {
                accessInventory.addLocation(name);
                locationList.add(name);
                adapter.notifyDataSetChanged();
                userText.setText("");
                Toast toast = Toast.makeText(getApplicationContext(), "Location created", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Location already exists", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Error: Please enter a valid input", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void buttonsDeleteLocationOnClick(View v) {
        String name = userText.getText().toString().trim();
        if (!accessInventory.isLocation(name)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Location was not found", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!TextUtils.isEmpty(name) && accessInventory.removeLocation(name)) {
            locationList.remove(name);
            adapter.notifyDataSetChanged();
            userText.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Location Deleted", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Location was not found", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}