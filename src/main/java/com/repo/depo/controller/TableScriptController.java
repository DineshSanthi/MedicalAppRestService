package com.repo.depo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.repo.depo.authentication.DSResponse;
import com.repo.depo.authentication.SmartGWTDSResponse;
import com.repo.depo.model.Application;
import com.repo.depo.model.Table;
import com.repo.depo.model.TableScript;
import com.repo.depo.model.TableThumbnailData;
import com.repo.depo.repository.ApplicationRepository;
import com.repo.depo.repository.TableScriptRepository;

@RestController
@RequestMapping("/tablescript")
public class TableScriptController {
	
	private TableScriptRepository tableScriptRepository;

	public TableScriptController(TableScriptRepository tableScriptRepository) {
		this.tableScriptRepository = tableScriptRepository;
	}

	
	  @RequestMapping(value ="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	    public ResponseEntity<SmartGWTDSResponse> bookInfo3() {
	    	DSResponse dsResponse = new DSResponse();
	    	List<TableScript> tableScripts = this.tableScriptRepository.findAll();
	    	dsResponse.setData(tableScripts.toArray());
	    	SmartGWTDSResponse response = new SmartGWTDSResponse();
	    	response.setResponse(dsResponse);
	        return ResponseEntity.accepted().body(response);
	    } 	
	  
	  @RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@Valid @RequestBody TableScript tableScript) {
			this.tableScriptRepository.insert(tableScript);
			DSResponse dsResponse = new DSResponse();
	    	dsResponse.setData(null);
	    	SmartGWTDSResponse response = new SmartGWTDSResponse();
	    	response.setResponse(dsResponse);
	        return ResponseEntity.accepted().body(response);		
		}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=application/json", "Content-Type=application/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody TableScript tableScript) {
		this.tableScriptRepository.save(tableScript);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.tableScriptRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}
	
	  @RequestMapping(value ="all/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	    public ResponseEntity<SmartGWTDSResponse> getScript(@PathVariable("id") String id) {
	    	DSResponse dsResponse = new DSResponse();
	    	Optional<TableScript> tableScript = this.tableScriptRepository.findById(id);
	    	Object[]  data = new Object[1];
	    	data[0] = tableScript;
	    	dsResponse.setData(data);
	    	SmartGWTDSResponse response = new SmartGWTDSResponse();
	    	response.setResponse(dsResponse);
	        return ResponseEntity.accepted().body(response);
	     	
	}


}
