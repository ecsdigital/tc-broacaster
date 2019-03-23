package com.ecs.tcbroadcaster.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonProcessor {
	
	String serializeObject(Object obj) {
		Gson gson = new GsonBuilder().create();
		String value = gson.toJson(obj);
		
		return value;
	}

}
