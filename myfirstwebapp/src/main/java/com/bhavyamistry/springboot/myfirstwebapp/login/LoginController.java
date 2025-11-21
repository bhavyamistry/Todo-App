package com.bhavyamistry.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private AuthenticationService authenticationService;
	
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		
//		logger.debug("Request param is {}",name);
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginPage(@RequestParam String name, @RequestParam String password, ModelMap modelmap) {
		if (authenticationService.authenticate(name, password)) {
			modelmap.put("name", name);
			return "welcome";
		}
		modelmap.put("error_message", "Invalid Credentials!");
		return "login";
	}
}
