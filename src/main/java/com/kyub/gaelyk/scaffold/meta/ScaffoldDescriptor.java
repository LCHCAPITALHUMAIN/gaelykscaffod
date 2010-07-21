package com.kyub.gaelyk.scaffold.meta;

public class ScaffoldDescriptor extends EntityDescriptor {
	
	private String scaffoldName;

	public ScaffoldDescriptor(String scaffoldName,EntityDescriptor parent ) {
		super();
		this.scaffoldName = scaffoldName;
		this.detailProperties = parent.detailProperties;
		this.editProperties = parent.editProperties;
		this.entityName = parent.entityName;
		this.insertProperties = parent.insertProperties;
		this.listProperties = parent.listProperties;
		this.entityStruct = parent.entityStruct;
		this.searchProperties = parent.searchProperties;
	}
	
	

}
