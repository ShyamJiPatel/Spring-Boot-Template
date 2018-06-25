package org.shyam.util;

public abstract class JSONUtil {

	public static String toJSON(String key, String value) {
		return "{ \"" + key + "\" : \"" + value + "\" }";
	}

}
