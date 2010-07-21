package com.kyub.gaelyk.scaffold.conversion.converters;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;
import com.kyub.gaelyk.scaffold.meta.RelationDescriptor;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class RelationConverter extends Converter<Entity> {

	DatastoreService datastore;
	
	
	
	public RelationConverter(DatastoreService datastore) {
		super();
		this.datastore = datastore;
	}



	@Override
	public Entity convert(Object p, FieldDescriptor dsr) throws Exception {
		
		RelationDescriptor rel = (RelationDescriptor)dsr;
		Key key =  new Builder(rel.targetPogo,new Long(String.valueOf(p))).getKey();
		Entity entity = datastore.get(key);
		
		System.out.println("Loaded entity " + entity);
		
		return entity;
	}

}
