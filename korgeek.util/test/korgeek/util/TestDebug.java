package korgeek.util;

import junit.framework.TestCase;

public class TestDebug extends TestCase{

	
	public void testDebug() throws Exception {
		Debug.debug("Debug Test", 1L, 1);
		Debug.debug("Debug Test");		
	}
	
}
