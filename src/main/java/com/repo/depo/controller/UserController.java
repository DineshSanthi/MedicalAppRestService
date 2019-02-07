package com.repo.depo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.repo.depo.authentication.DSResponse;
import com.repo.depo.authentication.SmartGWTDSResponse;
import com.repo.depo.model.User;
import com.repo.depo.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    @RequestMapping(value ="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<SmartGWTDSResponse> getUser() {
    	DSResponse dsResponse = new DSResponse();
    	List<User> users = this.userRepository.findAll();
    	dsResponse.setData(users.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);
    } 	
    
    @RequestMapping(value ="/relatedUsers", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
    public List<User> getUserList() {
    	List<User> users = this.userRepository.findAll();
        return users;
    } 
	
	@RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@Valid @RequestBody User user) {
		ObjectId objectId = new ObjectId();
		user.setId(objectId.toString());
		this.userRepository.insert(user);
		Object[] data = new Object[1];
		data[0] = user;
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(data);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=User/json", "Content-Type=User/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody User user) {
		this.userRepository.save(user);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.userRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@GetMapping("/{id}")
	public Optional<User> getById(@PathVariable("id") String id) {
		Optional<User> user = this.userRepository.findById(id);

		return user;
	}

}
