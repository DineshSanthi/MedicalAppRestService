package com.repo.depo.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.repo.depo.authentication.DSResponse;
import com.repo.depo.authentication.SmartGWTDSResponse;
import com.repo.depo.model.Column;
import com.repo.depo.model.Relation;
import com.repo.depo.model.Table;
import com.repo.depo.repository.ColumnRepository;
import com.repo.depo.repository.RelationRepository;

@RestController
@RequestMapping("/relationship")
public class RelationController {
	
	private RelationRepository relationRepository;

	public RelationController(RelationRepository relationRepository) {
		this.relationRepository = relationRepository;
	}

	@RequestMapping(value ="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SmartGWTDSResponse> getAll() {
		DSResponse dsResponse = new DSResponse();
    	List<Relation> relations = this.relationRepository.findAll();
    	dsResponse.setData(relations.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> insert(@RequestBody Relation relation) {
		ObjectId objectId = new ObjectId();
		relation.setId(objectId.toString());
		this.relationRepository.insert(relation);
		Object[] data = new Object[1];
		data[0] = relation;
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(data);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT,produces = "application/json", headers =
	    { "Accept=application/json", "Content-Type=application/json" }, consumes = "application/json" )
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> update(@RequestBody Relation relation) {
		this.relationRepository.save(relation);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("id") String id) {
		this.relationRepository.deleteById(id);
		DSResponse dsResponse = new DSResponse();
    	dsResponse.setData(null);
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);		
	}

	@GetMapping("/{id}")
	public Optional<Relation> getById(@PathVariable("id") String id) {
		Optional<Relation> relation = this.relationRepository.findById(id);

		return relation;
	}
	
	@RequestMapping(value ="/relation/{collectionName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Relation>> getRelation(@PathVariable("collectionName") String tableName) {
    	List<Relation> relation = this.relationRepository.findByPrimaryTable(tableName);
        return ResponseEntity.accepted().body(relation);
	}
	
	@RequestMapping(value ="/application/{appName}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SmartGWTDSResponse> getColumns(@PathVariable("appName") String appName) {
		DSResponse dsResponse = new DSResponse();
		List<Relation> relations = null;
		if(appName.equalsIgnoreCase("null"))
		{
			relations = this.relationRepository.findAll();	
		}
		else
		{
			relations = this.relationRepository.findByAppName(appName);
		}
    	dsResponse.setData(relations.toArray());
    	SmartGWTDSResponse response = new SmartGWTDSResponse();
    	response.setResponse(dsResponse);
        return ResponseEntity.accepted().body(response);
	}
	

}
