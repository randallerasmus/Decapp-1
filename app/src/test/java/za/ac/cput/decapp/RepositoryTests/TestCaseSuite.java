package za.ac.cput.decapp.RepositoryTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



// this is the test suite to create tests
@RunWith(Suite.class)
@Suite.SuiteClasses({
//        CaseRepositoryTests.class,
//        CommentsRepositoryTest.class,
//        SuspectRepositoryTest.class,
//        TransferRepositoryTest.class,
//        VictimRepositoryTest.class,
        UserRepositoryTest.class,

})

public class TestCaseSuite {
    @Test
    public void testThis() {
        Assert.assertTrue(true);
    }

}
