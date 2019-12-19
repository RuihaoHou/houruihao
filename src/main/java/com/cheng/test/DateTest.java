package com.cheng.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) throws ParseException {
		
		
		Date date = new Date();
		System.out.println(date.toString());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		
		String format = simpleDateFormat.format(date);
		System.out.println(format);
		Date parse = simpleDateFormat.parse(format);
		System.out.println(parse);
		
		
	
	}

}
