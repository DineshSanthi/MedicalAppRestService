package com.repo.depo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class CustomConverter implements Converter<Object, DBObject> {
    @Override
    public DBObject convert(Object event) {
/*        DBObject doc = new BasicDBObject ();
        doc.put("_id", event.getId());
        // …
        doc.put("sample", "test");
        for (Map.Entry<String, Object> entry : event.getFields().entrySet()) {
            doc.put(entry.getKey(), entry.getValue());
        }
        return doc;*/
    	DBObject doc = new BasicDBObject ();
    	return doc;
    }
}