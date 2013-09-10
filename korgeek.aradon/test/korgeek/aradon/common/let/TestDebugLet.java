package korgeek.aradon.common.let;

import korgeek.aradon.common.AbstractTestServer;
import net.ion.framework.parse.gson.JsonObject;
import net.ion.framework.util.Debug;
import net.ion.radon.client.AradonClientFactory;
import net.ion.radon.client.IAradonRequest;
import net.ion.radon.core.config.Configuration;
import net.ion.radon.core.config.ConfigurationBuilder;
import net.ion.radon.core.config.PathConfigurationBuilder;

import org.restlet.data.Method;

public class TestDebugLet extends AbstractTestServer{

	@Override
	protected Configuration getConfig() {
		PathConfigurationBuilder path = ConfigurationBuilder.newBuilder().aradon().sections().restSection("debug").path("debug");
		
		return path.addUrlPattern("/debug").handler(DebugLet.class).build();
	}
	
	public void testDebugGet() throws Exception {
		
		IAradonRequest request = AradonClientFactory.create("http://localhost:58585/").createRequest("/debug/debug?test=1234");
			request.addHeader("testheader", "testheadervalue");
		String text = request.get().getText();
		JsonObject json = JsonObject.fromString( text );
		
		assertEquals("1234", getNodeJsonObject(json, "form").asString("test"));
		assertEquals("testheadervalue", getNodeJsonObject(json, "headers").asString("testheader"));
		
	}

	public void testDebugPost() throws Exception {
		
		IAradonRequest request = AradonClientFactory.create("http://localhost:58585/").createRequest("/debug/debug");
			request.addHeader("testheader", "testheadervalue");
			request.addParameter("testparam", "testparamValue");
		String text = request.post().getText();
		JsonObject json = JsonObject.fromString( text );
		

		assertEquals("testparamValue", getNodeJsonObject(json, "form").asString("testparam"));
		assertEquals("testheadervalue", getNodeJsonObject(json, "headers").asString("testheader"));
		

	}

	
	
	private String getHeaderStringValue(JsonObject json, String name){
		return json.asJsonObject("result").asJsonArray("nodes").get(0).getAsJsonObject().getAsJsonObject("headers").asString(name);
	}
	
	private String getParameterStringValue(JsonObject json, String name){
		return json.asJsonObject("result").asJsonArray("nodes").get(0).getAsJsonObject().getAsJsonObject("form").asString(name);
	}
	
	private JsonObject getFormStringValue(JsonObject json){
		return json.asJsonObject("result").asJsonArray("nodes").get(0).getAsJsonObject().getAsJsonObject("aradon");
	}
	
}
