package com.ecs.tcbroadcaster.datamodels;

public class BuildState {
	
	private String BuildName;
	private String BuildNumber;
	private String BuildId;
	private String BuildStatus;
	private String buildProjectExtId;
	private String AgentName;
	
	public String getAgentName() {
		return AgentName;
	}

	public void setAgentName(String agentName) {
		AgentName = agentName;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	private String ProjectId;
	

	public String getBuildProjectExtId() {
		return buildProjectExtId;
	}

	public void setBuildProjectExtId(String buildProjectExtId) {
		this.buildProjectExtId = buildProjectExtId;
	}

	public String getBuildName() {
		return BuildName;
	}

	public void setBuildName(String buildName) {
		BuildName = buildName;
	}

	public String getBuildNumber() {
		return BuildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		BuildNumber = buildNumber;
	}

	public String getBuildId() {
		return BuildId;
	}

	public void setBuildId(String buildId) {
		BuildId = buildId;
	}

	public String getBuildStatus() {
		return BuildStatus;
	}

	public void setBuildStatus(String buildStatus) {
		BuildStatus = buildStatus;
	}

}
