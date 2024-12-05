package comp3350.ims;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import comp3350.ims.intergration.BusinessPersistenceSeamTest;
import comp3350.ims.intergration.DataAccessHSQLDBTest;


public class RunIntegrationTests extends TestCase {
    public static TestSuite suite;



    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}