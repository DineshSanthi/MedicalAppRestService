package com.repo.depo.authentication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SmartGWTDSResponse implements Serializable{

	private DSResponse response;

	public DSResponse getResponse() {
		return response;
	}

	public void setResponse(DSResponse response) {
		this.response = response;
	}
	

}