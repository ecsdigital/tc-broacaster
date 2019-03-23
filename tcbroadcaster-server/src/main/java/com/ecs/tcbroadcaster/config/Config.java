package com.ecs.tcbroadcaster.config;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Config {
	private static String url;
	private static String username;
	private static String password;
	
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String urlin) {
		url = urlin;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String usernamein) {
		username = usernamein;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String passwordin) {
		password = passwordin;
	}

}
