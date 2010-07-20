package com.kyub.gaelyk.scaffold.conversion.converters;

import com.kyub.gaelyk.scaffold.conversion.Converter;

public class IntegerConverter extends Converter<Integer> {

	@Override
	public Integer convert(Object p) throws Exception {
		
		return Integer.parseInt(p.toString()); 
	}

}
