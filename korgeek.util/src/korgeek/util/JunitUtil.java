package korgeek.util;

import java.io.File;
import java.util.Map;

public class JunitUtil {

	private static boolean init = false;
	private static boolean testcase = false;
	
	public static boolean isTestCase(){
		
		
		if(!init){
			StackTraceElement[] stackTraces = new Exception().getStackTrace() ;
			
			String debugtype = "";
			for (StackTraceElement st : stackTraces) {
				
				if(st.getClassName().equalsIgnoreCase("junit.framework.TestCase")){
					testcase = true;
				}
			}
			init = true;
		}
		
		return testcase;
		
	}

	public static File getAradonRoot() {
		
		if(isTestCase()){
			return new File("./");
		}else{
			return new File("../");
		}
		
	}
	
}
