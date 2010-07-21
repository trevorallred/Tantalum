package com.opentenfold.model;

import java.util.ArrayList;
import java.util.List;

public class Reference {
	private List<ReferenceStep> steps = new ArrayList<ReferenceStep>();

	public List<ReferenceStep> getSteps() {
		return steps;
	}

	public void setSteps(List<ReferenceStep> steps) {
		this.steps = steps;
	}

}
