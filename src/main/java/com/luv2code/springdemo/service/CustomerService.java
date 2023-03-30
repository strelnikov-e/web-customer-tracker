package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.user.CrmCustomer;

public interface CustomerService {

	public List<Customer> getCustomers(int theSortField);

	public void saveCustomer(CrmCustomer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);

	public Customer findCustomer(CrmCustomer theCustomer);
}
