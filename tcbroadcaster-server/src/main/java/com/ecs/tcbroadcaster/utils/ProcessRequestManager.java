package com.ecs.tcbroadcaster.utils;

import java.util.List;

import com.ecs.tcbroadcaster.broadcasts.BroadcastManager;
import com.ecs.tcbroadcaster.broadcasts.BuilderManager;
import com.ecs.tcbroadcaster.datamodels.BuildState;
import com.ecs.tcbroadcaster.datamodels.ProjectState;
import com.ecs.tcbroadcaster.datamodels.puremodels.BuildConfig;
import com.ecs.tcbroadcaster.dataprep.ProjectModeller;
import com.sun.jersey.api.client.WebResource.Builder;

import jetbrains.buildServer.ExtensionHolder;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;

public class ProcessRequestManager {
	
	private JsonProcessor jp;
	private BroadcastManager bm;
	private BuilderManager bldm;
	
	public ProcessRequestManager(){
		this.jp = new JsonProcessor();
		this.bm = new BroadcastManager();
		this.bldm = new BuilderManager();
	}
	
	public void processRequest(Boolean listenerSwitch, SProject project, ExtensionHolder myExtensionHolder,String dataTag) {
		if(listenerSwitch) {
			System.out.println("Running request");
			ProjectModeller pm = new ProjectModeller();
			List<SBuildType> builds = project.getBuildTypes();
			ProjectState ps= pm.createProjectStatus(project, myExtensionHolder, builds);
			String jsonStr = jp.serializeObject(ps);
			Builder builder = bldm.createBuilder(dataTag);
			String jsonResponse = bm.makeBroadcast(jsonStr,builder);
			System.out.println(jsonResponse);
		}
	}
	
	public void processRequest(Boolean listenerSwitch, SBuild build,String dataTag) {
		if(listenerSwitch) {
			System.out.println("Running Build Finished request");
			BuildState bs= new BuildState();
			bs.setAgentName(build.getAgentName());
			bs.setBuildId(build.getBuildTypeExternalId());
			bs.setBuildName(build.getFullName());
			bs.setBuildNumber(build.getBuildNumber());
			bs.setBuildProjectExtId(build.getProjectExternalId());
			bs.setBuildStatus(build.getBuildStatus().toString());
			String jsonStr = jp.serializeObject(bs);
			Builder builder = bldm.createBuilder(dataTag);
			String jsonResponse = bm.makeBroadcast(jsonStr,builder);
			System.out.println(jsonResponse);
		}
	}

	public void processRequest(Boolean listenerSwitch, SBuildType buildType, String dataTag) {
		if(listenerSwitch) {
			System.out.println("Running Build Finished request");
			
			BuildConfig bd = new BuildConfig();
			bd.setBuildTypeId(buildType.getBuildTypeId());
			bd.setBuildName(buildType.getName());
			bd.setInternalId(buildType.getInternalId());
			bd.setExternalId(buildType.getExternalId());
			bd.setDescription(buildType.getDescription());
			bd.setConfigParams(buildType.getConfigParameters());
			
			String jsonStr = jp.serializeObject(bd);
			Builder builder = bldm.createBuilder(dataTag);
			String jsonResponse = bm.makeBroadcast(jsonStr,builder);
			System.out.println(jsonResponse);
		}
		
	}

}
