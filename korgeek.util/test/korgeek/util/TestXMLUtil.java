package korgeek.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.TestCase;


public class TestXMLUtil extends TestCase{

	
	public void testToJson() throws Exception {
	
		String text = "<bulk_response><status_code>2200</status_code><status_text>Request has been successfully received</status_text><transaction_id>7eb039077e01eb1c8f5590f0b85583fd-819682-hwdqqb-01036</transaction_id></bulk_response>";
		JsonParser parser = new JsonParser();
		
	}
		
}




