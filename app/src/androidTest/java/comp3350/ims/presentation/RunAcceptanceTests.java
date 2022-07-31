package comp3350.ims.presentation;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;


@RunWith(Suite.class)
@Suite.SuiteClasses({AdministersAccessTest.class,CreateLocationTest.class,CreateCategoryTest.class,ItemCreateTest.class,InventoryTest.class})
public class RunAcceptanceTests
{
    public RunAcceptanceTests()
    {
        System.out.println("Acceptance tests");
    }
}
