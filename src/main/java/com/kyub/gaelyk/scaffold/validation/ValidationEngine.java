package com.kyub.gaelyk.scaffold.validation;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import com.kyub.gaelyk.scaffold.MessageHolder;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class ValidationEngine {
	
	public ValidationResult validate(Map<String,String> pogoVals, Map<String,FieldDescriptor> pogoDescr){		
		ValidationResult result = new ValidationResult();
		return validate(pogoVals, pogoDescr, result);
	}

	public ValidationResult validate(Map<String,String> pogoVals, Map<String,FieldDescriptor> pogoDescr,	MessageHolder result) {
		System.out.println(" *************** Validation: \n "+ pogoVals + " " + pogoDescr);
		Iterator<Entry<String,FieldDescriptor>> iterator = pogoDescr.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String,FieldDescriptor> entry =  iterator.next();
			FieldDescriptor dsr =  entry.getValue();
			String propName = entry.getKey().toString();
		
			System.out.println(propName + " " +String.valueOf(pogoVals.get(propName)));
			
			if(pogoVals.containsKey(propName) ){
				if(pogoVals.get(propName) != null && String.valueOf(pogoVals.get(propName)).trim().length() > 0){
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
