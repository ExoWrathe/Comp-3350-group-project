package comp3350.ims.objects;

import junit.framework.TestCase;

public class InventoryTest extends TestCase {

    public void setUp() {
    }

    public void tearDown() {
    }

    public void testSimpleCases(){

        Inventory inventory = new Inventory();

        ItemType item = new ItemType("Coke 12 pk", 8f,  "Ware House", "12/06/2022", "Drinks");
        ItemType item1 = new ItemType("Pepsi 12 pk", 8f,  "Ware House", "12/06/2022", "Drinks");

        assertTrue(inventory.addItem(item));
        assertTrue(inventory.addItem(item1));
        //checking if they are actually added.
        assertTrue(inventory.items.get(0).equals(item));
        assertTrue(inventory.items.get(0).equals(item));
        assertEquals(inventory.getNumOfItems(), 2);

        assertTrue(inventory.removeItem(item));
        assertTrue(inventory.removeItem(item1));
        //checking if they are actually removed
        assertEquals(inventory.items.size(), 0);

        assertTrue(inventory.addItem(item));
        assertTrue(inventory.addItem(item1));

        assertTrue(inventory.getItemType(0).equals(item));
        assertTrue(inventory.getItemType(1).equals(item1));

        ItemType item2 = new ItemType("DrPepper 12 pk", 8f,  "Ware House", "12/06/2022", "Drinks");
        assertTrue(inventory.addItem(item2));
        //checking re-order, if the 0 quantity item is at top of list.
        inventory.reorderByQuantity();
        assertTrue(inventory.getItemType(0).equals(item2));

    }

    public void testTypicalCases(){

        Inventory inventory = new Inventory();

        ItemType item = new ItemType("Coke 12 pk", 8f, "Ware House", "12/06/2022", "Drinks");
        ItemType item1 = new ItemType("Pepsi 12 pk", 8f, "Ware House", "12/06/2022", "Drinks");
        ItemType item2 = new ItemType("Fanta 12 pk", 8f, "Ware House", "12/06/2022", "Drinks");
        ItemType item3 = new ItemType("IceTea 12 pk", 8f, "Ware House", "12/06/2022", "Drinks");
        ItemType item4 = new ItemType("DrPepper 12 pk", 8f, "Ware House", "12/06/2022", "Drinks");

        assertTrue(inventory.addItem(item));
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.addItem(item2));
        assertTrue(inventory.addItem(item3));
        assertTrue(inventory.addItem(item4));

        //duplicate
        assertFalse(inventory.addItem(item));
        assertFalse(inventory.addItem(item1));
        assertFalse(inventory.addItem(item2));
        assertFalse(inventory.addItem(item3));
        assertFalse(inventory.addItem(item4));

        assertEquals(inventory.getNumOfItems(), 5);

        assertTrue(inventory.removeItem(item));
        assertTrue(inventory.removeItem(item1));
        assertTrue(inventory.removeItem(item2));
        assertTrue(inventory.removeItem(item3));
        assertTrue(inventory.removeItem(item4));

        assertEquals(inventory.getNumOfItems(), 0);

