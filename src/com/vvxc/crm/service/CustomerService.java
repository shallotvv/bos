package com.vvxc.crm.service;

import java.util.List;

import com.vvxc.crm.domain.Customer;

 
public interface CustomerService {
	
	List<Customer> findnoassociationCustomers();

	List<Customer> findhasassociationCustomers(String decidedZoneId);

	void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);

	Customer  findCustomerByPhonenumber(String phonenumber);
}
