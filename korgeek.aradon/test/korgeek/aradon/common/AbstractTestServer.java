package korgeek.aradon.common;

import junit.framework.TestCase;
import korgeek.aradon.common.let.FaviconLet;
import net.ion.framework.parse.gson.JsonArray;
import net.ion.framework.parse.gson.JsonElement;
import net.ion.framework.parse.gson.JsonObject;
import net.ion.nradon.HttpHandler;
import net.ion.nradon.Radon;
import net.ion.radon.core.Aradon;
import net.ion.radon.core.config.Configuration;
import net.ion.radon.core.config.ConfigurationBuilder;

public abstract class AbstractTestServer extends TestCase{

	protected Radon radon = null;
	
	protected abstract Configuration getConfig();
	
	protected void setUp() throws Exception {
		setAradon(getConfig());
	}
	
	protected void setAradon(Configuration conf) throws Exception {
		tearDown();	
		this.radon = Aradon.create(conf).toRadon(58585).start().get();
	}
	
	protected void tearDown() throws Exception {
		if (radon != null)
			radon.stop().get();
	}
	
	protected JsonArray getNodeJsonArray(JsonObject json){
		return json.asJsonObject("result").asJsonArray("nodes");
	}	
	
	protected JsonObject getNodeJsonObject(JsonObject json){
		return json.asJsonObject("result").asJsonArray("nodes").get(0).getAsJsonObject();
	}
	
	protected JsonElement getNodeJsonElement(JsonObject json, String memberName){
		return json.asJsonObject("result").asJsonArray("nodes").get(0).getAsJsonObject().get(memberName);
	}
	
}
