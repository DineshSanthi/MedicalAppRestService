package com.repo.depo.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import com.repo.depo.model.Application;

@Component
public class EventListener extends AbstractMongoEventListener<Object> {

	@Override
	public void onBeforeSave(BeforeSaveEvent<Object> event) {
		
		System.out.println("Before Save is getting called from class ---> " + event.getSource().getClass());
		
	}
	
	
}
