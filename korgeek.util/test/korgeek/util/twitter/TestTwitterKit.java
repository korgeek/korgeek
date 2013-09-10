package korgeek.util.twitter;

import java.io.File;

import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONObject;

import junit.framework.TestCase;
import korgeek.util.twitter.TwitterKit;

public class TestTwitterKit extends TestCase{

	
	private static final String consumerKey = "py1iWCJcWbExDvmB1q0aQw";
	private static final String consumerSecret = "S97oYmlkllr2G1WkFEAT8PjrX5dlbLqPOvllSz95Cs";
	
	private static final String token = "1396710012-oEDisC8Qzd7NTWlTjnwPgqigzE0RWu8ewUgPpSa";
	private static final String tokenSecret = "kNPP4giVvSUWSxBnZaNzVDYEIjN8luoRKtZCQMm7M";

	
	public void testGetMentions() throws Exception{
		TwitterKit kit = TwitterKit.create(consumerKey, consumerSecret, token, tokenSecret);
		JSONArray array = kit.getMetions();
		assertNotNull(array.getJSONObject(0).getLong("id"));
		System.out.println(array.getJSONObject(0));
	}
	
	public void testPostTwitter() throws Exception{
		
		TwitterKit kit = TwitterKit.create(consumerKey, consumerSecret, token, tokenSecret);
		
		JSONObject post = kit.post("Test", new File("./res/tmp.png"));
		assertNotNull(post.getLong("id"));
		System.out.println(post);
		
		JSONObject delete = kit.delete(post.getLong("id"));
		assertNotNull(delete.getLong("id"));
		System.out.println(delete);
		
		assertEquals(post.getLong("id"), delete.getLong("id"));
	}
	
	
	
}
