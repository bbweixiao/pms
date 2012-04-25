package com.kaishengit.util;

import java.util.UUID;

public class PKUtil {

	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
}
