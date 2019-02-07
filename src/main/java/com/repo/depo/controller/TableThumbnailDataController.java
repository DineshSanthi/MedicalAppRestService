package com.repo.depo.controller;

import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.repo.depo.model.TableThumbnailData;
import com.repo.depo.repository.TableThumbnailDataRepository;

@RestController
@RequestMapping("/tablethumbnail")
public class TableThumbnailDataController {

	private TableThumbnailDataRepository tableThumbnailDataRepository;

	public TableThumbnailDataController(TableThumbnailDataRepository tableThumbnailDataRepository) {
		this.tableThumbnailDataRepository = tableThumbnailDataRepository;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public String singleFileUpload(@RequestParam("image") MultipartFile multipart,@RequestParam("id") String id,@RequestParam("docType") String docType) {
	    try {
	        TableThumbnailData document = new TableThumbnailData();
	        document.setId(new ObjectId(id));;
	        document.setDocType(docType);
	        document.setImage(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
	        tableThumbnailDataRepository.save(document);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "failure";
	    }
		return "success";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable("id") String id) {
		Optional<TableThumbnailData> document = tableThumbnailDataRepository.findById(new ObjectId(id));
		return ResponseEntity.ok()
	            .contentLength(document.get().getImage().getData().length)
	            .contentType(MediaType.IMAGE_PNG)
	            .body(document.get().getImage().getData());
	}
	
}
