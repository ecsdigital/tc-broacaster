package com.ecs.tcbroadcaster.listeners;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.ecs.tcbroadcaster.config.ListenerConfig;
import com.ecs.tcbroadcaster.utils.ProcessRequestManager;

import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.Parameter;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildFeatureDescriptor;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.vcs.SVcsRoot;

public class BuildListener extends BuildServerAdapter{

	private ProjectManager myProjectManager;
	private ProcessRequestManager pr;
	
	public BuildListener(SBuildServer server) {
		server.addListener(this);
		this.myProjectManager = server.getProjectManager();
		this.pr = new ProcessRequestManager();
	}
	
	public void buildFinished(SRunningBuild build) {
		System.out.println("Build finished with Id " + Long.toString(build.getBuildId()) + " at " + DateTime.now());
		pr.processRequest(ListenerConfig.isBuildFinished(), build, "build_finished");
	}
	
	public void buildTypePersisted(SBuildType buildType) {
		System.out.println(buildType.getBuildTypeId()+ " persisted at " + DateTime.now());
		pr.processRequest(ListenerConfig.isBuildPersisted(), buildType, "build_persisted");
	}
}
