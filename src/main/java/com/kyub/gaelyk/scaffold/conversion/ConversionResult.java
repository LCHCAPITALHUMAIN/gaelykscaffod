package com.kyub.gaelyk.scaffold.conversion;

import java.util.HashMap;
import java.util.Map;

import com.kyub.gaelyk.scaffold.MessageHolder;

public class ConversionResult extends MessageHolder {
	
	
	private Map<String, Object> convertedVals = new HashMap<String, Object>();

	public Object put(String key, Object value) {
		return convertedVals.put(key, value);
	}

	public Map<String, Object> getConvertedVals() {
		return convertedVals;
	}
	
	
	

}
