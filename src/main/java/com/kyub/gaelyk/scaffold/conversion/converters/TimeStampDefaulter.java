package com.kyub.gaelyk.scaffold.conversion.converters;

import java.util.Date;

import com.kyub.gaelyk.scaffold.conversion.Converter;

public class TimeStampDefaulter extends Converter<Date> {

	@Override
	public Date convert(Object p) throws Exception {
		
		return new Date();
	}

}
