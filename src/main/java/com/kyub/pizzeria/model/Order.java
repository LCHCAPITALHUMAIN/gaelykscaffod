package com.kyub.pizzeria.model;

import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.Key;

public class Order {
	
	@Id 
	public Long id;
	public String customerMail;
	public Date dataRef;
	public Date issueDate;
	public Key<Pizza> desiredPizza;
	
	
	

}
