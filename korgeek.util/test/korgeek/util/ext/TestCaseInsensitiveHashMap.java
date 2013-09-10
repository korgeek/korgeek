package korgeek.util.ext;

import junit.framework.TestCase;

public class TestCaseInsensitiveHashMap extends TestCase{
	public void testCreate() throws Exception {
		
		CaseInsensitiveHashMap<String> map = new CaseInsensitiveHashMap<String>();
		map.put("TeSt", "test");
		
		assertTrue(map.containsKey("tEsT"));
		assertEquals("test", map.get("test"));
	}
}
