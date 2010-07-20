package com.kyub.gaelyk.scaffold.conversion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.kyub.gaelyk.scaffold.conversion.converters.DateConverter;
import com.kyub.gaelyk.scaffold.conversion.converters.DoubleConverter;
import com.kyub.gaelyk.scaffold.conversion.converters.StringConverter;
import com.kyub.gaelyk.scaffold.conversion.converters.TimeStampDefaulter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class ConversionEngine {
	
	private Map<String, Converter> converters;
	
	private Map<String, Converter> defaulters;
	
	
	
	public ConversionEngine() {
	
		converters = new HashMap<String, Converter>();
		converters.put("String", new StringConverter());
		converters.put("Double", new DoubleConverter());
		converters.put("Date", new DateConverter());
		
		defaulters  = new HashMap<String, Converter>();
		
		defaulters.put("TimeStamp", new TimeStampDefaulter());
		
		
		
	}



	public  ConversionResult convert(Map<String, String> params, Map<String ,FieldDescriptor> pogoDescr) {
		
		System.out.println(" *************** Conversion: \n"+ params + " " + pogoDescr);
		
		ConversionResult result = new ConversionResult();
		Iterator iterator = pogoDescr.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			FieldDescriptor dsr = (FieldDescriptor) entry.getValue();
			String propName = entry.getKey().toString();
			System.out.println(propName + " " + entry.getValue()+ " |" + params.get(propName) +"|");
			if(params.get(propName) != null && params.get(propName).toString().trim().length() > 0){
				
				doConvert(params, result, dsr, propName);
				
			}else{
				
				applyDefaults(params, result, dsr, propName);
			}
		}	
		
		
		return result;
		
	}



	public void applyDefaults(Map<String, String> params,
			ConversionResult result, FieldDescriptor dsr, String propName) {
		if(defaulters.get(dsr.typeName)!= null){
			try{
			result.put(propName, defaulters.get(dsr.typeName).convert(params.get(propName)));
			}catch(Exception e){
				result.add(propName, e.getMessage());
			}
			
		}else{
		
			result.put(propName, params.get(propName));
		}
	}



	public void doConvert(Map<String, String> params, ConversionResult result,
			FieldDescriptor dsr, String propName) {
		Converter c = converters.get(dsr.typeName);
		try{
			result.put(propName, c.convert(params.get(propName)));
		}
		catch(Exception e){
			result.add(propName, e.getMessage());
		}
	}

}
