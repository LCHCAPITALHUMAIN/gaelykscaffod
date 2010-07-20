package com.kyub.gaelyk.scaffold.validation;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.appengine.api.datastore.Entity;
import com.kyub.gaelyk.scaffold.MessageHolder;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class ValidationEngine {
	
	public ValidationResult validate(Map pogoVals, Map pogoDescr){		
		ValidationResult result = new ValidationResult();
		return validate(pogoVals, pogoDescr, result);
	}

	public ValidationResult validate(Map pogoVals, Map pogoDescr,	MessageHolder result) {
		System.out.println(" *************** Validation: \n "+ pogoVals + " " + pogoDescr);
		Iterator iterator = pogoDescr.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			FieldDescriptor dsr = (FieldDescriptor) entry.getValue();
			String propName = entry.getKey().toString();
		
			System.out.println(propName + " " +pogoVals.get(propName));
			
			if(pogoVals.containsKey(propName) ){
				if(pogoVals.get(propName) != null &&pogoVals.get(propName).toString().trim().length() > 0){
						//TODO extravalidation	
					System.err.println("***VALISDATION SHOUD OCCUR! ");
				}else{
					if(dsr.mandatory){
						result.add(propName, "Can not be null");
					}
				}
				
			}
			
			
		}
		
		return  new ValidationResult(result.getMessages());
	}

}
