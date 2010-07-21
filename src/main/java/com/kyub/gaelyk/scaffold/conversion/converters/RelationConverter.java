package com.kyub.gaelyk.scaffold.conversion.converters;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;
import com.kyub.gaelyk.scaffold.meta.RelationDescriptor;
import com.google.appengine.api.datastore.KeyFactory.Builder;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class RelationConverter extends Converter<Key> {



	@Override
	public Key convert(Object p, FieldDescriptor dsr) throws Exception {
		
		
		
		RelationDescriptor rel = (RelationDescriptor)dsr;
		
		return new Key(Class.forName(rel.targetPogo),new Long(String.valueOf(p)));
		
		
		
	}

}
