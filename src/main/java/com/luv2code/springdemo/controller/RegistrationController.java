package com.luv2code.springdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.service.UserService;
import com.luv2code.springdemo.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	// support to trim empty string to null
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		theModel.addAttribute("crmUser", new CrmUser());
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser,
							BindingResult theBindingResult, Model theModel) {
		// form validation
		if (theBindingResult.hasErrors()) {
			return "registration-form";
		}
		// check the database if user already exists
		User existing = userService.findUserByName(crmUser.getUserName());
		if (existing != null) {
			// clear the form
			theModel.addAttribute("crmUser", new CrmUser());
			// show error message
			theModel.addAttribute("registrationError", "User name already exists");
			return "registration-form";
		}
		userService.save(crmUser);
		return "registration-confirmation";
	}
}
