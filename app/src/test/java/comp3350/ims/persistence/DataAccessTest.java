package comp3350.ims.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.ims.objects.Inventory;
import comp3350.ims.objects.Item;
import comp3350.ims.objects.ItemType;


public class DataAccessTest extends TestCase {

    private DataAccess dataAccess;

    public DataAccessTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");
    }

    public static void dataAccessTestSimple(DataAccess dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;
        dataAccessTest.testSimpleCases();
    }

    public static void dataAccessTestTypical(DataAccess dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;
        dataAccessTest.testTypicalCases();
    }

    public static void dataAccessTestEdge(DataAccess dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;
        dataAccessTest.testEdgeCases();
    }

    public static void dataAccessTestInvalid(DataAccess dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;
        dataAccessTest.testInvalidCases();
    }

    public void tearDown() {
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public void testSimpleCases() {
        Inventory inventory;
        ArrayList < String > categoryList = new ArrayList < String > ();
        ArrayList < String > locationList = new ArrayList < String > ();

        inventory = dataAccess.getActiveInventory();
        dataAccess.getCategoryList(categoryList);
        dataAccess.getLocationList(locationList);
        assertEquals(inventory.getNumOfItems(), 0);
        assertEquals(categoryList.size(), 0);
        assertEquals(locationList.size(), 0);

        //Item Type Tests
        ItemType milk = new ItemType("Milk", 5, "Warehouse", "7/27/2022", "Dairy");
        dataAccess.insertItem(milk);
        inventory = dataAccess.getActiveInventory();
        assertTrue(inventory.contains(milk));

        dataAccess.editItemType(milk, "Chocolate Milk", milk.getPrice(), milk.getCategory());
        inventory = dataAccess.getActiveInventory();
        assertEquals("Chocolate Milk", milk.getName());

        //Category Tests
        dataAccess.addCategory("Dairy");
        categoryList.clear();
        dataAccess.getCategoryList(categoryList);
        assertTrue(categoryList.contains("Dairy"));

        dataAccess.removeCategory("Dairy");
        categoryList.clear();
        dataAccess.getCategoryList(categoryList);
        assertFalse(categoryList.contains("Dairy"));

        //Location Tests
        dataAccess.addLocation("Warehouse");
        locationList.clear();
        dataAccess.getLocationList(locationList);
        assertTrue(locationList.contains("Warehouse"));

        dataAccess.removeLocation("Warehouse");
        locationList.clear();
        dataAccess.getLocationList(locationList);
        assertFalse(locationList.contains("Warehouse"));

        //Item Tests
        Item firstMilk = new Item(milk.getLocation(), milk.getDate());
        milk.addItem(firstMilk);
        dataAccess.addItem(firstMilk, milk.getID());
        inventory = dataAccess.getActiveInventory();
        assertEquals(firstMilk.getId(), inventory.getItem(0).getItem(0).getId());

        dataAccess.editItem(firstMilk, "Warehouse2");
        inventory = dataAccess.getActiveInventory();
        assertEquals("Warehouse2", inventory.getItem(0).getItem(0).getLocation());

        dataAccess.removeItem(firstMilk.getId(), milk.getID(), milk.getQuantity());
        milk.removeItem(0);
        inventory = dataAccess.getActiveInventory();
        assertNull(inventory.getItem(0).getItem(0));
    }

    public void testTypicalCases() {
        Inventory inventory;
        ArrayList < String > categoryList = new ArrayList < String > ();
        ArrayList < String > locationList = new ArrayList < String > ();

        dataAccess.addCategory("Produce");
        dataAccess.addLocation("Warehouse");
        dataAccess.addLocation("Store");
        ItemType itemType = new ItemType("Banana", 0.25f, "Warehouse", "7/27/2022", "Produce");
        dataAccess.insertItem(itemType);
        for (int i = 0; i < 5; i++) {
            Item item = new Item("Warehouse", "7/27/2022");
            itemType.addItem(item);
            dataAccess.addItem(item, itemType.getID());
        }
        inventory = dataAccess.getActiveInventory();
        dataAccess.getLocationList(locationList);
        dataAccess.getCategoryList(categoryList);
        itemType.getItem(0).setLocation("Store");
        dataAccess.editItem(inventory.getItem(0).getItem(0), "Store");
        itemType.removeItem(1);
        dataAccess.removeItem(inventory.getItem(0).getItem(1).getId(), inventory.getItem(0).getID(), inventory.getItem(0).getQuantity());

        categoryList.clear();
        locationList.clear();
        dataAccess.getCategoryList(categoryList);
        dataAccess.getLocationList(locationList);
        inventory = dataAccess.getActiveInventory();
        assertEquals(1, inventory.getNumOfItems());
        assertEquals(4, inventory.getItem(0).getQuantity());
        assertEquals("Store", inventory.getItem(0).getItem(0).getLocation());
        assertEquals(1, categoryList.size());
        assertEquals(2, locationList.size());
    }

    public void testEdgeCases() {
        Inventory inventory;
        ArrayList < String > categoryList = new ArrayList < String > ();
        ArrayList < String > locationList = new ArrayList < String > ();

        //Empty Tests
        dataAccess.getCategoryList(categoryList);
        dataAccess.getLocationList(locationList);
        inventory = dataAccess.getActiveInventory();

        assertEquals(0, inventory.getNumOfItems());
        assertEquals(0, categoryList.size());
        assertEquals(0, locationList.size());

        //Single Value
        dataAccess.addLocation("1");
        dataAccess.addCategory("1");
        ItemType itemType = new ItemType("Banana", 0.25f, "Warehouse", "7/27/2022", "Produce");
        dataAccess.insertItem(itemType);

        categoryList.clear();
        locationList.clear();
        dataAccess.getCategoryList(categoryList);
        dataAccess.getLocationList(locationList);
        inventory = dataAccess.getActiveInventory();

        assertEquals(0, inventory.getItem(0).getQuantity());
        assertEquals(1, inventory.getNumOfItems());
        assertEquals(1, categoryList.size());
        assertEquals(1, locationList.size());

        Item item = new Item(itemType.getLocation(), itemType.getDate());
        itemType.addItem(item);
        dataAccess.addItem(item, itemType.getID());

        inventory = dataAccess.getActiveInventory();

        assertEquals(1, inventory.getItem(0).getQuantity());

        //After Removing
        itemType.removeItem(0);
        dataAccess.removeItem(item.getId(), itemType.getID(), itemType.getQuantity());
        dataAccess.removeCategory("1");
        dataAccess.removeLocation("1");

        categoryList.clear();
        locationList.clear();
        dataAccess.getCategoryList(categoryList);
        dataAccess.getLocationList(locationList);
        inventory = dataAccess.getActiveInventory();

        assertEquals(0, inventory.getItem(0).getQuantity());
        assertEquals(0, categoryList.size());
        assertEquals(0, locationList.size());
    }

    public void testInvalidCases() {
        Inventory inventory;
        ArrayList < String > categoryList = new ArrayList < String > ();
        ArrayList < String > locationList = new ArrayList < String > ();

        dataAccess.addItem(null, -1);
        dataAccess.insertItem(null);
        dataAccess.addCategory(null);
        dataAccess.addLocation(null);
        dataAccess.removeLocation(null);
        dataAccess.removeCategory(null);
        dataAccess.removeItem(-4, -1, -4);

        dataAccess.getLocationList(locationList);
        dataAccess.getCategoryList(categoryList);
        inventory = dataAccess.getActiveInventory();
        assertEquals(inventory.getNumOfItems(), 0);
        assertEquals(locationList.size(), 0);
        assertEquals(categoryList.size(), 0);

        assertFalse(locationList.contains(null));
        assertFalse(categoryList.contains(null));

        //Now we run typical cases to ensure that the app recovered correctly from the invalid cases
        testTypicalCases();
    }

}