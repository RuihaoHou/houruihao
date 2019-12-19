package com.cheng.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Reverse {
	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
	
		File configFile = new File("src/main/resources/generatorConfig.xml");
		// ����config/generatorConfig.xml
		// File configFile = new File("src/main/resources/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		//����һ�����ö���
		Configuration config = cp.parseConfiguration(configFile);
		//���ûص�
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		//Mybatis�����򹤳�������
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		//�������������ļ�.
		myBatisGenerator.generate(null);
	}
}
