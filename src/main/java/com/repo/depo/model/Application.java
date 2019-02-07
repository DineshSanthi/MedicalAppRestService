package com.repo.depo.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.repo.depo.customvalidation.ValidateName;

@Document(collection = "Application")
public class Application  {
	
	private ObjectId appId;
	
	@Indexed(unique=true)
	@NotNull
	@ValidateName(message="Please enter a valid name")
	private String appName;
	private String appDescription;
	private String messagingFeature;
	public String getMessagingFeature() {
		return messagingFeature;
	}
	public void setMessagingFeature(String messagingFeature) {
		this.messagingFeature = messagingFeature;
	}
	private String mobileApplication;
	private String webApplication;
	private String defaultTheme;
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
	@Id
    private String id;
	private List<Language> language;
	
	public List<Language> getLanguage() {
		return language;
	}
	public void setLanguage(List<Language> language) {
		this.language = language;
	}
	
	public String getMobileApplication() {
		return mobileApplication;
	}
	public void setMobileApplication(String mobileApplication) {
		this.mobileApplication = mobileApplication;
	}
	public String getWebApplication() {
		return webApplication;
	}
	public void setWebApplication(String webApplication) {
		this.webApplication = webApplication;
	}
	public String getDefaultTheme() {
		return defaultTheme;
	}
	public void setDefaultTheme(String defaultTheme) {
		this.defaultTheme = defaultTheme;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ObjectId getAppId() {
		return appId;
	}
	public void setAppId(ObjectId appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	

}
