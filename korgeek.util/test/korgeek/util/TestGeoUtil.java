package korgeek.util;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestGeoUtil extends TestCase{

	double latitude=-6.2173964, longitude=106.8126695;

	
	public void testGetAddress() throws Exception {

		Assert.assertEquals("Jalan Jenderal Sudirman No.Kav 44-46, Tanahabang, Central Jakarta City, Jakarta 10210, Indonesia", GeoUtil.getAddress(latitude, longitude, "street_address"));
		Assert.assertEquals("Indonesia", GeoUtil.getAddress(latitude, longitude, "country"));
		Assert.assertEquals("Tanahabang, Central Jakarta City, Jakarta", GeoUtil.getAddress(latitude, longitude));
	
	}

}