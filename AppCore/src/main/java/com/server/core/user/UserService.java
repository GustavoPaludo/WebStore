package com.server.core.user;

import com.server.core.user.model.UserAuthenticationReturn;
import com.server.core.user.model.UserFormModel;
import com.server.core.user.model.UserRegisterModel;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

	public UserAuthenticationReturn login(UserFormModel userFormModel, HttpServletRequest httpRequest);

	public UserAuthenticationReturn register(UserRegisterModel userRegisterModel, HttpServletRequest httpRequest);
}
