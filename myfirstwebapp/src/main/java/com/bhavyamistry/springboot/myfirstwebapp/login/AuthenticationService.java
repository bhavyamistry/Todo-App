package com.bhavyamistry.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	public boolean authenticate(String username, String password) {
		boolean isUsernameCorrect = false;
		boolean isPasswordCorrect = false;
		if(username.equalsIgnoreCase("ABC")) {isUsernameCorrect = true;}
		if(password.equals("12345")) {isPasswordCorrect = true;}
		return isUsernameCorrect && isPasswordCorrect;
		
	}
}