        assertTrue(inventory.addItem(item));
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.addItem(item2));
        assertTrue(inventory.addItem(item3));
        assertTrue(inventory.addItem(item4));

        ItemType lowQuantityItem = new ItemType("Chair", 8f,  "Ware House", "12/06/2022", "Drinks");
        ItemType lowQuantityItem1 = new ItemType("Chocolate", 8f,  "Ware House", "12/06/2022", "Drinks");
        ItemType lowQuantityItem2 = new ItemType("Gum", 8f,  "Ware House", "12/06/2022", "Drinks");

        assertTrue(inventory.addItem(lowQuantityItem));
        assertTrue(inventory.addItem(lowQuantityItem1));
        assertTrue(inventory.addItem(lowQuantityItem2));
        //should not be re-order
        assertFalse(inventory.items.get(0).equals(lowQuantityItem));
        assertFalse(inventory.items.get(1).equals(lowQuantityItem1));
        assertFalse(inventory.items.get(2).equals(lowQuantityItem2));

        inventory.reorderByQuantity();

        assertTrue(inventory.items.get(0).equals(lowQuantityItem));
        assertTrue(inventory.items.get(1).equals(lowQuantityItem1));
        assertTrue(inventory.items.get(2).equals(lowQuantityItem2));

        assertEquals(inventory.getNumOfItems(), 8);

        Inventory test = new Inventory();
        ItemType testType = new ItemType();
        ItemType testType2 = new ItemType();

        //Set first item to not need a refill
        testType.setNeedsRefill(false);
        test.addItem(testType);
        test.addItem(testType2);

        test.reorderByQuantity();
        assertEquals(testType2,test.getItemType(0));
    }

    public void testEdgeCases(){

        Inventory inventory = new Inventory();

        assertEquals(inventory.getNumOfItems(), 0);
        ItemType item = new ItemType("Coke 12 pk", 8f,  "Ware House", "12/06/2022", "Drinks");
        assertFalse(inventory.removeItem(item));

        assertTrue(inventory.addItem(item));
        assertEquals(inventory.getNumOfItems(),1);

    }


    public void testInvalidCases(){

        Inventory inventory = new Inventory();

        assertFalse(inventory.addItem(null));
        assertFalse(inventory.removeItem(null));

        boolean thrown = false;

        try {
            inventory.getItemType(-1);
        } catch (IndexOutOfBoundsException e){
            thrown = true;
        }

        thrown = false;
        try {
            inventory.getItemType(100);
        } catch (IndexOutOfBoundsException e){
            thrown = true;
        }

        assertTrue(thrown);
    }

    public void testSortingBaseCases(){
        Inventory test = new Inventory();

        ItemType milk =  new ItemType("Milk",1.1f,"Here","","");
        test.addItem(milk);
        ItemType cereal =  new ItemType("Cereal",1.2f,"Here","","");
        test.addItem(cereal);
        ItemType cookie =  new ItemType("Cookie",5.1f,"Here","","");
        test.addItem(cookie);

        test.sortByPrice();
        assertEquals(milk,test.getItemType(0));
        assertEquals(cookie,test.getItemType(test.getNumOfItems()-1));

        test.reverseSortByPrice();
        assertEquals(cookie,test.getItemType(0));
        assertEquals(milk,test.getItemType(test.getNumOfItems()-1));

        test.sortByName();
        assertEquals(cereal,test.getItemType(0));
        assertEquals(milk,test.getItemType(test.getNumOfItems()-1));

        test.reverseSortByName();
        assertEquals(milk,test.getItemType(0));
        assertEquals(cereal,test.getItemType(test.getNumOfItems()-1));
    }

    public void testInvalidSortCases(){
        Inventory test = new Inventory();
        test.addItem(null);

        boolean passed = false;
        //Sorting empty array
        test.sortByName();
        try{
            test.getItemType(0);
        }
        catch(IndexOutOfBoundsException e){
            passed = true;
        }
        assertTrue(passed);

        passed = false;
        test.reverseSortByName();
        try{
            test.getItemType(0);
        }
        catch(IndexOutOfBoundsException e){
            passed = true;
        }
        assertTrue(passed);

        passed = false;
        test.sortByPrice();
        try{
            test.getItemType(0);
        }
        catch(IndexOutOfBoundsException e){
            passed = true;
        }
        assertTrue(passed);

        passed = false;
        test.reverseSortByPrice();
        try{
            test.getItemType(0);
        }
        catch(IndexOutOfBoundsException e){
            passed = true;
        }
        assertTrue(passed);
    }

    public void testSortingEqualCases(){
        Inventory test = new Inventory();

        ItemType milk1 = new ItemType("Milk",2,"","","");
        test.addItem(milk1);
        ItemType milk2 = new ItemType("Milk",2,"","","");
        test.addItem(milk2);
        ItemType milk3 = new ItemType("Milk",2,"","","");
        test.addItem(milk3);

        test.sortByName();
        assertEquals(milk1,test.getItemType(0));
        assertEquals(milk3,test.getItemType(test.getNumOfItems()-1));

        test.reverseSortByName();
        assertEquals(milk3,test.getItemType(0));
        assertEquals(milk1,test.getItemType(test.getNumOfItems()-1));

        test.sortByPrice();
        assertEquals(milk1,test.getItemType(0));
        assertEquals(milk3,test.getItemType(test.getNumOfItems()-1));

        test.reverseSortByPrice();
        assertEquals(milk3,test.getItemType(0));
        assertEquals(milk1,test.getItemType(test.getNumOfItems()-1));
    }
}