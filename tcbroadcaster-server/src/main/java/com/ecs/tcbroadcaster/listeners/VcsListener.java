package com.ecs.tcbroadcaster.listeners;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

import com.ecs.tcbroadcaster.config.ListenerConfig;
import com.ecs.tcbroadcaster.utils.ProcessRequestManager;

import jetbrains.buildServer.ExtensionHolder;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.vcs.SVcsRoot;

public class VcsListener extends BuildServerAdapter{
	private ProjectManager myProjectManager;
	private ProcessRequestManager pr;
	
	public VcsListener(SBuildServer server, @NotNull ExtensionHolder extensionHolder) {
		server.addListener(this);
		this.myProjectManager = server.getProjectManager();
	}
	
	public void vcsRootPersisted(@NotNull SVcsRoot vcsRoot) {
		System.out.println("VCS roots changed at " + DateTime.now());
		pr.processRequest(ListenerConfig.isVcsPersisted(), vcsRoot, "vcs_persisted");
	}
	
}	
