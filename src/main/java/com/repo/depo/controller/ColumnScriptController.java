package com.repo.depo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.repo.depo.model.ColumnScript;
import com.repo.depo.repository.ColumnScriptRepository;

@RestController
@RequestMapping("/columnscript")
public class ColumnScriptController {
	
	private ColumnScriptRepository columnScriptRepository;

	public ColumnScriptController(ColumnScriptRepository columnScriptRepository) {
		this.columnScriptRepository = columnScriptRepository;
	}

	
	  @RequestMapping(value ="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	    public ResponseEntity<SmartGWTDSResponse> getScripts() {
	    	DSResponse dsResponse = new DSResponse();
	    	List<ColumnScript> columnScripts = this.columnScriptRepository.findAll();
	    	dsResponse.setData(columnScripts.toArray());
	    	SmartGWTDSResponse response = new SmartGWTDSResponse();
	    	response.setResponse(dsResponse);
	        return ResponseEntity.accepted().body(response);
	    } 	
	  
	  @RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@Valid @RequestBody ColumnScript columnScript) {
			this.columnScriptRepository.insert(columnScript);
			DSResponse dsResponse = new DSResponse();
	    	dsResponse.setData(null);
	    	SmartGWTDSResponse response = new SmartGWTDSResponse();
	    	response.setResponse(dsResponse);
	        return ResponseEntity.accepted().body(response);		
		}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=application/json", "Content-Type=application/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody ColumnScript columnScript) {
		this.columnScriptRepository.save(columnScript);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.columnScriptRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}
	
	  @RequestMapping(value ="all/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	    public ResponseEntity<SmartGWTDSResponse> getScript(@PathVariable("id") String id) {
	    	DSResponse dsResponse = new DSResponse();
	    	Optional<ColumnScript> columnScript = this.columnScriptRepository.findById(id);
	    	Object[]  data = new Object[1];
	    	data[0] = columnScript;
	    	dsResponse.setData(data);
	    	SmartGWTDSResponse response = new SmartGWTDSResponse();
	    	response.setResponse(dsResponse);
	        return ResponseEntity.accepted().body(response);
	     	
	}


}
