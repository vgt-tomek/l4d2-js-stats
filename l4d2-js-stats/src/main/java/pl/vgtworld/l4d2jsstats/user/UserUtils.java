package pl.vgtworld.l4d2jsstats.user;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

abstract class UserUtils {
	
	private static final int RADIX = 16;

	private static final int HASH_EXPECTED_LENGTH = 32;
	
	private static final String CHARSET_NAME = "UTF-8";
	
	public static String generateSalt() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String seed = "" + System.currentTimeMillis();
		return generateHash(seed);
	}
	
	public static String generatePasswordHash(String password, String salt) throws NoSuchAlgorithmException,
		UnsupportedEncodingException {
		String seed = password + salt;
		return generateHash(seed);
	}
	
	public static String generateToken(String token) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return generateHash(token);
	}
	
	private static String generateHash(String seed) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] result = md.digest(seed.getBytes(CHARSET_NAME));
		BigInteger bigInt = new BigInteger(1, result);
		String hashtext = bigInt.toString(RADIX);
		while (hashtext.length() < HASH_EXPECTED_LENGTH) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
}
