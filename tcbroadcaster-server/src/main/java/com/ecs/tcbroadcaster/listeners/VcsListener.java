package com.ecs.tcbroadcaster.listeners;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

import com.ecs.tcbroadcaster.config.ListenerConfig;
import com.ecs.tcbroadcaster.utils.ProcessRequestManager;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.vcs.SVcsRoot;

public class VcsListener extends BuildServerAdapter{
	private ProjectManager myProjectManager;
	private ProcessRequestManager pr;
	
	public VcsListener(SBuildServer server) {
		server.addListener(this);
		this.myProjectManager = server.getProjectManager();
		this.pr = new ProcessRequestManager();
	}
	
	public void vcsRootPersisted(SVcsRoot vcsRoot) {
		System.out.println("VCS roots persisted at " + DateTime.now());
		pr.processRequest(ListenerConfig.isVcsPersisted(), vcsRoot, "vcs_persisted");
	}
	
}	
