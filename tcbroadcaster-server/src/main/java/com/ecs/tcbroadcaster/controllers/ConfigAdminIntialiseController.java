package com.ecs.tcbroadcaster.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;
import com.ecs.tcbroadcaster.broadcasts.BroadcastManager;
import com.ecs.tcbroadcaster.broadcasts.BuilderManager;
import com.ecs.tcbroadcaster.config.Config;
import com.ecs.tcbroadcaster.config.ListenerConfig;
import com.ecs.tcbroadcaster.datamodels.ProjectState;
import com.ecs.tcbroadcaster.dataprep.ProjectModeller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.WebResource.Builder;

import jetbrains.buildServer.ExtensionHolder;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;

public class ConfigAdminIntialiseController extends BaseController{
	
	private final ProjectManager myProjectManager;
	private final ExtensionHolder myExtensionHolder;
	private BuilderManager bldm;
	
	public ConfigAdminIntialiseController(@NotNull WebControllerManager manager, PluginDescriptor descriptor, @NotNull final ProjectManager projectManager, @NotNull ExtensionHolder extensionHolder) {
		
		manager.registerController("/admin/initialise.html", this);
		this.myProjectManager = projectManager;
		this.myExtensionHolder = extensionHolder;
		this.bldm = new BuilderManager();
		
	}

	@Override
	protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url =request.getParameter("urla");
		String username =request.getParameter("usernamea");
		String password = request.getParameter("passworda");
		
		String project_persiststr = request.getParameter("project_persista");
		String project_removedstr = request.getParameter("project_removeda");
		String build_finishedstr = request.getParameter("build_finisheda");
		String build_persistedstr = request.getParameter("build_persisteda");
		String vcs_persistedstr = request.getParameter("vcs_persisteda");
		System.out.println(vcs_persistedstr);
		Boolean project_persister = Boolean.parseBoolean(project_persiststr);
		Boolean project_removed = Boolean.parseBoolean(project_removedstr);
		Boolean build_finished = Boolean.parseBoolean(build_finishedstr);
		Boolean build_persisted = Boolean.parseBoolean(build_persistedstr);
		Boolean vcs_persisted = Boolean.parseBoolean(vcs_persistedstr);
		
		System.out.println("VCS : " +vcs_persisted);
		Config.setUrl(url);
		Config.setUsername(username);
		Config.setPassword(password);
		
		ListenerConfig.setProjectPersist(project_persister);
		ListenerConfig.setProjectRemoved(project_removed);
		ListenerConfig.setBuildFinished(build_finished);
		ListenerConfig.setBuildPersisted(build_persisted);
		ListenerConfig.setVcsPersisted(vcs_persisted);
		
		System.out.println("Project Persist " +ListenerConfig.isProjectPersist());
		System.out.println("Project Removed " +ListenerConfig.isProjectRemoved());
		System.out.println("Build Finished " +ListenerConfig.isBuildFinished());
		System.out.println("Build Persisted " +ListenerConfig.isBuildPersisted());
		System.out.println("VCS Persisted " +ListenerConfig.isVcsPersisted());
		
		BroadcastManager bm = new BroadcastManager();
		Builder builder =bldm.createBuilder("projects");
		ProjectModeller pm = new ProjectModeller();
		
		List<SProject> projects = myProjectManager.getProjects();
		List<ProjectState> pslist = new ArrayList<ProjectState>();
		
		for(SProject project:projects) {
			List<SBuildType> buildTypes =project.getBuildTypes();
			ProjectState ps = pm.createProjectStatus(project, myExtensionHolder, buildTypes);
			pslist.add(ps);
		}
		
		Gson gson = new GsonBuilder().create();
		String value = gson.toJson(pslist);
		
		
		bm.makeBroadcast(value,builder);
		return null;
	}

}
