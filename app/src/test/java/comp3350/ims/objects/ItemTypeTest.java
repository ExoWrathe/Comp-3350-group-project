package comp3350.ims.objects;

import junit.framework.TestCase;

public class ItemTypeTest extends TestCase {

    public void setUp() {
    }

    public void tearDown() {
    }

    public void testSimpleCases(){

        ItemType item = new ItemType("Oreo", (float) 3.5, "Warehouse","12/03/2022","Ambient");

        assertEquals(item.getQuantity(),0);
        assertEquals(item.getDate(),"12/03/2022");
        assertEquals(item.getLocation(),"Warehouse" );
        assertEquals(item.getPrice(),(float) 3.5);
        assertEquals(item.getName(), "Oreo");

        assertTrue(item.needsRefill());

        item.removeItem(0);
        item.removeItem(0);
        item.removeItem(0);
        item.removeItem(0);
        item.removeItem(0);
        item.removeItem(0);

        assertTrue(item.needsRefill());

        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));

        ItemType test1 = new ItemType("Milk", 5.55f,  "Ware House", "12/06/2022", "Dairy");
        ItemType test2 = new ItemType("Milk", 2.23f, "Ware House", "12/06/2022", "Dairy");
        assertEquals(test1.equals(test2), true);

        ItemType test3 = new ItemType("Cheese", 5.55f,  "Ware House", "12/06/2022", "Dairy");
        ItemType test4 = new ItemType("Butter", 2.23f,  "Ware House", "12/06/2022", "Dairy");
        assertFalse(test3.equals(test4));

        ItemType test5 = new ItemType("Milk", 5.55f, "Ware House", "12/06/2022", "Dairy");
        ItemType test6 = new ItemType("Milk", 5.55f,  "Ware House", "12/06/2022", "Dairy");
        assertEquals(test5.equals(test6), true);

    }

    public void testInvalidCases(){

        ItemType item = new ItemType("Oreo", (float) 3.5, "Warehouse","12/03/2022","Ambient");
        assertEquals(item.getQuantity(),0);
        assertTrue(item.needsRefill());

        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));
        item.addItem(new Item("ware house", "12/06/2022"));

        assertEquals(item.getQuantity(),3);

        item.removeItem(-10);
        item.removeItem(23);
        assertEquals(item.getQuantity(),3);

    }


    public void testQuantity(){
        ItemType test1 = new ItemType();

        assertEquals(test1.getQuantity(), 0);

        test1.addItem(new Item("ware house", "12/06/2022"));
        test1.addItem(new Item("ware house", "12/06/2022"));
        test1.addItem(new Item("ware house", "12/06/2022"));
        test1.addItem(new Item("ware house", "12/06/2022"));
        test1.addItem(new Item("ware house", "12/06/2022"));
        test1.addItem(new Item("ware house", "12/06/2022"));
        test1.addItem(new Item("ware house", "12/06/2022"));

        assertEquals(test1.getQuantity(), 7);

        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);

        assertEquals(test1.getQuantity(),0);
        test1.addItem(new Item("ware house", "12/06/2022"));

        //removing after from empty list.
        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);
        test1.removeItem(0);
        assertEquals(test1.getQuantity(),0);

    }


}