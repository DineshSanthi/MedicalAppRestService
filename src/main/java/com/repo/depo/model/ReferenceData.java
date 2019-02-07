package com.repo.depo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reference_Data")
public class ReferenceData {
	
	@Id
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	private String refId;
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public String getDisplayValueTamil() {
		return displayValueTamil;
	}
	public void setDisplayValueTamil(String displayValueTamil) {
		this.displayValueTamil = displayValueTamil;
	}
	public String getDisplayValueTelugu() {
		return displayValueTelugu;
	}
	public void setDisplayValueTelugu(String displayValueTelugu) {
		this.displayValueTelugu = displayValueTelugu;
	}
	private String displayValue;
	private String displayValueTamil;
	private String displayValueTelugu;

}
