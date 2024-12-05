package comp3350.ims.intergration;

import junit.framework.TestCase;

import java.sql.SQLException;

import comp3350.ims.application.Main;
import comp3350.ims.application.Services;

import comp3350.ims.persistence.DataAccess;
import comp3350.ims.persistence.DataAccessTest;


public class DataAccessHSQLDBTest extends TestCase
{
	private static String dbName = Main.dbName;
	
	public DataAccessHSQLDBTest(String arg0)
	{
		super(arg0);
	}

	public void testDataAccess(){
		DataAccess dataAccess;
		
		Services.closeDataAccess();

		System.out.println("\nStarting Integration test DataAccess (using default DB)");

		Services.createDataAccess(dbName);
		Services.setAutoCommitOff();
		dataAccess = Services.getDataAccess(dbName);
		DataAccessTest.dataAccessTestSimple(dataAccess);
		Services.closeDataAccess();

		Services.createDataAccess(dbName);
		Services.setAutoCommitOff();
		dataAccess = Services.getDataAccess(dbName);
		DataAccessTest.dataAccessTestTypical(dataAccess);
		Services.closeDataAccess();

		Services.createDataAccess(dbName);
		Services.setAutoCommitOff();
		dataAccess = Services.getDataAccess(dbName);
		DataAccessTest.dataAccessTestInvalid(dataAccess);
		Services.closeDataAccess();

		Services.createDataAccess(dbName);
		Services.setAutoCommitOff();
		dataAccess = Services.getDataAccess(dbName);
		DataAccessTest.dataAccessTestEdge(dataAccess);
		Services.closeDataAccess();

		System.out.println("Finished Integration test DataAccess (using default DB)");
	}
}