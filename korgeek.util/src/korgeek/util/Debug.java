package korgeek.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;

public class Debug {

	public static void debug(Object ... objs) {
		out(System.out, getDebugMap(objs));
	}
	
	public static void error(Object ... objs) {
		out(System.err, getDebugMap(objs));		
	}
	
	public static Map getDebugMap(Object ... objs){
		Map<String, Object> map = getCallerInfo();
		map.put("desc", Arrays.deepToString(objs));
		return map;
	}
	
	private static void out(PrintStream out, Map map) {
		out.println(
			"[" + String.valueOf(map.get("DebugType")).toUpperCase() + 
			" " +DateFormatUtil.toStandardDateTime() + "]" +
			map.get("desc") + "\t" +
			"at " + map.get("ClassName") + "." + map.get("MethodName") + 
			" (" + map.get("FileName") + ":" + map.get("LineNumber") + ") "
			
		);
	}



	public static Map<String, Object> getCallerInfo(){

		StackTraceElement[] stackTraces = new Exception().getStackTrace() ;
		String thisName = Debug.class.getName();
		String debugtype = "";
		for (StackTraceElement st : stackTraces) {
			String className = st.getClassName();
			if (!thisName.equals(className)) {
				Map<String, Object> map = MapUtil.createCaseInsensitiveMap();
					map.put("ClassName", st.getClassName());
					map.put("MethodName", st.getMethodName());
					map.put("FileName", st.getFileName());
					map.put("LineNumber", st.getLineNumber());
					map.put("DebugType", debugtype);
					map.put("CurrentTime", System.currentTimeMillis());
				return map;
			}
			debugtype = st.getMethodName();
		}
		return MapUtil.create();
	}




}

