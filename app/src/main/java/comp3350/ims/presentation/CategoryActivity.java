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

public class CategoryActivity extends Activity {

    private ArrayList < String > categoryList;
    private ArrayAdapter adapter;
    private Button createButton;
    private EditText userText;
    private AccessInventory accessInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_category);
        categoryList = new ArrayList < String > ();

        accessInventory = new AccessInventory();

        accessInventory.getCategories(categoryList);

        adapter = new ArrayAdapter < String > (this, R.layout.activtylist_view, categoryList);
        ListView listView = (ListView) findViewById(R.id.categoryList);
        listView.setAdapter(adapter);
        createButton = (Button) findViewById(R.id.btnCreateCategory);
        userText = (EditText) findViewById(R.id.txtCategoryName);

    }

    public void buttonsCreateCategoryOnClick(View v) {
        String name = userText.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            accessInventory.addCategory(name);
            categoryList.add(name);
            adapter.notifyDataSetChanged();
            userText.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Category created", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Error: Please enter a valid input", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}