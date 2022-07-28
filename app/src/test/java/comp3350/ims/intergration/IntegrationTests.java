package comp3350.ims.intergration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class IntegrationTests extends TestCase {
    public static TestSuite suite;



    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}