package com.ecs.tcbroadcaster.listeners;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

import com.ecs.tcbroadcaster.config.ListenerConfig;
import com.ecs.tcbroadcaster.utils.ProcessRequestManager;

import jetbrains.buildServer.ExtensionHolder;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SProject;

public class ProjectListener extends BuildServerAdapter{
	
	private ProjectManager myProjectManager;
	private ExtensionHolder myExtensionHolder;
	private ProcessRequestManager pr;
	
	public ProjectListener(SBuildServer server, @NotNull ExtensionHolder extensionHolder) {
		server.addListener(this);
		this.myProjectManager = server.getProjectManager();
		this.myExtensionHolder = extensionHolder;
		this.pr = new ProcessRequestManager();
	}
	
	public void projectPersisted(String projectId) {
		System.out.println(projectId + " persisted at "+DateTime.now());
		SProject project = myProjectManager.findProjectById(projectId);

		pr.processRequest(ListenerConfig.isProjectPersist(), project,myExtensionHolder,"project_persisted");
	}
	
	public void projectDearchived(String projectId) {
		System.out.println(projectId + " dearchived at " + DateTime.now());
		SProject project = myProjectManager.findProjectById(projectId);
		pr.processRequest(ListenerConfig.isProjectPersist(), project,myExtensionHolder,"project_dearchived");
	}
	
	public void projectRemoved(SProject project) {
		System.out.println(project.getProjectId() + " removed at " + DateTime.now());
		pr.processRequest(ListenerConfig.isProjectRemoved(), project,myExtensionHolder,"project_removed");
	}
	
	public void projectRestored(String projectId) {
		System.out.println(projectId + " restored at " + DateTime.now());
		SProject project = myProjectManager.findProjectById(projectId);
		pr.processRequest(ListenerConfig.isProjectPersist(), project,myExtensionHolder,"project_restored");
	}
	
}
