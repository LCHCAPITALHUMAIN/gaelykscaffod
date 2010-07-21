package com.kyub.gaelyk.scaffold.conversion.converters;

import java.util.Date;

import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class TimeStampDefaulter extends Converter<Date> {

	@Override
	public Date convert(Object p, FieldDescriptor dsr) throws Exception {
		
		return new Date();
	}

}
