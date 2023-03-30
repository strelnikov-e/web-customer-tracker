package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.user.CrmCustomer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional("transactionManager")
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}

	@Override
	@Transactional("transactionManager")
	public void saveCustomer(CrmCustomer crmCustomer) {
		Customer customer = new Customer();
		// assign user details to the user object
		if (crmCustomer.getId() != 0) {
			customer.setId(crmCustomer.getId());
		}
		customer.setFirstName(crmCustomer.getFirstName());
		customer.setLastName(crmCustomer.getLastName());
		customer.setEmail(crmCustomer.getEmail());
		// save user in th database
		customerDAO.saveCustomer(customer);
		
	}

	@Override
	@Transactional("transactionManager")
	public Customer getCustomer(int theId) {
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional("transactionManager")
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);
		
	}

	@Override
	@Transactional("transactionManager")
	public List<Customer> searchCustomers(String theSearchName) {
		return customerDAO.searchCustomers(theSearchName);
	}

	@Override
	@Transactional("transactionManager")
	public Customer findCustomer(CrmCustomer theCustomer) {
		List<Customer> customers = getCustomers(0);
		
		for (Customer customer : customers) {
			if (customer.getFirstName().equals(theCustomer.getFirstName()) 
					&& customer.getLastName().equals(theCustomer.getLastName())
					&& customer.getEmail().equals(theCustomer.getEmail())
					&& customer.getId() != theCustomer.getId()) {
				return customer;
			}
		}
		return null;
	}

}
