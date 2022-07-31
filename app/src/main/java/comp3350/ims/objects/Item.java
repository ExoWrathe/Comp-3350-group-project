package comp3350.ims.objects;

public class Item {

    private int itemId;
    private String location;
    private String date;
    public static int ItemID;

    public Item() {
        location = "";
        date = "";
        itemId = ItemID++;
    }

    public Item(String location, String date) {
        this.date = date;
        this.location = location;
        itemId = ItemID++;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int i) {
        this.itemId = i;
        if (i > ItemID) {
            ItemID = i + 1;
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

}