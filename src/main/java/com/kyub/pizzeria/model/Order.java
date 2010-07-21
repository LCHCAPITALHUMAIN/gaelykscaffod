package com.kyub.pizzeria.model;

import java.util.Date;

import javax.persistence.Id;

public class Order {
	
	@Id Long id;
	String customarMail;
	Date dataRef;
	Date issueDate;
	
	
	

}
