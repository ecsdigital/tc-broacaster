package com.ecs.tcbroadcaster.broadcasts;

import com.ecs.tcbroadcaster.config.Config;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public class BroadcastManager {
	
	public String makeBroadcast(String jsonString,Builder builder) {
		
		ClientResponse response = builder.post(ClientResponse.class, jsonString);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
		return output;
	}

}