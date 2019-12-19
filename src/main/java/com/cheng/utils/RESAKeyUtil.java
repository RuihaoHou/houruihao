package com.cheng.utils;

import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import sun.misc.*;

public class RESAKeyUtil {
	private static final String KEY_ALGORITHM = "RSA";
	public static final String PUBLICKEY = "RSAPublicKey";
	public static final String PRIVATEKEY = "RSAPrivateKey";
	public static Map<String, Object> initKey() {
		HashMap<String, Object> map = new HashMap<String, Object>(2);
		// 生成秘钥的 密钥对生成器
		KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			// 限定生成的秘钥的长度为1024
			generator.initialize(1024);
			KeyPair KeyPair = generator.generateKeyPair();
			PublicKey public1 = KeyPair.getPublic();
			PrivateKey private1 = KeyPair.getPrivate();
			map.put(PUBLICKEY, public1);
			map.put(PRIVATEKEY, private1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	// 对公钥和秘钥进行二次加密
	/* 利用Base64 编码进行加密的算法 */
	public static String encryptBase64(byte[] key) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeBuffer(key);
	}

	/* 利用Base64 编码进行解密的算法 */
	public static byte[] decryptBase64(String key) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {

		Map<String, Object> map = initKey();
		Key pubKey = (Key) map.get(PUBLICKEY);
		Key priKey = (Key) map.get(PRIVATEKEY);
		String pub = encryptBase64(pubKey.getEncoded());
		String pri = encryptBase64(priKey.getEncoded());			
	}

}
