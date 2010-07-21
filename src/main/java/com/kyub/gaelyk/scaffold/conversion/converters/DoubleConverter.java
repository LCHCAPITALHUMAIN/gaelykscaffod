package com.kyub.gaelyk.scaffold.conversion.converters;

import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class DoubleConverter extends Converter<Double> {

	@Override
	public Double convert(Object p, FieldDescriptor dsr) throws Exception {
		
		return Double.valueOf(String.valueOf(p));
	}

}
