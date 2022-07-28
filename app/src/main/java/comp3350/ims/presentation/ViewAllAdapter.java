package comp3350.ims.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import comp3350.ims.R;
import comp3350.ims.objects.ItemType;

public class ViewAllAdapter extends BaseAdapter {
    private Context context;
    private ItemType item;
    private static LayoutInflater inflater = null;

    public ViewAllAdapter(Context context, ItemType item) {
        this.context = context;
        this.item = item;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (vi == null) {
            vi = inflater.inflate(R.layout.view_all_row, null);
        }

        TextView itemName = (TextView) vi.findViewById(R.id.itemName1);
        itemName.setText("Name: " + item.getName());

        TextView itemDate = (TextView) vi.findViewById(R.id.itemDate);
        itemDate.setText("Date: " + item.getItem(position).getDate() + "");

        TextView itemId = (TextView) vi.findViewById(R.id.itemId);
        itemId.setText("ID: " + item.getItem(position).getItemId() + "");

        TextView itemLocation = (TextView) vi.findViewById(R.id.itemLocation);
        itemLocation.setText("Location: " + item.getItem(position).getLocation() + "");

        return vi;
    }

    @Override
    public int getCount() {
        return item.getSize();
    }

    @Override
    public Object getItem(int position) {
        return item.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}