package com.repo.depo.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.repo.depo.customvalidation.ValidateName;

@Document(collection = "Table")
public class Table  {

	@Id
	private String id;
	
	private String appName;
	
	@NotNull
	@Size(min=2, message="Name should have atleast 2 characters")
	@Indexed(unique=true)
	@ValidateName(message="Please ebter a valid name")
	private String tableName;
	private String tableDescription;
	private String descTamil;
	private String image = "sample.svg";
	private String descTelugu;
	@NotNull
	private String category;
	private Long ttl;
	private List<Language> language;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
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
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
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
	public String getTableDescription() {
		return tableDescription;
	}
	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}
	public Long getTtl() {
		return ttl;
	}
	public void setTtl(Long ttl) {
		this.ttl = ttl;
	}
	public List<Language> getLanguage() {
		return language;
	}
	public void setLanguage(List<Language> language) {
		this.language = language;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
