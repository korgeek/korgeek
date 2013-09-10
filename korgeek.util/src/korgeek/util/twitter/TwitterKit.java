package korgeek.util.twitter;

import java.io.File;
import java.util.List;

import korgeek.util.GeoUtil;

import org.apache.commons.lang.StringUtils;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class TwitterKit {

	//private String consumerkey, consumersecret, token, tokensecret;
	private static TwitterKit kit;  
	private Twitter twitter;
	private User user;
	
	private TwitterKit(String consumerkey, String consumersecret, String token, String tokensecret) throws TwitterException{

		this.twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerkey, consumersecret);
        twitter.setOAuthAccessToken(new AccessToken(token, tokensecret));
        user = twitter.verifyCredentials();
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				twitter.shutdown();
			}
		});       
	}
	
	public static TwitterKit create(String consumerkey, String consumersecret, String token, String tokensecret) throws TwitterException {
		if(kit == null) kit = new TwitterKit(consumerkey, consumersecret, token, tokensecret) ;
		return kit;
	}

	public JSONArray getMetions() throws TwitterException, JSONException {
        
        List<Status> statuses = twitter.getMentionsTimeline();
        
        JSONArray result = new JSONArray();
        
        for (Status status : statuses) {
            result.put(fromStatus(user, status));
        }

        return result;
	}

	public JSONObject post(String text) throws TwitterException, JSONException{
		return post(text, null, 0);
	}
	
	public JSONObject post(String text, File file) throws TwitterException, JSONException{
		return post(text, file, 0);
	}
	
	public JSONObject post(String text, File file, long inReplyToStatusId) throws TwitterException, JSONException{
		StatusUpdate post = new StatusUpdate(text);
			
			if(file != null && file.canRead()){
				post.media(file);	
			}
			
			if(inReplyToStatusId > 0){
				post.inReplyToStatusId(inReplyToStatusId);	
			}
			
		return fromStatus(user, twitter.updateStatus(post));
	}
	
	public JSONObject delete(long id) throws TwitterException, JSONException {
		return fromStatus(user, twitter.destroyStatus(id));
	}
	
	public void shutdown(){
		twitter.shutdown();
	}
	
	
	private JSONObject fromStatus(User user, Status status) throws JSONException{
    	JSONObject json = new JSONObject();
    	
    	String text = status.getText();
    	json.put("id", status.getId());
    	json.put("text", text);
    	
    	json.put("to_name", user.getScreenName());
    	json.put("to_id", user.getId());
    	json.put("from_name", status.getUser().getScreenName());
    	json.put("from_id", status.getUser().getId());
    	
    	json.put("address", StringUtils.trim(getAddress(status)));
		
    	UserMentionEntity[] mentionEntities = status.getUserMentionEntities();
		JSONArray mentionArray = new JSONArray();
        for (int i = 0; i < mentionEntities.length; i++) {
        	UserMentionEntity mentionEntity = mentionEntities[i];
        	mentionArray.put(StringUtils.trim(mentionEntity.getScreenName())) ;
			text = StringUtils.remove(text, "@" + mentionEntity.getScreenName());
		}
        json.put("mention", mentionArray);
    			
    			
    	MediaEntity[] mediaEntities = status.getMediaEntities();
        JSONArray mediaArray = new JSONArray();
        for (int i = 0; i < mediaEntities.length; i++) {
			MediaEntity mediaEntity = mediaEntities[i];
			mediaArray.put(StringUtils.trim(mediaEntity.getMediaURL())) ;
			text = StringUtils.remove(text, mediaEntity.getURL());
		}
        json.put("media", mediaArray);
        
        HashtagEntity[] hashtagEntities = status.getHashtagEntities();
        JSONArray hashtagArray = new JSONArray();
        for (int i = 0; i < hashtagEntities.length; i++) {
        	HashtagEntity hashtagEntity = hashtagEntities[i];
        	hashtagArray.put(StringUtils.trim(hashtagEntity.getText()));
			text = StringUtils.remove(text, "#"+hashtagEntity.getText());
		}
        json.put("hashtag", hashtagArray);
        
        text = StringUtils.remove(text, "@" + user.getScreenName());
        json.put("onlytext", StringUtils.trim(text));
        
        return json;
	}
	
	private String getAddress(Status status){
		GeoLocation geoLocation = status.getGeoLocation();
		if(geoLocation != null){
			return GeoUtil.getAddress(geoLocation.getLatitude(), geoLocation.getLongitude());	
		}else{
			return null;
		}
		
	}

	
	

}
