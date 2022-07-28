package comp3350.ims.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.ims.R;
import comp3350.ims.business.AccessInventory;
import comp3350.ims.objects.Inventory;
import comp3350.ims.objects.Item;
import comp3350.ims.objects.ItemType;

public class ViewAllActivity extends Activity {

    ListView listView;
    private AccessInventory accessInventory;
    private ViewAllAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accessInventory = new AccessInventory();
        int position = accessInventory.getCurrentItemPosition();
        ItemType currentItem = accessInventory.getItem(position);

        setContentView(R.layout.activity_view_all);
        listView = (ListView) findViewById(R.id.viewAllListView);
        adapter = new ViewAllAdapter(this, currentItem);
        listView.setAdapter(adapter);
    }

    public void buttonRemoveItem(View v) {
        int position = listView.getPositionForView((View) v.getParent());
        if (position >= 0) {
            accessInventory.removeIndividualItem(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
        }
    }

    private String saveLocation = "";
    public void buttonEditItemDialogOnClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Location");

        int position = listView.getPositionForView((View) v.getParent());
        Item item = accessInventory.getItem(accessInventory.getCurrentItemPosition()).getItem(position);

        final LayoutInflater inflater = getLayoutInflater();
        final View inflator = inflater.inflate(R.layout.activity_edit_dialog_viewall_row, null);

        Spinner spinLocation = (Spinner) inflator.findViewById(R.id.spinnerLocation);
        ArrayList < String > locationList = new ArrayList < > ();
        accessInventory.getLocations(locationList);

        ArrayAdapter < String > adapterLocation = new ArrayAdapter < > (this, R.layout.support_simple_spinner_dropdown_item, locationList);
        adapterLocation.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinLocation.setAdapter(adapterLocation);

        Spinner newLocation = (Spinner) inflator.findViewById(R.id.spinnerLocation);
        ArrayAdapter < String > spinnerAdap = (ArrayAdapter < String > ) newLocation.getAdapter();
        int spinnerPosition = spinnerAdap.getPosition(item.getLocation());
        newLocation.setSelection(spinnerPosition);

        builder.setView(inflator);

        // Set up the buttons
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveLocation = newLocation.getSelectedItem().toString();


                adapter.notifyDataSetChanged();
                accessInventory.editItem(item, saveLocation);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

}