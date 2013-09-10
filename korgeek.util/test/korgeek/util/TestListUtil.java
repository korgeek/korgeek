package korgeek.util;

import java.util.ArrayList;

import junit.framework.TestCase;
import korgeek.util.ext.LimitSizeList;

public class TestListUtil extends TestCase{

	public void testCreate() throws Exception {

		ArrayList list = new ArrayList();
		assertEquals(list, ListUtil.create());
		
		

	}
	
	public void testLimitSizeList() throws Exception {
		
		LimitSizeList<String> list = (LimitSizeList<String>) ListUtil.createLimitSize(10);
		
		for(int i=0; i < 100; i ++){
			list.add(i+"");
		}
		System.out.println(list);
		
	}
	
}
