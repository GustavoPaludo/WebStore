package com.server.controllers.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.server.core.common.ProblemList;
import com.server.core.user.UserService;
import com.server.core.user.model.UserAuthenticationReturn;
import com.server.core.user.model.UserFormModel;
import com.server.core.user.model.UserRegisterModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/common/user")
public class UserCWSController {

	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserAuthenticationReturn> login(HttpServletRequest request, @RequestBody UserFormModel userFormModel) {
		UserAuthenticationReturn userAuthenticationReturn = this.userService.login(userFormModel, request);

		if (userAuthenticationReturn.getProblemList() != null && userAuthenticationReturn.getProblemList().hasAny()) {
			return new ResponseEntity<UserAuthenticationReturn>(userAuthenticationReturn, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<UserAuthenticationReturn>(userAuthenticationReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserAuthenticationReturn> register(HttpServletRequest request, @RequestBody UserRegisterModel userRegisterModel) {
		UserAuthenticationReturn userAuthenticationReturn = this.userService.register(userRegisterModel, request);

		if (userAuthenticationReturn.getProblemList() != null && userAuthenticationReturn.getProblemList().hasAny()) {
			return new ResponseEntity<UserAuthenticationReturn>(userAuthenticationReturn, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<UserAuthenticationReturn>(userAuthenticationReturn, HttpStatus.OK);
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
