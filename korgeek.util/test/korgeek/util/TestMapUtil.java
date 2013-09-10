package korgeek.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class TestMapUtil extends TestCase{

	public void testCreate() throws Exception {
		
		Map map = new HashMap();
		assertEquals(map, MapUtil.create());
		
		map.put("test", "test");
		assertEquals(map, MapUtil.create("test", "test"));
		
		Map caseInsensitiveMap = MapUtil.createCaseInsensitiveMap();
				caseInsensitiveMap.put("TesT", "test");
		assertEquals(map, caseInsensitiveMap);

	}
	
}
