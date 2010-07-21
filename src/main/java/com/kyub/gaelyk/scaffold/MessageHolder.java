package com.kyub.gaelyk.scaffold;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageHolder {
	
   protected Map<String,String> messages= new HashMap<String,String>();	
	
	public Object add(String fieldName, String message) {
		return messages.put(fieldName, message);
	}

	public Map<String,String> getMessages() {
		return messages; //TODO unmodificable map??
	}
	
	

}
