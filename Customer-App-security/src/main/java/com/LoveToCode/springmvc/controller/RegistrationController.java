package com.LoveToCode.springmvc.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LoveToCode.springmvc.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserDetailsManager userDetailsManager;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(CrmUser.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {

		CrmUser user = new CrmUser();

		model.addAttribute("theUser", user);

		return "registration";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("theUser") CrmUser user, BindingResult theBindingResult,
			Model model) {

		String userName = user.getUserName();

		myLogger.info("The person's user name is?" + userName);

		// form validation
		if (theBindingResult.hasErrors()) {
			CrmUser theUser = new CrmUser();

			model.addAttribute("theUser", theUser);
			model.addAttribute("registrationError", "User name/password cannot be empty");

			myLogger.warning("The password/ Username cannot be empty.");
			return "registration";
		}

		// check the database to check if the user exist
		boolean userExist = doesUserExist(userName);

		if (userExist) {
			model.addAttribute("theUser", new CrmUser());
			model.addAttribute("registrationError", "User name already exist");

			myLogger.warning("User name already exists.");
			return "registration";
		}

		// the user has passed all the validation checks hence lets register him/her

		// encode the user password and prepend the encoding algorithm id.
		String encodedPassword = passwordEncoder.encode(user.getPassword());

		encodedPassword = "{bcrypt}" + encodedPassword;

		// give user default default role of employee
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE" , "ROLE_MANAGER");

		// create user object (from Spring Security framework)
       User theUser = new User(userName, encodedPassword, authorities);


		// save user in the database
		userDetailsManager.createUser(theUser);
		return "registration-confirmation";
	}

	private boolean doesUserExist(String userName) {
		myLogger.info("Checking if user exist!");

		// check the database if the user exist.
		boolean exist = userDetailsManager.userExists(userName);

		myLogger.info("User " + userName + " exits " + exist);
		return exist;
	}
}
