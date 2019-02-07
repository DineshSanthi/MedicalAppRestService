package com.repo.depo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RelationData")
public class RelationData {
	
	@Id
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPrimaryTableId() {
		return primaryTableId;
	}
	public void setPrimaryTableId(String primaryTableId) {
		this.primaryTableId = primaryTableId;
	}
	public String getPrimaryTable() {
		return primaryTable;
	}
	public void setPrimaryTable(String primaryTable) {
		this.primaryTable = primaryTable;
	}
	public String getSecondaryTable() {
		return secondaryTable;
	}
	public void setSecondaryTable(String secondaryTable) {
		this.secondaryTable = secondaryTable;
	}
	public String getSecondaryTableId() {
		return secondaryTableId;
	}
	public void setSecondaryTableId(String secondaryTableId) {
		this.secondaryTableId = secondaryTableId;
	}
	private String appName;
	private String primaryTableId;
	private String primaryTable;
	private String secondaryTable;
	private String secondaryTableId;


}
