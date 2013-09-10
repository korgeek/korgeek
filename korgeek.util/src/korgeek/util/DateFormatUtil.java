package korgeek.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatUtil {
	
	private static final String DEFALUT = "yyyyMMdd-HHmmss";
	private static final String STANDARD_DATE = "yyyy-MM-dd_HH:mm:ss";
	private static final String DEFALUT_DATE = "yyyyMMdd";

	public static String toDateTime(Date date) {
		return toFormat(date, DEFALUT);
	}
	
	public static String toDateTime(long date) {
		return toFormat(new Date(date), DEFALUT);
	}
	
	public static String toDateTime() {
		return toFormat( GregorianCalendar.getInstance().getTime() , DEFALUT);
	}
	
	public static String toStandardDateTime() {
		return toFormat( GregorianCalendar.getInstance().getTime() , STANDARD_DATE).replace('_', 'T');
	}
	
	public static Date fromDateTime(String datetime){
		try{
			return fromFormat(datetime, DEFALUT);
		}catch(Exception e){
			Debug.error(e);
		}
		return null;
	}

	public static String toDate(Date date) {
		return toFormat(date, DEFALUT_DATE);
	}
	
	public static String toDate(long date) {
		return toFormat(new Date(date), DEFALUT_DATE);
	}
	
	public static String toDate() {
		return toFormat( GregorianCalendar.getInstance().getTime(), DEFALUT_DATE);
	}
	
	public static Date fromDate(String datetime){
		try{
			return fromFormat(datetime, DEFALUT_DATE);
		}catch(Exception e){
			Debug.error(e);
		}
		return null;
	}	
	

	public static String toFormat(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format( date );
	}
	
	public static Date fromFormat(String date, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse( date );
	}

}
