package com.repo.depo.model;

import org.springframework.data.annotation.Id;

public abstract class AbstractEntityObject {

	@Id
    private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
