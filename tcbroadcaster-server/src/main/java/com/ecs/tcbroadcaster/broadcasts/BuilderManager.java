package com.ecs.tcbroadcaster.broadcasts;

import com.ecs.tcbroadcaster.config.Config;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class BuilderManager {
	
	public Builder createBuilder(String dataTag) {
		try {
			Client client = Client.create();
			String url= Config.getUrl();
			WebResource webResource = client.resource(url);
			client.addFilter(new HTTPBasicAuthFilter(Config.getUsername(),Config.getPassword()));
			Builder builder = webResource.accept("application/json");
			builder =webResource.header("dataTag", dataTag);
			return builder;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}
