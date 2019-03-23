package com.ecs.tcbroadcaster.datamodels;

public class VcsRootState {
	
	private String VcsName;
	private String VcsDisplayName;
	private String VcsParentId;
	private String VcsRootUrl;
	private String Username;
	private String VcsExtId;
	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getVcsExtId() {
		return VcsExtId;
	}

	public void setVcsExtId(String vcsExtId) {
		VcsExtId = vcsExtId;
	}

	public String getVcsDisplayName() {
		return VcsDisplayName;
	}

	public void setVcsDisplayName(String vcsDisplayName) {
		VcsDisplayName = vcsDisplayName;
	}

	public String getVcsName() {
		return VcsName;
	}

	public void setVcsName(String vcsName) {
		VcsName = vcsName;
	}

	public String getVcsParentId() {
		return VcsParentId;
	}

	public void setVcsParentId(String vcsParentId) {
		VcsParentId = vcsParentId;
	}

	public String getVcsRootUrl() {
		return VcsRootUrl;
	}

	public void setVcsRootUrl(String vcsRootUrl) {
		VcsRootUrl = vcsRootUrl;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

}
