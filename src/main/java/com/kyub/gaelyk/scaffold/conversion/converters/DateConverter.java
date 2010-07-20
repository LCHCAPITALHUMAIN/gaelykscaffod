package com.kyub.gaelyk.scaffold.conversion.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.kyub.gaelyk.scaffold.conversion.Converter;

public class DateConverter extends Converter<Date> {
	
	SimpleDateFormat shortFormat = new SimpleDateFormat("dd/MM/yyyy");
	

	@Override
	public Date convert(Object p) throws Exception {		
		
		return shortFormat.parse(p.toString());
	}

}
