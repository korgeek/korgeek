package korgeek.aradon.common.let;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.AttributedString;

import org.restlet.data.Method;

import net.ion.framework.util.Debug;
import net.ion.framework.util.IOUtil;
import net.ion.radon.client.AradonClientFactory;
import net.ion.radon.core.config.AttributeValue;
import net.ion.radon.core.config.Configuration;
import net.ion.radon.core.config.ConfigurationBuilder;
import net.ion.radon.core.config.PathConfigurationBuilder;
import net.ion.radon.core.config.SectionsConfigurationBuilder;
import korgeek.aradon.common.AbstractTestServer;

public class TestFaviconLet extends AbstractTestServer{

	@Override
	protected Configuration getConfig() {
		return ConfigurationBuilder.newBuilder().build();
	}
	
	public void testFavicon() throws Exception {
		
		this.setAradon(
				ConfigurationBuilder.newBuilder().aradon().sections().restSection("").path("")
				.addUrlPattern("/favicon.ico").handler(FaviconLet.class).build()
				);
		
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.DELETE).getEntity();
		
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		
		InputStream forOriginal = FaviconLet.class.getResourceAsStream("res/favicon.ico");
		InputStream forResult = AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET).getEntity().getStream();

		byte[] original = IOUtil.toByteArray(forOriginal, forOriginal.available());
		byte[] result = IOUtil.toByteArray(forResult, forResult.available());
		
		assertEquals(byteArrayToHex(original), byteArrayToHex(result));

	}

	public void testFaviconAttribute() throws Exception {
		
		this.setAradon(ConfigurationBuilder.newBuilder().aradon().sections().restSection("").path("")
				.addAttribute("favicon.ico", "res/favicon.ico")
				.addUrlPattern("/favicon.ico")
				.handler(FaviconLet.class).build());
		
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.DELETE).getEntity();

		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET);
		
		InputStream forOriginal = new FileInputStream(new File("res/favicon.ico"));
		InputStream forResult = AradonClientFactory.create("http://localhost:58585/").createRequest("/favicon.ico").handle(Method.GET).getEntity().getStream();

		byte[] original = IOUtil.toByteArray(forOriginal, forOriginal.available());
		byte[] result = IOUtil.toByteArray(forResult, forResult.available());
		
		assertEquals(byteArrayToHex(original), byteArrayToHex(result));

	}	
	
	private String byteArrayToHex(byte[] ba) {
	    if (ba == null || ba.length == 0) {
	        return null;
	    }
	 
	    StringBuffer sb = new StringBuffer(ba.length * 2);
	    String hexNumber;
	    for (int x = 0; x < ba.length; x++) {
	        hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
	 
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }
	    return sb.toString();
	} 
}
