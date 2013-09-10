package korgeek.util.ext;

import junit.framework.TestCase;

public class TestLimitSizeList extends TestCase{

	public void testCreate() throws Exception {
		
		LimitSizeList<Integer> list = LimitSizeList.create(10);
		for(int i = 0; i < 100; i++){
			list.add(i);
		}
		assertEquals(10, list.size());
	}
	
}
