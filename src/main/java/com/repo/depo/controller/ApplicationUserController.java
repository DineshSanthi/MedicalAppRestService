package com.repo.depo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.repo.depo.authentication.DSResponse;
import com.repo.depo.authentication.SmartGWTDSResponse;
import com.repo.depo.model.ApplicationUser;
import com.repo.depo.model.User;
import com.repo.depo.repository.ApplicationUserRepository;

@RestController
@RequestMapping("/applicationuser")
public class ApplicationUserController {
	
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public ApplicationUserController(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@Valid @RequestBody ApplicationUser applicationUser) {
		
		DSResponse dsResponse = new DSResponse();
		ObjectId objectId = new ObjectId();
		applicationUser.setId(objectId.toString());
		this.applicationUserRepository.insert(applicationUser);
		List<ApplicationUser> appUser = mongoTemplate.findAll(ApplicationUser.class);
		List<User> users = userController.getUserList();
		List<User> relatedUsers = new ArrayList<>();
		for (int i = 0; i < appUser.size(); i++) {
			String user = appUser.get(i).getUserName();
			for (int j = 0; j < users.size(); j++) {
				if(users.get(j).getUserName().equalsIgnoreCase(user)) {
					relatedUsers.add(users.get(j));
				}
			}
		}
    	dsResponse.setData(relatedUsers.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=application/json", "Content-Type=application/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody ApplicationUser applicationUser) {
		this.applicationUserRepository.save(applicationUser);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.applicationUserRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	
	@RequestMapping(value ="/application/{appName}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SmartGWTDSResponse> getColumns(@PathVariable("appName") String appName,@RequestBody String jsonString) {
	
		Document doc = Document.parse(jsonString);
		String value = doc.get("data").toString();
		Query query = new Query();
		Document docData = (Document)doc.get("data");
		if (docData.size() > 0) {
			for (String key : docData.keySet()) {
				query.addCriteria(new Criteria(key).is(docData.get(key)));
			}
		}
		DSResponse dsResponse = new DSResponse();
		List<ApplicationUser> applicationUser = mongoTemplate.find(query, ApplicationUser.class);
		List<User> users = userController.getUserList();
		List<User> relatedUsers = new ArrayList<>();
		for (int i = 0; i < applicationUser.size(); i++) {
			String user = applicationUser.get(i).getUserName();
			for (int j = 0; j < users.size(); j++) {
				if(users.get(j).getUserName().equalsIgnoreCase(user)) {
					relatedUsers.add(users.get(j));
				}
			}
		}
	/*	Object[] data = new Object[1];
		data[0] = relatedUsers;*/
		dsResponse.setData(relatedUsers.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);
	}

}
