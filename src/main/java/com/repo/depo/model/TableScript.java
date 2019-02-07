package com.repo.depo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "TableScript")
public class TableScript {
	
	@Id
    @Field
    private ObjectId id;
	private String beforeSave;
	public String getBeforeSave() {
		return beforeSave;
	}

	public void setBeforeSave(String beforeSave) {
		this.beforeSave = beforeSave;
	}

	public String getAfterSave() {
		return afterSave;
	}

	public void setAfterSave(String afterSave) {
		this.afterSave = afterSave;
	}

	public String getBeforeFetch() {
		return beforeFetch;
	}

	public void setBeforeFetch(String beforeFetch) {
		this.beforeFetch = beforeFetch;
	}

	public String getAfterFetch() {
		return afterFetch;
	}

	public void setAfterFetch(String afterFetch) {
		this.afterFetch = afterFetch;
	}

	public String getBeforeDelete() {
		return beforeDelete;
	}

	public void setBeforeDelete(String beforeDelete) {
		this.beforeDelete = beforeDelete;
	}

	public String getAfterDelete() {
		return afterDelete;
	}

	public void setAfterDelete(String afterDelete) {
		this.afterDelete = afterDelete;
	}

	public String getBeforeInsert() {
		return beforeInsert;
	}

	public void setBeforeInsert(String beforeInsert) {
		this.beforeInsert = beforeInsert;
	}

	public String getAfterInsert() {
		return afterInsert;
	}

	public void setAfterInsert(String afterInsert) {
		this.afterInsert = afterInsert;
	}

	private String afterSave;
	private String beforeFetch;
	private String afterFetch;
	private String beforeDelete;
	private String afterDelete;
	private String beforeInsert;
	private String afterInsert;
	public String getRequiredEvent() {
		return requiredEvent;
	}

	public void setRequiredEvent(String requiredEvent) {
		this.requiredEvent = requiredEvent;
	}

	public String getHiddenEvent() {
		return hiddenEvent;
	}

	public void setHiddenEvent(String hiddenEvent) {
		this.hiddenEvent = hiddenEvent;
	}

	public String getDisabledEvent() {
		return disabledEvent;
	}

	public void setDisabledEvent(String disabledEvent) {
		this.disabledEvent = disabledEvent;
	}

	public String getReadOnlyEvent() {
		return readOnlyEvent;
	}

	public void setReadOnlyEvent(String readOnlyEvent) {
		this.readOnlyEvent = readOnlyEvent;
	}

	private String requiredEvent;
	private String hiddenEvent;
	private String disabledEvent;
	private String readOnlyEvent;
	
	
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	

}
