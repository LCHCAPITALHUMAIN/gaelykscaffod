package com.kyub.gaelyk.scaffold.conversion;

import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public abstract class Converter<T> {	
	public abstract T convert(Object p, FieldDescriptor dsr) throws Exception ;
}
