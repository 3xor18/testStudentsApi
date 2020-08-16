package com.gersonAponte.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthToken() throws Exception {
		AuthenticationRequest autenthicationRequestNew = new AuthenticationRequest();
		autenthicationRequestNew.setUsername("");
		autenthicationRequestNew.setPassword("");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(autenthicationRequestNew.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}
