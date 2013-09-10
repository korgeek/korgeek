package korgeek;

import junit.framework.Test;
import junit.framework.TestSuite;
import korgeek.util.TestCodeUtil;
import korgeek.util.TestDateFormatUtil;
import korgeek.util.TestDebug;
import korgeek.util.TestGeoUtil;
import korgeek.util.TestJunitUtil;
import korgeek.util.TestListUtil;
import korgeek.util.TestMapUtil;

public class TestAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(TestAllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestCodeUtil.class);
		suite.addTestSuite(TestDateFormatUtil.class);
		suite.addTestSuite(TestDebug.class);
		suite.addTestSuite(TestGeoUtil.class);
		suite.addTestSuite(TestJunitUtil.class);
		suite.addTestSuite(TestListUtil.class);
		suite.addTestSuite(TestMapUtil.class);
		//$JUnit-END$
		return suite;
	}

}
