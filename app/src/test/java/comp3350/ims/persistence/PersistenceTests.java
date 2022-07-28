package comp3350.ims.persistence;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PersistenceTests extends TestCase {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(DataAccessTest.class);
        return suite;
    }
}
