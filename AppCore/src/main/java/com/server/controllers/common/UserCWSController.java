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
import com.server.core.user.model.UserFormModel;
import com.server.core.user.model.UserRegisterModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/common/user")
public class UserCWSController {

	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ProblemList> login(HttpServletRequest request, @RequestBody UserFormModel userFormModel) {
		ProblemList problemList = this.userService.login(userFormModel, request);

		if (problemList != null && problemList.hasAny()) {
			return new ResponseEntity<ProblemList>(problemList, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<ProblemList>(problemList, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ProblemList> register(HttpServletRequest request, @RequestBody UserRegisterModel userRegisterModel) {
		ProblemList problemList = this.userService.register(userRegisterModel, request);

		if (problemList != null && problemList.hasAny()) {
			return new ResponseEntity<ProblemList>(problemList, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<ProblemList>(problemList, HttpStatus.OK);
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
