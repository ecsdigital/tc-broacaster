package com.ecs.tcbroadcaster.dataprep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ecs.tcbroadcaster.datamodels.BuildState;
import com.ecs.tcbroadcaster.datamodels.ProjectState;
import com.ecs.tcbroadcaster.datamodels.SshKeyState;
import com.ecs.tcbroadcaster.datamodels.VcsRootState;

import jetbrains.buildServer.ExtensionHolder;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.ssh.ServerSshKeyManager;
import jetbrains.buildServer.ssh.TeamCitySshKey;
import jetbrains.buildServer.vcs.SVcsRoot;

public class ProjectModeller {
	
	public ProjectState createProjectStatus(SProject project, ExtensionHolder myExtensionHolder,
			List<SBuildType> buildTypes) {

		ProjectState ps = new ProjectState();

		ps.setProjectName(project.getExternalId());
		ps.setProjectId(project.getProjectId());
		try {
			ps.setParentProject(project.getParentProject().getExternalId());
		} catch (Exception e) {
			ps.setParentProject(null);
		}

		ps.setIsArchived(project.isArchived());
		ps.setIsDeleted(false);

		List<VcsRootState> vcsRootList = new ArrayList<VcsRootState>();
		List<SVcsRoot> roots = project.getVcsRoots();

		for (SVcsRoot root : roots) {
			VcsRootState svcs = new VcsRootState();
			svcs.setVcsName(root.getVcsName());
			svcs.setVcsExtId(root.getExternalId());
			svcs.setVcsDisplayName(root.getVcsDisplayName());
			svcs.setName(root.getName());
			Map<String, String> props = root.getProperties();
			svcs.setUsername(props.get("username"));
			svcs.setVcsRootUrl(root.getDescription());
			vcsRootList.add(svcs);
		}
		ps.setVcsRootList(vcsRootList);

		List<SshKeyState> sshList = new ArrayList<SshKeyState>();
		List<TeamCitySshKey> sshKeys = getSshKeyList(myExtensionHolder, project);
		for (TeamCitySshKey ssh : sshKeys) {
			SshKeyState sshKey = new SshKeyState();
			sshKey.setSshName(ssh.getName());
			sshKey.setSshEncrypted(ssh.isEncrypted());
			sshList.add(sshKey);
		}

		ps.setSshKeys(sshList);

		List<BuildState> buildList = new ArrayList<BuildState>();

		for (SBuildType buildType : buildTypes) {
			try {
				BuildState sbuildStatus = new BuildState();
				String buildName = buildType.getLastChangesFinished().getBuildTypeName();
				String buildNumber = buildType.getLastChangesFinished().getBuildNumber();
				String buildId = Long.toString(buildType.getLastChangesFinished().getBuildId());
				String buildStatus = buildType.getLastChangesFinished().getBuildStatus().toString();
				String buildProjectExtId = buildType.getLastChangesFinished().getProjectExternalId();
				String agentName = buildType.getLastChangesFinished().getAgentName();
				String projectId = buildType.getLastChangesFinished().getProjectId();
				
				sbuildStatus.setBuildName(buildName);
				sbuildStatus.setBuildNumber(buildNumber);
				sbuildStatus.setBuildId(buildId);
				sbuildStatus.setBuildStatus(buildStatus);
				sbuildStatus.setBuildProjectExtId(buildProjectExtId);
				sbuildStatus.setAgentName(agentName);
				sbuildStatus.setProjectId(projectId);

				buildList.add(sbuildStatus);
			} catch (Exception e) {
				System.out.println("No Successful builds");
			}
		}
		ps.setBuildTypes(buildList);

		return ps;
	}

	List<TeamCitySshKey> getSshKeyList(ExtensionHolder myExtensionHolder, SProject project) {
		Collection<ServerSshKeyManager> extensions = myExtensionHolder.getExtensions(ServerSshKeyManager.class);
		ServerSshKeyManager sshm;

		if (extensions.isEmpty()) {
			sshm = null;
		} else {
			sshm = extensions.iterator().next();
		}
		List<TeamCitySshKey> sshKeys = sshm.getKeys(project);
		return sshKeys;
	}

}
