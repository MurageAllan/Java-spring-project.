package com.LoveToCode.springboot.controller;

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

import com.LoveToCode.springboot.User.EmployeeUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserDetailsManager userDetailsManager;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@InitBinder // This annotation pre-process all web request coming into our controller
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // This class trims the whitespace and
																					// trim it to null if it is entirely
																					// whitespace
		dataBinder.registerCustomEditor(EmployeeUser.class, stringTrimmerEditor); //
	}

	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {

		EmployeeUser user = new EmployeeUser();

		model.addAttribute("theUser", user);
		return "registration";
	}

	@PostMapping("/processRegistrationForm")
	public String processingRegistration(@Valid @ModelAttribute("theUser") EmployeeUser employeeUser,
			BindingResult theBindingResult, Model model) {
		String userName = employeeUser.getUserName();

		myLogger.info("The person's user name is " + userName);

		if (theBindingResult.hasErrors()) {
			EmployeeUser theUser = new EmployeeUser();

			model.addAttribute("theUser", theUser);
			model.addAttribute("registrationError", "Username / password cannot be empty.");

			myLogger.warning("The password / Username cannot be empty");

			return "registration";
		}

		boolean userExist = doesUserExist(userName);

		if (userExist) {
			model.addAttribute("theUser", new EmployeeUser());
			model.addAttribute("registrationError", "User with that username already exists");

			myLogger.warning("User name already exist.");

			return "registration";
		}

		String encodedPassword = passwordEncoder.encode(employeeUser.getPassword());
		encodedPassword = "{bcrypt}" + encodedPassword;

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
		
		User user = new User(userName, encodedPassword, authorities);

		userDetailsManager.createUser(user);
		return "registration-confirmation";
	}

	private boolean doesUserExist(String userName) {
		myLogger.info("Checking if user exist");

		boolean exist = userDetailsManager.userExists(userName);

		myLogger.info("User" + userName + "exists" + exist);
		return exist;
	}
}
