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
import com.repo.depo.model.Application;
import com.repo.depo.model.GlobalProperties;
import com.repo.depo.repository.ApplicationRepository;
import com.repo.depo.repository.GlobalPropertiesRepository;

@RestController
@RequestMapping("/globalproperties")
public class GlobalPropertiesController {
	
	private GlobalPropertiesRepository globalPropertiesRepository;

	public GlobalPropertiesController(GlobalPropertiesRepository globalPropertiesRepository) {
		this.globalPropertiesRepository = globalPropertiesRepository;
	}

    @RequestMapping(value ="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<SmartGWTDSResponse> bookInfo3() {
    	DSResponse dsResponse = new DSResponse();
    	List<GlobalProperties> globalProps = this.globalPropertiesRepository.findAll();
    	dsResponse.setData(globalProps.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);
    } 	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@Valid @RequestBody GlobalProperties globalProps) {
		ObjectId objectId = new ObjectId();
		globalProps.setId(objectId.toString());
		this.globalPropertiesRepository.insert(globalProps);
		Object[] data = new Object[1];
		data[0] = globalProps;
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(data);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=application/json", "Content-Type=application/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody GlobalProperties globalProps) {
		this.globalPropertiesRepository.save(globalProps);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.globalPropertiesRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@GetMapping("/{id}")
	public Optional<GlobalProperties> getById(@PathVariable("id") String id) {
		Optional<GlobalProperties> globalProps = this.globalPropertiesRepository.findById(id);

		return globalProps;
	}

}
