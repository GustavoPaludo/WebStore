package com.server.controllers.authenticated;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/authenticated/user")
public class UserWSController {

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Void> logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    return ResponseEntity.ok().build();
	}
}
