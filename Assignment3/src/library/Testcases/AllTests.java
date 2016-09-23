package library.Testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
	TestControl testBorrowUI=new TestControl();
	TestControlDaos testDeaos=new TestControlDaos();
}
