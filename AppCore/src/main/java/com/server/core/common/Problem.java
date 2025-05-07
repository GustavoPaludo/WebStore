package com.server.core.common;

import java.io.Serializable;

public class Problem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;

	public Problem() {}

	public Problem(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
