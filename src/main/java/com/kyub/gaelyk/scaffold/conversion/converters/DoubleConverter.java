package com.kyub.gaelyk.scaffold.conversion.converters;

import com.kyub.gaelyk.scaffold.conversion.Converter;

public class DoubleConverter extends Converter<Double> {

	@Override
	public Double convert(Object p) throws Exception {
		
		return Double.valueOf(String.valueOf(p));
	}

}
