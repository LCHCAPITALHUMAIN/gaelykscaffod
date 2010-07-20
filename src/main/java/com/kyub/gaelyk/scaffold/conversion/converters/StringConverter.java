package com.kyub.gaelyk.scaffold.conversion.converters;

import com.kyub.gaelyk.scaffold.conversion.Converter;

public class StringConverter extends Converter {

	@Override
	public Object convert(Object p) throws Exception {
		
		return String.valueOf(p);
	}

}
