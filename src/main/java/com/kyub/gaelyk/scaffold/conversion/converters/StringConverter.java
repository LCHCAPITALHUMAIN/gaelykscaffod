package com.kyub.gaelyk.scaffold.conversion.converters;

import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class StringConverter extends Converter<String> {

	@Override
	public String convert(Object p, FieldDescriptor dsr) throws Exception {
		
		return String.valueOf(p);
	}

}
