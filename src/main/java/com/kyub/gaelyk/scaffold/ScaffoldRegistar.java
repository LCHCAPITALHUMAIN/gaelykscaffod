package com.kyub.gaelyk.scaffold;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.objectify.ObjectifyService;
import com.kyub.gaelyk.scaffold.meta.EntityDescriptor;
import com.kyub.gaelyk.scaffold.meta.ScaffoldDescriptor;

public class ScaffoldRegistar {
	
	private static Map<String, ScaffoldDescriptor> registryMap = new HashMap<String, ScaffoldDescriptor>();

	public static void registerEntity(String key, EntityDescriptor descriptor) throws Exception{
		if(!registryMap.containsKey(key)){
			ObjectifyService.register(descriptor.getEntityClass());			
		}
		ScaffoldDescriptor sd = new ScaffoldDescriptor(key,descriptor);
		//TODO introspect class
		
		registryMap.put(key, sd);		
		
	}

	public static ScaffoldDescriptor getEntityDescriptor(String key) {
		return registryMap.get(key);
	}
	
	
	
}
