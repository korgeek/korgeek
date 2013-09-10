package korgeek;

import junit.framework.Test;
import junit.framework.TestSuite;
import korgeek.aradon.common.let.TestDebugLet;
import korgeek.aradon.common.let.TestFaviconLet;

public class TestAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(TestAllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestDebugLet.class);
		suite.addTestSuite(TestFaviconLet.class);
		//$JUnit-END$
		return suite;
	}

}
