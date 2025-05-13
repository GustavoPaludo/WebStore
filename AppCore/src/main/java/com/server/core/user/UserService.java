package com.server.core.user;

import com.server.core.user.model.UserAuthenticationReturn;
import com.server.core.user.model.UserFormModel;
import com.server.core.user.model.UserRegisterModel;

public interface UserService {

	public UserAuthenticationReturn login(UserFormModel userFormModel);

	public UserAuthenticationReturn register(UserRegisterModel userRegisterModel);
}
