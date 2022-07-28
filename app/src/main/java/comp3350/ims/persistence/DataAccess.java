package comp3350.ims.persistence;
import java.util.ArrayList;
import comp3350.ims.objects.Inventory;
import comp3350.ims.objects.Item;
import comp3350.ims.objects.ItemType;

public interface DataAccess {
    void open(String string);

    void close();

    public void addItem(Item item, int itemTypeID);

    public Inventory getActiveInventory();

    public void insertItem(ItemType item);

    public String getCategoryList(ArrayList < String > categoryList);

    public String getLocationList(ArrayList < String > locationList);

    public void addCategory(String category);

    public void addLocation(String location);

    public boolean removeLocation(String name);

    public boolean removeCategory(String name);

    public boolean isCategory(String name);

    public boolean isLocation(String name);

    public boolean removeItem(int itemID,int itemTypeID, int quantity);

    public boolean editItemType(ItemType itemType,String name,float price,String category);

    public boolean editItem(Item item,String location);
}
