package com.server.core.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class ProblemList implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Problem> problemList;

	public ProblemList add(Problem problem) {
		this.problemList.add(problem);
		return this;
	}

	public boolean hasAny() {
		if(CollectionUtils.isEmpty(problemList)) {
			return false;
		}

		return true;
	}

	public List<Problem> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}
}
