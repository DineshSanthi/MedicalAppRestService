package com.repo.depo.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.repo.depo.authentication.DSResponse;
import com.repo.depo.authentication.SmartGWTDSResponse;
import com.repo.depo.configuration.ZXingHelper;
import com.repo.depo.model.Column;
import com.repo.depo.model.TableThumbnailData;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private RelationController relationController;
	
	 @Field
	 private Binary qrCode;

	public Binary getQrCode() {
		return qrCode;
	}

	public void setQrCode(Binary qrCode) {
		this.qrCode = qrCode;
	}

	@RequestMapping(value = "/{entityName}/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SmartGWTDSResponse> insert(@PathVariable("entityName") String collectionName,
			@Valid @RequestBody String jsonString) {
		ObjectId objectId = new ObjectId();
		Document doc = Document.parse(jsonString);
		doc.put("_id", objectId);
		/* try {
	            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
	            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	            BitMatrix matrix = new MultiFormatWriter().encode(objectId.toString(),
	                BarcodeFormat.QR_CODE, 200, 200, hintMap);
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            MatrixToImageWriter.writeToStream(matrix,"png",byteArrayOutputStream);
	        	doc.put("qrCode",  new Binary(BsonBinarySubType.BINARY, byteArrayOutputStream.toByteArray()));
	            System.out.println("QR Code image created successfully!");
	        } catch (Exception e) {
	            System.err.println(e);
	        }
		*/
		mongoTemplate.insert(doc, collectionName);
		doc.put("id", doc.getObjectId("_id").toString());
		doc.remove("_id");
		DSResponse dsResponse = new DSResponse();
		Object[] data = new Object[1];
		data[0] = doc;
		dsResponse.setData(data);
		SmartGWTDSResponse response = new SmartGWTDSResponse();
		response.setResponse(dsResponse);
		return ResponseEntity.accepted().body(response);
	}

	@RequestMapping(value = "/{entityName}/all", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmartGWTDSResponse> getAll(@PathVariable("entityName") String collectionName,
			@Valid @RequestBody String jsonString) {
		Document doc = Document.parse(jsonString);
		String value = doc.get("data").toString();
		Query query = new Query();
		Document docData = (Document)doc.get("data");
		if (docData.size() > 0) {
			for (String key : docData.keySet()) {
				if(key.equalsIgnoreCase("id"))
				{
					//ObjectId obj = (ObjectId)(docData.get(key)) ;
					query.addCriteria(new Criteria("_id").is(docData.get(key)));
				}
				else
				{
				query.addCriteria(new Criteria(key).is(docData.get(key)));
				}
			}
		}

		List<BasicDBObject> data = mongoTemplate.find(query, BasicDBObject.class, collectionName);
		for (int i = 0; i < data.size(); i++) {
			data.get(i).put("id", data.get(i).getObjectId("_id").toString());
			data.get(i).remove("_id");
			
		}
		DSResponse dsResponse = new DSResponse();
		dsResponse.setData(data.toArray());
		SmartGWTDSResponse response = new SmartGWTDSResponse();
		response.setResponse(dsResponse);
		return ResponseEntity.accepted().body(response);
	}

	/*
	 * @RequestMapping(value ="/{entityName}/{id}", method=RequestMethod.GET,
	 * produces=MediaType.APPLICATION_JSON_VALUE ) public
	 * ResponseEntity<SmartGWTDSResponse> getAll(@PathVariable("entityName") String
	 * collectionName,@PathVariable("id") String id) { Query query = new Query();
	 * query.addCriteria(new Criteria("_id").is(id)); List<BasicDBObject> data =
	 * mongoTemplate.find(query, BasicDBObject.class, collectionName); DSResponse
	 * dsResponse = new DSResponse(); dsResponse.setData(data.toArray());
	 * SmartGWTDSResponse response = new SmartGWTDSResponse();
	 * response.setResponse(dsResponse); return
	 * ResponseEntity.accepted().body(response); }
	 */

	@RequestMapping(value = "/{entityName}/update/{id}", method = RequestMethod.PUT, produces = "application/json", headers = {
			"Accept=application/json", "Content-Type=application/json" }, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<SmartGWTDSResponse> update(@PathVariable("entityName") String collectionName,
			@PathVariable("id") String id, @Valid @RequestBody String jsonString) {
		Document doc = Document.parse(jsonString);
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(id));
		DBObject dbDoc = new BasicDBObject();
		doc.remove("id");
		mongoTemplate.getConverter().write(doc, dbDoc);

		Update update = Update.fromDBObject(dbDoc);
		mongoTemplate.upsert(query, update, collectionName);

		DSResponse dsResponse = new DSResponse();
		dsResponse.setData(null);
		SmartGWTDSResponse response = new SmartGWTDSResponse();
		response.setResponse(dsResponse);
		return ResponseEntity.accepted().body(response);
	}

	@RequestMapping(value = "/{entityName}/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<SmartGWTDSResponse> delete(@PathVariable("entityName") String collectionName,
			@PathVariable("id") String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(id));
		mongoTemplate.remove(query, collectionName);
		DSResponse dsResponse = new DSResponse();
		dsResponse.setData(null);
		SmartGWTDSResponse response = new SmartGWTDSResponse();
		response.setResponse(dsResponse);
		return ResponseEntity.accepted().body(response);
	}
	
/*	@RequestMapping(value = "/{entityName}/qrcode/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<SmartGWTDSResponse> generateQrCode(@PathVariable("entityName") String collectionName,@PathVariable("id") String id) {
		
		 try {
	            String filePath = "C:\\QRCODE\\"+collectionName+"_"+id+".png";
	            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
	            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	            BitMatrix matrix = new MultiFormatWriter().encode(id,
	                BarcodeFormat.QR_CODE, 200, 200, hintMap);
	            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
	                .lastIndexOf('.') + 1), new File(filePath));
	            System.out.println("QR Code image created successfully!");
	        } catch (Exception e) {
	            System.err.println(e);
	        }
		DSResponse dsResponse = new DSResponse();
		dsResponse.setData(null);
		SmartGWTDSResponse response = new SmartGWTDSResponse();
		response.setResponse(dsResponse);
		return ResponseEntity.accepted().body(response);
	}*/
	
	
	/*
	 * @RequestMapping(value ="/{entityName}/query/{value}",
	 * method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE ) public
	 * ResponseEntity<SmartGWTDSResponse>
	 * getFilteredData(@PathVariable("entityName") String
	 * collectionName,@Valid @RequestBody String jsonString) { Query query = new
	 * Query(); query.addCriteria(new Criteria("First_Name").is(value));
	 * List<BasicDBObject> data = mongoTemplate.find(query, BasicDBObject.class,
	 * collectionName); DSResponse dsResponse = new DSResponse();
	 * dsResponse.setData(data.toArray()); SmartGWTDSResponse response = new
	 * SmartGWTDSResponse(); response.setResponse(dsResponse); return
	 * ResponseEntity.accepted().body(response); }
	 */

}
