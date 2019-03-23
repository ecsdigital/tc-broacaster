package com.ecs.tcbroadcaster.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jetbrains.annotations.NotNull;

import com.ecs.tcbroadcaster.config.Config;
import com.ecs.tcbroadcaster.config.ListenerConfig;

import jetbrains.buildServer.controllers.admin.AdminPage;
import jetbrains.buildServer.serverSide.auth.Permission;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.PositionConstraint;

public class BroadcastAdminController extends AdminPage{

	protected BroadcastAdminController(@NotNull PagePlaces pagePlaces, @NotNull PluginDescriptor descriptor) {

		super(pagePlaces);
		setPluginName("teamCityBroadcaster");
	    setIncludeUrl(descriptor.getPluginResourcesPath("teamCityBroadcasterAdmin.jsp"));
	    setTabTitle("Team City Broadcaster");
	    setPosition(PositionConstraint.after("plugins"));
	    register();
	}
	
	@Override
	public boolean isAvailable(@NotNull HttpServletRequest request) {

		  if(Config.getUrl() == null) {
			request.getSession().setAttribute("url", "enter-url");
		  }else {
			request.getSession().setAttribute("url", Config.getUrl());
		  }
		  
		  if(Config.getUsername() == null) {
			  request.getSession().setAttribute("username", "enter-username");
		  }else {
			  request.getSession().setAttribute("username", Config.getUsername());
		  }
		  
		  if(Config.getPassword()== null) {
			  request.getSession().setAttribute("password", "enter-password");
		  }else {
			  request.getSession().setAttribute("password", Config.getPassword());
		  }
		 
		  request.getSession().setAttribute("project_persist", ListenerConfig.isProjectPersist());
		  request.getSession().setAttribute("project_removed", ListenerConfig.isProjectRemoved());
		  request.getSession().setAttribute("build_finished", ListenerConfig.isBuildFinished());
		  request.getSession().setAttribute("build_persisted", ListenerConfig.isBuildPersisted());
		  
		  return super.isAvailable(request) && checkHasGlobalPermission(request, Permission.CHANGE_SERVER_SETTINGS);
		
	}

	@Override
	public String getGroup() {
		// TODO Auto-generated method stub
		return SERVER_RELATED_GROUP;
	}

}
