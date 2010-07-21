package com.kyub.gaelyk.scaffold.meta;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class EntityDescriptor {
	
	public String entityName ;
	
	
	public Map entityStruct;
	
	public List detailProperties ;
	
	public List listProperties ;
	
	public List searchProperties ;
	
	public List insertProperties ;
	
	public List editProperties ;
	
	
	public Class getEntityClass() throws Exception{
		if(entityName == null){
			throw new IllegalStateException("entityName not set");
		}
		return Class.forName(entityName);
		
	}

}
