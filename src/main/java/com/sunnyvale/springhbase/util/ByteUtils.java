package com.sunnyvale.springhbase.util;

import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.util.NumberUtils;

public class ByteUtils {
	public static int stringToInt(byte[] val) {
		String str = Bytes.toString(val);
		int loc = str.indexOf(".");
		if (loc >= 0)
			str = str.substring(0, loc);
		return NumberUtils.parseNumber(str, Integer.class);
	}

	public static long stringToLong(byte[] val) {
		String str = Bytes.toString(val);
		int loc = str.indexOf(".");
		if (loc >= 0)
			str = str.substring(0, loc);
		return NumberUtils.parseNumber(str, Long.class);
	}

	public static boolean stringToBoolean(byte[] val) {
		return Bytes.toString(val).trim().equals("true") ? true : false;
	}
	
	public static String toString(byte[] val) {
		return Bytes.toString(val);
	}
}
