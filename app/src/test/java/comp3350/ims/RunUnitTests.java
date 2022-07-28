package comp3350.ims;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import comp3350.ims.objects.InventoryTest;
import comp3350.ims.objects.ItemTest;
import comp3350.ims.objects.ItemTypeTest;
import comp3350.ims.persistence.DataAccessTest;

public class RunUnitTests extends TestCase
{
    public static TestSuite suite;

    public static Test suite()
    {
        System.out.println("Launching Test Suite.");
        suite = new TestSuite("All tests");
        tObjects();
        return suite;
    }

    private static void tObjects()
    {
        suite.addTestSuite(InventoryTest.class);
        suite.addTestSuite(ItemTest.class);
        suite.addTestSuite(ItemTypeTest.class);
        suite.addTestSuite(DataAccessTest.class);;
    }
}
