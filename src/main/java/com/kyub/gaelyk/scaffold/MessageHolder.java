package com.kyub.gaelyk.scaffold;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageHolder {
	
   protected Map messages= new HashMap();	
	
	public Object add(String fieldName, String message) {
		return messages.put(fieldName, message);
	}

	public Map getMessages() {
		return messages; //TODO unmodificable map??
	}
	
	

}
