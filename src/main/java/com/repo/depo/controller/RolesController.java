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
import com.repo.depo.model.Roles;
import com.repo.depo.repository.RolesRepository;

@RestController
@RequestMapping("/roles")
public class RolesController {
	
	private RolesRepository rolesRepository;

	public RolesController(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

    @RequestMapping(value ="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<SmartGWTDSResponse> bookInfo3() {
    	DSResponse dsResponse = new DSResponse();
    	List<Roles> roles = this.rolesRepository.findAll();
    	dsResponse.setData(roles.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);
    } 	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@Valid @RequestBody Roles role) {
		ObjectId objectId = new ObjectId();
		role.setId(objectId.toString());
		this.rolesRepository.insert(role);
		Object[] data = new Object[1];
		data[0] = role;
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(data);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=Roles/json", "Content-Type=Roles/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody Roles role) {
		this.rolesRepository.save(role);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.rolesRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@GetMapping("/{id}")
	public Optional<Roles> getById(@PathVariable("id") String id) {
		Optional<Roles> role = this.rolesRepository.findById(id);

		return role;
	}

}
