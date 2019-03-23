package com.ecs.tcbroadcaster.datamodels;

public class SshKeyState {

	private String SshName;
	private Boolean SshEncrypted;

	public String getSshName() {
		return SshName;
	}

	public void setSshName(String sshName) {
		SshName = sshName;
	}

	public Boolean getSshEncrypted() {
		return SshEncrypted;
	}

	public void setSshEncrypted(Boolean sshEncrypted) {
		SshEncrypted = sshEncrypted;
	}
}
