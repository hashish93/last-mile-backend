package com.appzoneltd.lastmile.microservice.workflow.handler;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ProcessServiceKey implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final long EXPIRATION_MILLIS = 6 * 60 * 1000;

	@NotNull
	public final Long packageId;

	@NotNull
	public final String processName;

	@NotNull
	public final long timestamp;

	public ProcessServiceKey(@NotNull Long packageId, @NotNull String processName) {
		super();
		this.packageId = packageId;
		this.processName = processName;
		this.timestamp = System.currentTimeMillis();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + packageId.hashCode();
		result = prime * result + processName.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessServiceKey other = (ProcessServiceKey) obj;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		if (processName == null) {
			if (other.processName != null)
				return false;
		} else if (!processName.equals(other.processName))
			return false;
		return true;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() - timestamp >= EXPIRATION_MILLIS;
	}

	@Override
	public String toString() {
		return "ProcessServiceKey [packageId=" + packageId + ", processName=" + processName + "]";
	}

}
