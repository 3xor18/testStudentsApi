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

	//Using Implicid Auth (user and password no requerid in the header) Hardcode empty in this method
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthToken() throws Exception {
		//without checking if the credentials are correct
		
		//Making a own AuthRequest
		AuthenticationRequest autenthicationRequestNew = new AuthenticationRequest();
		//set empty for inject this to new token
		autenthicationRequestNew.setUsername("");
		autenthicationRequestNew.setPassword("");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(autenthicationRequestNew.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}
