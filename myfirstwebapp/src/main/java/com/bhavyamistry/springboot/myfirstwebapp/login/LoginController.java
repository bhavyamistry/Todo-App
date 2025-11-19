package com.bhavyamistry.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		
//		logger.debug("Request param is {}",name);
		return "login";
	}
	
	@RequestMapping(value="welcome", method=RequestMethod.POST)
	public String welcome(@RequestParam String name, @RequestParam String password, ModelMap modelmap) {
		modelmap.put("name", name);
		modelmap.put("password", password);
		return "welcome";
	}
}
