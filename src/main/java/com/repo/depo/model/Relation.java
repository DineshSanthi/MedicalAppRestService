package com.repo.depo.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Relationship")
public class Relation {
	
	@Id
	private String id;
	private String appName;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMinimumMultiplicity() {
		return minimumMultiplicity;
	}
	public void setMinimumMultiplicity(String minimumMultiplicity) {
		this.minimumMultiplicity = minimumMultiplicity;
	}
	public String getMaximumMultiplicity() {
		return maximumMultiplicity;
	}
	public void setMaximumMultiplicity(String maximumMultiplicity) {
		this.maximumMultiplicity = maximumMultiplicity;
	}
	@NotNull
	private String primaryTable;
	@NotNull
	private String secondaryTable;
	private String minimumMultiplicity;
	private String maximumMultiplicity;

}
