package com.kyub.gaelyk.scaffold.conversion.converters;

import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class IntegerConverter extends Converter<Integer> {

	@Override
	public Integer convert(Object p, FieldDescriptor dsr) throws Exception {
		
		return Integer.parseInt(p.toString()); 
	}

}
