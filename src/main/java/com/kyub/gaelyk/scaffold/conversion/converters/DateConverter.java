package com.kyub.gaelyk.scaffold.conversion.converters;


import java.text.SimpleDateFormat;
import java.util.Date;


import com.kyub.gaelyk.scaffold.conversion.Converter;
import com.kyub.gaelyk.scaffold.meta.FieldDescriptor;

public class DateConverter extends Converter<Date> {
	
	SimpleDateFormat shortFormat = new SimpleDateFormat("dd/MM/yyyy");
	

	@Override
	public Date convert(Object p, FieldDescriptor dsr) throws Exception {		
		
		return shortFormat.parse(p.toString());
	}

}
