package comp3350.ims.business;

import junit.framework.TestCase;

import comp3350.ims.application.Main;
import comp3350.ims.application.Services;
import comp3350.ims.objects.Item;
import comp3350.ims.objects.ItemType;

public class AccessInventoryTest extends TestCase {


    public void testCategoryCases(){
        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();

        AccessInventory test = new AccessInventory();

        //Testing basic adding and removing of categories
        assertFalse(test.isCategory("test123"));
        test.addCategory("test123");
        assertTrue(test.isCategory("test123"));

        assertTrue(test.removeCategory("test123"));
        assertFalse(test.isCategory("test123"));

        Services.closeDataAccess();
    }

    public void testLocationCases(){

        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();

        AccessInventory test = new AccessInventory();

        test.addLocation("test123");
        assertTrue(test.isLocation("test123"));

        assertTrue(test.isLocation("test123"));

        assertTrue(test.removeLocation("test123"));
        assertFalse(test.isLocation("test123"));

        Services.closeDataAccess();
    }

    public void testItemCases(){
        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();

        AccessInventory test = new AccessInventory();
        ItemType testType = new ItemType("Test",3f,"test","test","test");

        int prevSize = test.getActiveInventory().getNumOfItems();

        test.addItem("here","now",new ItemType());
        ItemType returned = test.getItem(test.getActiveInventory().getNumOfItems());
        assertTrue(returned.getSize() == prevSize+1);

        test.removeIndividualItem(prevSize+1);
        int removedSize = test.getActiveInventory().getNumOfItems();
        assertTrue(removedSize == prevSize);
        Services.closeDataAccess();
    }

    public void testItemTypeCases(){
        Services.createDataAccess(Main.dbName);
        Services.setAutoCommitOff();
        AccessInventory test = new AccessInventory();

        assertTrue(test.insertItemType("Test",3f,3,"test","test","test"));
        Services.closeDataAccess();
    }

}