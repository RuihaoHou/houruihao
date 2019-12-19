package com.cheng.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 现在有了公钥 和私钥 利用私钥对明文（数据库密码） 进行加密 利用公钥 进行解密
 * 
 */
// 私钥MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMCSY50Ih0wKVlwku/YUzClLy83WmprjmTdGrfosqPGbkdWgYggkTUALnY5y91SUsh6Q4oT78C7wh/L0yqxr7Had43nWwDRNPOwf0UQw77u7/hajkMV1Vmfd+8tAzJ8tzAE0FLW+6a7Qn12FGfEXqIRr5IdJgf5kzMcNWkxDOWDxAgMBAAECgYEAms+UwVNv4VOWJbFqYeRKVRCYITxbQhWiwOtTRgHNN57HWKP862qzp78uxKvr5dU8dJIpaJtZdYLs6AafQInqp/Q7cD0iRcd3jeLuTJdL8qBr/MZqqhljT2L+lfvZPkOHSF69KTEVtJapkJV6xTLJMsFzrVDMQnNySFbK1tFquAECQQDefoXgDdk1PGbmURtLKzs62Sl7HMMTXWIrjoSqy4IF1JdjpmKjOUgJdnoRBnLd2Yhrh7Vfv2IyH66f3RtUzabBAkEA3ZJQaGJw0IyWoIoGmtbtddlYFSpuo/NmyZ0wsHXa57QvXs0dJyRlg0nu5WZ7KDeUt0MVRpGWNBJs8IKsiF/2MQJBAJ7SdkIv4IMt8M4ry23nGEqzxfaJ236xRGiPPYil6NS8oqyRkwus0g1fM1d/4PMYW1dW8lO8zB23m/Gl5eKtQoECQEoqfLHEKcEVHiJUsUtJICkvfFVxY6GLit93t2GJwaGHZcfU3qpIe5ZLvCSbfiQDHsuNL+T0PJPniwGiq//mJzECQQDBDt351z+j2IhjFkJfI/rOQGB3nrY3f9U34IeLbD9x+Kqges7tzjoxwjJDw1spz0O9IXjwevyjf79NnhVXfKZD
public class SecurityUtil {
	// 利用私钥用来进行加密
	private static final String PRIVATEKEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMCSY50Ih0wKVlwku/YUzClLy83WmprjmTdGrfosqPGbkdWgYggkTUALnY5y91SUsh6Q4oT78C7wh/L0yqxr7Had43nWwDRNPOwf0UQw77u7/hajkMV1Vmfd+8tAzJ8tzAE0FLW+6a7Qn12FGfEXqIRr5IdJgf5kzMcNWkxDOWDxAgMBAAECgYEAms+UwVNv4VOWJbFqYeRKVRCYITxbQhWiwOtTRgHNN57HWKP862qzp78uxKvr5dU8dJIpaJtZdYLs6AafQInqp/Q7cD0iRcd3jeLuTJdL8qBr/MZqqhljT2L+lfvZPkOHSF69KTEVtJapkJV6xTLJMsFzrVDMQnNySFbK1tFquAECQQDefoXgDdk1PGbmURtLKzs62Sl7HMMTXWIrjoSqy4IF1JdjpmKjOUgJdnoRBnLd2Yhrh7Vfv2IyH66f3RtUzabBAkEA3ZJQaGJw0IyWoIoGmtbtddlYFSpuo/NmyZ0wsHXa57QvXs0dJyRlg0nu5WZ7KDeUt0MVRpGWNBJs8IKsiF/2MQJBAJ7SdkIv4IMt8M4ry23nGEqzxfaJ236xRGiPPYil6NS8oqyRkwus0g1fM1d/4PMYW1dW8lO8zB23m/Gl5eKtQoECQEoqfLHEKcEVHiJUsUtJICkvfFVxY6GLit93t2GJwaGHZcfU3qpIe5ZLvCSbfiQDHsuNL+T0PJPniwGiq//mJzECQQDBDt351z+j2IhjFkJfI/rOQGB3nrY3f9U34IeLbD9x+Kqges7tzjoxwjJDw1spz0O9IXjwevyjf79NnhVXfKZD";

	// DRUID 实现加密利用的IBM 提供的
	public static String encryptDes(String msg) {
		// 注意:在Druid中,Druid实现加解密利用的IBM提供的算法,在这个算法中,是利用的
		// 私钥进行加密,公钥进行解密,也就是与正常的RSA算法相反.
		try {
			return ConfigTools.encrypt(PRIVATEKEY, msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
