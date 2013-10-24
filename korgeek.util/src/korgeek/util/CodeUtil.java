package korgeek.util;

import java.io.File;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class CodeUtil {
	
	static Random r = new Random();
	
    public static <N> String toBase36( N num ) {
    	
    	if(num.getClass().getName().equals("java.lang.Long")){
    		return Long.toString((Long) num, 36);	
    	}else if(num.getClass().getName().equals("java.lang.Integer")){
    		return Integer.toString((Integer) num, 36);	
    	}else{
    		throw new IllegalArgumentException("Not support this Number type.");
    	}

    }
    
    
    public static String randomCode(int length) {
    	int seed = Double.valueOf(Math.pow(10, length)).intValue();  
    	return String.format("%0"+length+"d", (r.nextInt(seed))); 
    }
    
    public static <RET> RET fromBase36( String basecode ){
    	Long result = Long.valueOf(basecode, 36);
    	long maxInt = new Long(Integer.MAX_VALUE);
    	
    	if(result > maxInt){
    		return ((RET) result);
    	}else{
    		return ((RET) (Integer)result.intValue());
    	}
    	
    }
    
    public static String sequence(){
    	return toBase36(System.nanoTime());
    }
    
    public static String unique(){
    	UUID uuid = UUID.randomUUID();
    	return toUnsignedString(uuid.getMostSignificantBits(), 6) + toUnsignedString(uuid.getLeastSignificantBits(), 6);
    }
    
    public static String UUID(){
    	return UUID.randomUUID().toString();
    }
    
    
    
    public static String UUID(String key){
    	return UUID.fromString(key).toString();
    }
    
    public static File toFile(String filename) {
		return toFile(filename, filename);
	}
    
    public static File toFile(File file) {
		return toFile(file.getPath(), file.length());
	}
    
    private static File toFile(String filename, String hash) {
		return new File(toFilePath(filename, hash));
	}
    
    public static File toFile(String filename, long filehash) {
		return toFile(filename, String.valueOf(filehash));
	}
    

	public static String toFilePath(String filename) {
    	return toFilePath(filename, filename);
    }
    
	public static String toFilePath(String filename, long filehash) {
    	return toFilePath(filename, String.valueOf(filehash));
    }
	
    public static String toFilePath(String filename, String hash) {
    	String ext = filename.substring(filename.lastIndexOf("."), filename.length());
    	String full = md5(filename+hash);
    	
    	return md5Path(full+ext);
	}
    
    public static String md5Path(String md5filename_ext){
    	String ext = md5filename_ext.substring(md5filename_ext.lastIndexOf("."), md5filename_ext.length());
    	String full = md5filename_ext.substring(0, md5filename_ext.lastIndexOf("."));
    	
    	StringBuffer sb = new StringBuffer();
		sb
		.append(full.substring(0, 2)).append(File.separator)
		.append(full.substring(2, 4)).append(File.separator)
		.append(full.substring(4, 6)).append(File.separator)
		.append(full.substring(6, 8)).append(File.separator)
		.append(full.substring(8, 10)).append(File.separator)
		.append(full.substring(10, 12)).append(File.separator)
		.append(full.substring(12, 14)).append(File.separator)
		.append(full.substring(14, 16)).append(File.separator)
		.append(full).append(ext);
		
		return sb.toString();
    }
    
    public static String md5UUID(){
    	return md5(UUID());
    }
    
    public static String md5UUID(String key){
    	return md5(UUID(key));
    }
    
    public static String md5( String data ){
    	return org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
    }
    
    public static String mmd5( String data ){
    	return md5(md5(data));
    }
    
    //code from http://www.androidsnippets.com/encryptdecrypt-strings
    public static String encrypt(String seed, String cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(result));
	}
	
	public static String decrypt(String seed, String encrypted) throws Exception {
	        byte[] rawKey = getRawKey(seed.getBytes());
	        byte[] enc = org.apache.commons.codec.binary.Base64.decodeBase64(encrypted.getBytes());
	        byte[] result = decrypt(rawKey, enc);
	        return new String(result);
	}
	
	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	    sr.setSeed(seed);
	    kgen.init(128, sr); // 192 and 256 bits may not be available
	    SecretKey skey = kgen.generateKey();
	    byte[] raw = skey.getEncoded();
	    return raw;
	}
	
	
	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	    byte[] encrypted = cipher.doFinal(clear);
	        return encrypted;
	}
	
	private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	    byte[] decrypted = cipher.doFinal(encrypted);
	    return decrypted;
	}
	
	public static String toHex(String txt) {
	        return toHex(txt.getBytes());
	}
	public static String fromHex(String hex) {
	        return new String(toByte(hex));
	}
	
	public static byte[] toByte(String hexString) {
	        int len = hexString.length()/2;
	        byte[] result = new byte[len];
	        for (int i = 0; i < len; i++)
	                result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
	        return result;
	}
	
	public static String toHex(byte[] buf) {
	        if (buf == null)
	                return "";
	        StringBuffer result = new StringBuffer(2*buf.length);
	        for (int i = 0; i < buf.length; i++) {
	                appendHex(result, buf[i]);
	        }
	        return result.toString();
	}
	private final static String HEX = "0123456789abcdef";
	private static void appendHex(StringBuffer sb, byte b) {
	        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
	}
    
	public static String password(String str) throws Exception{
		return md5(encrypt(str, str));
	}
	
	
	
	private static String toUnsignedString(long i, int shift) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        long number = i;
        do {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= shift;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }


    final static char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', '_', '-' // '.', '-'
    };


	

	
	
	
	
}
