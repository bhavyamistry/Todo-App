package com.bhavyamistry.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloConroller {
	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello World to SpringBoot!";
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHTML() {
		String s = new String();
		s = "<html><head><title>My First HTML page</title></head><body><p>My First html page with body</p></body></html>";
		return s;
	}
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJSP() {
		return "sayHello";
	}
}
