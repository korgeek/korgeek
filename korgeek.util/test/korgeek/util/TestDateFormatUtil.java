package korgeek.util;

import java.util.Date;

import junit.framework.TestCase;

public class TestDateFormatUtil extends TestCase{

	public void testToFormatAndDate() throws Exception {
		Date date = new Date();
		String format = "yyyyMMdd-HHmmss";
		long conconvert = DateFormatUtil.fromDateTime(DateFormatUtil.toFormat(date, format)).getTime();
		
		Debug.debug(
			DateFormatUtil.toFormat(date, format), 
			DateFormatUtil.fromDateTime(DateFormatUtil.toFormat(date, format))
		);
		assertEquals(conconvert, (date.getTime()/1000)*1000);
		
		assertEquals(
			DateFormatUtil.toDateTime(date), 
			DateFormatUtil.toDateTime(date.getTime())
		);
	}
	
	public void testSimpleTo() throws Exception {
		Date date = new Date();
		long conconvert = DateFormatUtil.fromDateTime(DateFormatUtil.toDateTime(date)).getTime();
		assertEquals(conconvert, (date.getTime()/1000)*1000);
	}
	
}
