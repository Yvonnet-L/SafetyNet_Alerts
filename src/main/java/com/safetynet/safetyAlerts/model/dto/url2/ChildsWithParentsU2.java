package com.safetynet.safetyAlerts.model.dto.url2;

import java.util.List;

public class ChildsWithParentsU2 {

	
	private List<PersonWithAgeU2> childs;
	private List<PersonWithAgeU2> parents;
	
	
	public ChildsWithParentsU2() {
		super();
	}


	public List<PersonWithAgeU2> getEnfants() {
		return childs;
	}


	public void setEnfants(List<PersonWithAgeU2> childs) {
		this.childs = childs;
	}


	public List<PersonWithAgeU2> getParents() {
		return parents;
	}


	public void setParents(List<PersonWithAgeU2> parents) {
		this.parents = parents;
	}
}
