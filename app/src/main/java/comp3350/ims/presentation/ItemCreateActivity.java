package comp3350.ims.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import comp3350.ims.R;
import comp3350.ims.business.AccessInventory;
import comp3350.ims.objects.ItemType;

public class ItemCreateActivity extends AppCompatActivity {

    private EditText itemName;
    private EditText itemPrice;
    private EditText itemQuantity;
    private Spinner itemCategory;
    private Spinner itemLocation;
    private Button createBtn;
    private AccessInventory accessInventory;
    private ArrayList < String > categoryList;
    private ArrayList < String > locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_create);
        accessInventory = new AccessInventory();
        Spinner spinCategory = findViewById(R.id.spinnerCategory);

        categoryList = new ArrayList < > ();
        locationList = new ArrayList < > ();

        accessInventory.getCategories(categoryList);
        accessInventory.getLocations(locationList);
        ArrayAdapter < String > adapterCategory = new ArrayAdapter < > (this, R.layout.support_simple_spinner_dropdown_item, categoryList);
        adapterCategory.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinCategory.setAdapter(adapterCategory);

        Spinner spinLocation = findViewById(R.id.spinnerLocation);
        ArrayAdapter < String > adapterLocation = new ArrayAdapter < > (this, R.layout.support_simple_spinner_dropdown_item, locationList);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinLocation.setAdapter(adapterLocation);

        //setting default date to today's date.
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        EditText date = (EditText) findViewById(R.id.editTextDate);
        date.setText(thisDate);

        itemName = findViewById(R.id.itemNameInput);
        itemPrice = findViewById(R.id.itemPriceInput);
        itemQuantity = findViewById(R.id.itemQuantityInput);
        itemCategory = spinCategory;
        itemLocation = spinLocation;
        createBtn = findViewById(R.id.itemCreateBtn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nameString = itemName.getText().toString();
                String priceString = itemPrice.getText().toString();
                String quantityString = itemQuantity.getText().toString();

                ItemType newItem;

                if (TextUtils.isEmpty(nameString)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: name can't be empty", Toast.LENGTH_SHORT);
                    toast.show();

                } else if (TextUtils.isEmpty(priceString)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: Please enter a valid price", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (TextUtils.isEmpty(quantityString)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: Please enter a valid quantity", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    try {
                        String categoryString = itemCategory.getSelectedItem().toString();
                        String locationString = itemLocation.getSelectedItem().toString();
                        float price = Float.parseFloat(priceString);
                        int quantity = Integer.parseInt(quantityString);
                        boolean isInserted = accessInventory.insertItemType(nameString, price, quantity, locationString, thisDate, categoryString);
                        if (isInserted) {
                            Toast.makeText(ItemCreateActivity.this, "New Item Created", Toast.LENGTH_SHORT).show();
                            itemName.setText("");
                            itemPrice.setText("");
                            itemQuantity.setText("");

                        } else {
                            Toast.makeText(ItemCreateActivity.this, "Item already exists", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(ItemCreateActivity.this, "Please select a location and a category", Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });
    }
}