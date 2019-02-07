package com.repo.depo.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.repo.depo.customvalidation.ValidateName;

@Document(collection = "Column")
public class Column  {

	@Id
	private String id;
	private String tableName;
	private String appName;
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	private String columnName;
	private Integer columnSize;
	private String columnType;
	private String required;
	private String disabled;
	private String readOnly;
	private String hidden;
	private String category;
	private String columnDesc;
	private String reference;
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getColumnDesc() {
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}

	private String descTamil;
	public String getDescTamil() {
		return descTamil;
	}

	public void setDescTamil(String descTamil) {
		this.descTamil = descTamil;
	}

	public String getDescTelugu() {
		return descTelugu;
	}

	public void setDescTelugu(String descTelugu) {
		this.descTelugu = descTelugu;
	}

	private String descTelugu;

	private Object languages;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Object getLanguages() {
		return languages;
	}

	public void setLanguages(Object languages) {
		this.languages = languages;
	}

	

}
