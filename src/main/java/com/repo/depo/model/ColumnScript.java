package com.repo.depo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ColumnScript")
public class ColumnScript {
	
	@Id
    @Field
    private ObjectId id;
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
	
}
