package korgeek.util;

import junit.framework.TestCase;


public class TestCodeUtil extends TestCase{

	public void testEncode36() throws Exception {
		
		long nanoTime = System.nanoTime();
		int intValue = Integer.MAX_VALUE;
		
		Debug.debug( CodeUtil.toBase36( nanoTime ) );
		Debug.debug( CodeUtil.toBase36( intValue ) );
		
		long longBase36 = CodeUtil.fromBase36(CodeUtil.toBase36( nanoTime ));
		assertEquals(nanoTime, longBase36);

		int intBase36 = CodeUtil.fromBase36(CodeUtil.toBase36( intValue ));
		assertEquals(intValue, intBase36);

	}
	
	public void testDecode36() throws Exception {
	
		String data = "jinik";
		
		Debug.debug( CodeUtil.md5( data ) );
		Debug.debug( CodeUtil.mmd5( data ) );
	
		assertEquals(CodeUtil.md5( data ), CodeUtil.md5( data ));
		assertEquals(CodeUtil.mmd5( data ), CodeUtil.mmd5( data ));
	}
	
	public void testEncrypt() throws Exception {

		String message = "한글과English가함께있는01234테스트'";
		String key = "this is key";
		
		String encrypted = CodeUtil.encrypt(key, message);
		Debug.debug( encrypted );
		
		assertEquals(message, CodeUtil.decrypt(key, encrypted));

	}
	
}




