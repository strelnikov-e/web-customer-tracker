package com.luv2code.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.user.CrmCustomer;
import com.luv2code.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customers from the service
		List<Customer> theCustomers = null;
		
		// check for sort field
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);
		}
		else {
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		theModel.addAttribute("crmCustomer", new CrmCustomer());
		
		return "customer-form";
	}
	
	// maping to <form:form action="saveCustomer" modeAttribute="customer" method="POSt" in customer-form.jsp
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("crmCustomer") CrmCustomer theCustomer, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "customer-form";
		}
		Customer existing = customerService.findCustomer(theCustomer);
		
		if (existing != null) {
			model.addAttribute("crmCustomer", new CrmCustomer());
			model.addAttribute("creationError", "Customer with this name is already registered");
			return "customer-form";
		}
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the customer from the database
		Customer theCustomer = customerService.getCustomer(theId);
		CrmCustomer crmCustomer = new CrmCustomer();
		crmCustomer.setFirstName(theCustomer.getFirstName());
		crmCustomer.setLastName(theCustomer.getLastName());
		crmCustomer.setEmail(theCustomer.getEmail());
		crmCustomer.setId(theCustomer.getId());
		
		// set customer as a model attribure to pre-populate the form
		theModel.addAttribute("crmCustomer", crmCustomer);
		
		// send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		// search customer from the service
		List<Customer> customers = customerService.searchCustomers(theSearchName);
		
		// add the customers to the model
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
}
