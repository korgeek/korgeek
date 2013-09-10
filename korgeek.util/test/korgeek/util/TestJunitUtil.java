package korgeek.util;

import junit.framework.TestCase;

public class TestJunitUtil extends TestCase{

	
	public void testIsTestCase() throws Exception {
		
		assertTrue( JunitUtil.isTestCase() );
		
	}
	
	public void testGetAradonRoot() throws Exception {
		
		Debug.debug( JunitUtil.getAradonRoot().getAbsolutePath() );
		
	}
	
}
