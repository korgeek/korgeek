package korgeek.util;

import java.io.File;

import junit.framework.TestCase;


public class TestCodeUtil extends TestCase{

	
	public void testRandomCode() throws Exception {
		
		assertEquals(1, CodeUtil.randomCode(1).length());
		assertEquals(2, CodeUtil.randomCode(2).length());
		assertEquals(3, CodeUtil.randomCode(3).length());
		assertEquals(4, CodeUtil.randomCode(4).length());
		assertEquals(5, CodeUtil.randomCode(5).length());
		assertEquals(6, CodeUtil.randomCode(6).length());
		assertEquals(7, CodeUtil.randomCode(7).length());
		assertEquals(8, CodeUtil.randomCode(8).length());
		assertEquals(9, CodeUtil.randomCode(9).length());
		
	}
	
	public void testUniqueKey() throws Exception {
		
		assertNotSame(CodeUtil.unique(), CodeUtil.unique());
		assertNotSame(CodeUtil.sequence(), CodeUtil.sequence());
		
	}
	
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
	
	public void testPassword() throws Exception{

		assertEquals(CodeUtil.password("1234"), CodeUtil.password("1234"));
		
	}
	
	
	public void testFileName() throws Exception {
		
		String filename = "test.png";
		long filehash = 1234L;
		String filehashstr = "1234";
		
		
		File test = CodeUtil.toFile("test.png");
		File test2 = CodeUtil.toFile("test.png", filehash);
		String test3 = CodeUtil.toFilePath(filename, filehashstr);
		Debug.debug(test);
		Debug.debug(test2);
		Debug.debug(test3);
	}
	
	public void xtestName()  {
		
		Debug.debug( CodeUtil.toBase36(System.nanoTime()) );
		
	}
	
	public void testPin() throws Exception {
		
		
		Debug.debug( Integer.toHexString(1) ); 
		Debug.debug( (long)0xEFFFFFFF ); 
		Debug.debug( Integer.toHexString( (int)(System.nanoTime() % (long)0xFFFFFFFF))  );
	}
	
	
}




