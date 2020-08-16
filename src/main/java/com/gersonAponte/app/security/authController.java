package com.gersonAponte.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest autenthicationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					autenthicationRequest.getUsername(), autenthicationRequest.getPassword()));
		} catch (Error e) { 
			throw new Exception("Incorrect userName or Password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(autenthicationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}
