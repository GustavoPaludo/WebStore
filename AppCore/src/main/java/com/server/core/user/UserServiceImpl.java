package com.server.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.server.core.common.JwtUtil;
import com.server.core.common.PasswordUtils;
import com.server.core.common.Problem;
import com.server.core.common.ProblemList;
import com.server.core.user.dao.User;
import com.server.core.user.dao.UserDAO;
import com.server.core.user.model.UserAuthenticationReturn;
import com.server.core.user.model.UserFormModel;
import com.server.core.user.model.UserRegisterModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Override
	public UserAuthenticationReturn login(UserFormModel userFormModel, HttpServletRequest httpRequest) {
		ProblemList problemList = new ProblemList();
		UserAuthenticationReturn userAuthenticationReturn = new UserAuthenticationReturn();

		User user = this.userDAO.findByEmail(userFormModel.getEmail());

		if (user == null) {
			problemList.add(new Problem("Nenhum usuário cadastrado para o email informado"));
			userAuthenticationReturn.setProblemList(problemList);

			return userAuthenticationReturn;
		}

		if (!PasswordUtils.validatePassword(userFormModel.getPassword(), user.getPassword())) {
			problemList.add(new Problem("Senha incorreta!"));
			userAuthenticationReturn.setProblemList(problemList);

			return userAuthenticationReturn;
		}

		HttpSession session = httpRequest.getSession(true);
		session.setAttribute("userName", user.getName());
		session.setAttribute("userEmail", user.getEmail());
		session.setMaxInactiveInterval(24 * 60 * 60);

		String jwt = JwtUtil.generateToken(user.getEmail(), user.getName());

		userAuthenticationReturn.setJwtToken(jwt);
		userAuthenticationReturn.setProblemList(problemList);

		return userAuthenticationReturn;
	}

	@Override
	public UserAuthenticationReturn register(UserRegisterModel userRegisterModel, HttpServletRequest httpRequest) {
		UserAuthenticationReturn userAuthenticationReturn = new UserAuthenticationReturn();
		ProblemList problemList = new ProblemList();

		User user = new User();
		user.setEmail(userRegisterModel.getEmail());
		user.setFederalIdentification(userRegisterModel.getFedidentification());
		user.setName(userRegisterModel.getName());
		user.setPassword(userRegisterModel.getPassword());
		user.setSurname(userRegisterModel.getSurname());

		try {
			this.userDAO.save(user);
		} catch (DataIntegrityViolationException e) {
			problemList.add(new Problem("O email informado já está em uso."));
			userAuthenticationReturn.setProblemList(problemList);
		}

		if (!problemList.hasAny()) {
			HttpSession session = httpRequest.getSession(true);
			session.setAttribute("userName", user.getName());
			session.setAttribute("userEmail", user.getEmail());
			session.setMaxInactiveInterval(24 * 60 * 60);

			String jwt = JwtUtil.generateToken(user.getEmail(), user.getName());

			userAuthenticationReturn.setJwtToken(jwt);
			userAuthenticationReturn.setProblemList(problemList);
		}

		return userAuthenticationReturn;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
