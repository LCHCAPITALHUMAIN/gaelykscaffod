package com.kyub.gaelyk.scaffold.validation;


import java.util.Map;

import com.kyub.gaelyk.scaffold.MessageHolder;

public class ValidationResult extends MessageHolder{
	
	public ValidationResult() {
		super();				
	}

	public ValidationResult(Map<String,String> messages) {
		super();
		this.messages = messages;
		
	}

	public boolean isValid() {
		return messages.size() == 0;
	}

}
