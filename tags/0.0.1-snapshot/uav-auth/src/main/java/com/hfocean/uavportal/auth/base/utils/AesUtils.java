package com.hfocean.uavportal.auth.base.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//import io.jsonwebtoken.impl.TextCodec;

public class AesUtils {
	
	
	private final static String AES_KEY = "$G&r!I(b?(2017~]";
	private final static byte[] RAW_KEY = getRawKey(AES_KEY.getBytes());
	
	

	public static void main(String[] args) throws Exception {
		// 密码的明文
		String clearPwd = "1234567890123123123123123123";
		// 密码加密后的密文
		String s1 = encrypt(clearPwd);
		String s2 = decrypt(s1);
		// 解密后的字符串
		System.out.println("加密:"+s1);
		System.out.println("加密:"+s2);

	}

	/**
	 * @param rawKey
	 *            密钥
	 * @param clearPwd
	 *            明文字符串
	 * @return 密文字节数组
	 */
	private static String encrypt(String clearStr) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(RAW_KEY, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encypted = cipher.doFinal(clearStr.getBytes());
			return null;
//					TextCodec.BASE64.encode(encypted);
		} catch (Exception e) {
			return clearStr;
		}
	}

	/**
	 * @param encrypted
	 *            密文字节数组
	 * @param rawKey
	 *            密钥
	 * @return 解密后的字符串
	 */
	private static String decrypt(String str) {
		try {
			byte[] encrypted = null;
//					TextCodec.BASE64.decode(str);
			SecretKeySpec secretKeySpec = new SecretKeySpec(RAW_KEY, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return new String(decrypted);
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * @param seed种子数据
	 * @return 密钥数据
	 */
	private static byte[] getRawKey(byte[] seed) {
		byte[] rawKey = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seed);
			// AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			rawKey = secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
		}
		return rawKey;
	}

}
