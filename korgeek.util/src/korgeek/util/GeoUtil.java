package korgeek.util;

import java.util.Iterator;
import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

public class GeoUtil {
	
	//latitude=-6.2173964, longitude=106.8126695
	//LatLng location = new LatLng(Double.toString(lat), Double.toString(lng));
	
	public static String getAddress(double lat, double lng){
		return getAddress(Double.toString(lat), Double.toString(lng));
	}
	
	public static String getAddress(double lat, double lng, String geoResultType){
		return getAddress(Double.toString(lat), Double.toString(lng), geoResultType);
	}
	
	public static String getAddress(String lat, String lng){
		return getAddress(lat, lng, "administrative_area_level_3", false);
	}
	
	public static String getAddress(String lat, String lng, String geoResultType){
		return getAddress(lat, lng, geoResultType, true);
	}
	
	public static String getAddress(String lat, String lng, String geoResultType, boolean country){
		
		String result = "";
		
		try{
			LatLng location = new LatLng(lat, lng);
			Geocoder geo = new Geocoder();
			GeocodeResponse response = geo.geocode( (new GeocoderRequestBuilder()).setLanguage("en").setLocation(location).getGeocoderRequest() );
			List<GeocoderResult> geoResults = response.getResults();
			
			
			
			for (Iterator iterator = geoResults.iterator(); iterator.hasNext();) {
				GeocoderResult geocoderResult = (GeocoderResult) iterator.next();
				String type = geocoderResult.getTypes().get(0);
				//System.out.println (type);
				if(type.equalsIgnoreCase(geoResultType)){
					result = geocoderResult.getFormattedAddress() ;
				}else if(!country && type.equalsIgnoreCase("country")){
					result = result.replaceAll(", " + geocoderResult.getFormattedAddress(),  "");
				}
				//System.out.println(geocoderResult.getTypes().get(0) +" - "+ geocoderResult.getFormattedAddress() ) ;
			}
		}catch(Exception e){
			
		}
		return result;
	}
	
}
