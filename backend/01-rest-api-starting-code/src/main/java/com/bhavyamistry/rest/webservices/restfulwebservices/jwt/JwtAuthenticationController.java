package com.bhavyamistry.rest.webservices.restfulwebservices.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	private final JwtTokenService tokenService;

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationController(JwtTokenService tokenService, AuthenticationManager authenticationManager) {
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
		logger.info("This URL got hit");
		var authenticationToken = new UsernamePasswordAuthenticationToken(jwtTokenRequest.username(),
				jwtTokenRequest.password());
		logger.info("Authentication Token:" + authenticationToken);
		var authentication = authenticationManager.authenticate(authenticationToken);
		logger.info("Authentication:" + authentication);

		var token = tokenService.generateToken(authentication);
		logger.info("token:" + token);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}
}
