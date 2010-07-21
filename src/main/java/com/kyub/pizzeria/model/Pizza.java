package com.kyub.pizzeria.model;

import javax.persistence.Id;

public class Pizza {
	
	@Id 
	public Long id;
	
	public String name;
	
	public Double price;
	
	public String description;

	@Override
	public String toString() {
		return name;
	}
	
	

}
