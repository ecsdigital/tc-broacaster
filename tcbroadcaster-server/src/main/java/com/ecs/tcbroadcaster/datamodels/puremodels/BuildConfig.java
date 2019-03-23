package com.ecs.tcbroadcaster.datamodels.puremodels;

import java.util.Map;

public class BuildConfig {

	private String buildTypeId;
	private String buildName;
	private String internalId;
	private String externalId;
	private String description;
	private String buildCounter;
	private Map<String,String> configParams;
	

	public String getBuildCounter() {
		return buildCounter;
	}

	public void setBuildCounter(String buildCounter) {
		this.buildCounter = buildCounter;
	}	

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getConfigParams() {
		return configParams;
	}

	public void setConfigParams(Map<String, String> configParams) {
		this.configParams = configParams;
	}

	public String getBuildTypeId() {
		return buildTypeId;
	}

	public void setBuildTypeId(String buildTypeId) {
		this.buildTypeId = buildTypeId;
	}
}
