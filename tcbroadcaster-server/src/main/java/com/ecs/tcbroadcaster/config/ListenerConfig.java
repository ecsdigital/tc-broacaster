package com.ecs.tcbroadcaster.config;

public class ListenerConfig {
	
	private static boolean projectPersist=false;
	private static boolean projectRemoved=false;
	private static boolean buildFinished=false;
	private static boolean buildPersisted=false;
	private static boolean vcsPersisted=false;

	public static boolean isVcsPersisted() {
		return vcsPersisted;
	}

	public static void setVcsPersisted(boolean vcsPersisted) {
		ListenerConfig.vcsPersisted = vcsPersisted;
	}

	public static boolean isBuildPersisted() {
		return buildPersisted;
	}

	public static void setBuildPersisted(boolean buildPersisted) {
		ListenerConfig.buildPersisted = buildPersisted;
	}

	public static boolean isBuildFinished() {
		return buildFinished;
	}

	public static void setBuildFinished(boolean buildFinished) {
		ListenerConfig.buildFinished = buildFinished;
	}

	public static boolean isProjectRemoved() {
		return projectRemoved;
	}

	public static void setProjectRemoved(boolean projectRemoved) {
		ListenerConfig.projectRemoved = projectRemoved;
	}

	public static boolean isProjectPersist() {
		return projectPersist;
	}

	public static void setProjectPersist(boolean projectPersist) {
		ListenerConfig.projectPersist = projectPersist;
	}

}
