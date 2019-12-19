package com.cheng.utils;

import java.util.Properties;

import com.alibaba.druid.filter.config.ConfigTools;
/*

*/
import com.alibaba.druid.util.DruidPasswordCallback;

//利用公钥进行解密的工具类
public class DbPasswordCallback extends DruidPasswordCallback {
	private static final long serialVersionUID = 1L;

	// 利用公钥进行解密
	public static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAkmOdCIdMClZcJLv2FMwpS8vN1pqa45k3Rq36LKjxm5HVoGIIJE1AC52OcvdUlLIekOKE+/Au8Ify9Mqsa+x2neN51sA0TTzsH9FEMO+7u/4Wo5DFdVZn3fvLQMyfLcwBNBS1vumu0J9dhRnxF6iEa+SHSYH+ZMzHDVpMQzlg8QIDAQAB";
	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String password = properties.getProperty("password");
		if (password != null) {
			try {
				String decrypt = ConfigTools.decrypt(PUBLICKEY, password);
				setPassword(decrypt.toCharArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
