package com.server.core.user;

import com.server.core.common.ProblemList;
import com.server.core.user.model.UserFormModel;
import com.server.core.user.model.UserRegisterModel;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

	public ProblemList login(UserFormModel userFormModel, HttpServletRequest httpRequest);

	public ProblemList register(UserRegisterModel userRegisterModel, HttpServletRequest httpRequest);
}
