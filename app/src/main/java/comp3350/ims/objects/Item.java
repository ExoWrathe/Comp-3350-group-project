package comp3350.ims.objects;

public class Item {

    private int id;
    private String location;
    private String date;
    public static int ItemID;

    public Item() {
        location = "";
        date = "";
        id = ItemID++;
    }

    public Item(String location, String date) {
        this.date = date;
        this.location = location;
        id = ItemID++;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
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