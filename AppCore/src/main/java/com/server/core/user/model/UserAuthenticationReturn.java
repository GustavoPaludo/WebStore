package com.server.core.user.model;

import java.io.Serializable;

import com.server.core.common.ProblemList;

public class UserAuthenticationReturn implements Serializable {

	private static final long serialVersionUID = 1L;

	private String jwtToken;
	private ProblemList problemList;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public ProblemList getProblemList() {
		return problemList;
	}

	public void setProblemList(ProblemList problemList) {
		this.problemList = problemList;
	}
}
