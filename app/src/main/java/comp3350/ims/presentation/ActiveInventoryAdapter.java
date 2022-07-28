package comp3350.ims.presentation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;
import comp3350.ims.R;
import comp3350.ims.objects.Inventory;
import comp3350.ims.objects.ItemType;

public class ActiveInventoryAdapter extends BaseAdapter {
    private Inventory filteredInventory;
    private Inventory mainInventory;
    private static LayoutInflater inflater = null;

    public ActiveInventoryAdapter(Context context, Inventory inventory) {
        this.filteredInventory = inventory;
        this.mainInventory = inventory;
        this.filteredInventory.reorderByQuantity();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (vi == null) {
            vi = inflater.inflate(R.layout.inventory_row, null);
        }

        try {

            TextView itemName = (TextView) vi.findViewById(R.id.itemName);
            itemName.setText("Name: " + filteredInventory.getItem(position).getName());

            TextView categoryName = (TextView) vi.findViewById(R.id.categoryName);
            categoryName.setText("Category: " + filteredInventory.getItem(position).getCategory() + "");

            TextView itemQuantity = (TextView) vi.findViewById(R.id.itemQuantity);
            itemQuantity.setText("Quantity: " + filteredInventory.getItem(position).getQuantity() + "");

            if (filteredInventory.getItem(position).needsRefill()) {
                itemQuantity.setTextColor(Color.parseColor("RED"));
            }

            TextView itemPrice = (TextView) vi.findViewById(R.id.itemPrice);
            itemPrice.setText("Price: $" + filteredInventory.getItem(position).getPrice() + "");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        return vi;
    }

    @Override
    public int getCount() {
        return filteredInventory.items.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredInventory.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            Inventory tempInventory = new Inventory();

            if (constraint == null || constraint.length() == 0) {
                results.count = mainInventory.getNumOfItems();
                results.values = mainInventory;
            } else {
                ItemType item;
                for (int i = 0; i < mainInventory.getNumOfItems(); i++) {
                    item = mainInventory.items.get(i);
                    if (((item.getName()).toLowerCase()).startsWith((constraint.toString()).toLowerCase())) {
                        tempInventory.addItem(item);
                    }
                }
                results.count = tempInventory.items.size();
                results.values = tempInventory;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count > 0) {
                filteredInventory = (Inventory) results.values;
                mainInventory.setFilteredItems(filteredInventory.items);
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    };

    public Filter getFilter() {
        return myFilter;
    }
}