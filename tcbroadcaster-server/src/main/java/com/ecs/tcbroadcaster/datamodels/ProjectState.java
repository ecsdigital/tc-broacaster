package com.ecs.tcbroadcaster.datamodels;

import java.util.ArrayList;
import java.util.List;

public class ProjectState {
	
	private String projectName;
	private String projectId;
	private String parentProject;
	private Boolean isArchived;
	private Boolean isDeleted;
	
	private List<VcsRootState> vcsRootList = new ArrayList<VcsRootState>();
	private List<SshKeyState> sshKeys = new ArrayList<SshKeyState>();
	private List<BuildState> buildTypes = new ArrayList<BuildState>();

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getParentProject() {
		return parentProject;
	}
	public void setParentProject(String parentProject) {
		this.parentProject = parentProject;
	}
	public Boolean getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<VcsRootState> getVcsRootList() {
		return vcsRootList;
	}
	public void setVcsRootList(List<VcsRootState> vcsRootList) {
		this.vcsRootList = vcsRootList;
	}
	public List<SshKeyState> getSshKeys() {
		return sshKeys;
	}
	public void setSshKeys(List<SshKeyState> sshKeys) {
		this.sshKeys = sshKeys;
	}
	public List<BuildState> getBuildTypes() {
		return buildTypes;
	}
	public void setBuildTypes(List<BuildState> buildTypes) {
		this.buildTypes = buildTypes;
	}

}
