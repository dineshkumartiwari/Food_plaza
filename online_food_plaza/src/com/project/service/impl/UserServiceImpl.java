package com.project.service.impl;

import java.util.List;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDauImpUsingHibernet;
import com.project.dao.impl.UserdauImplUsingJdbc;
import com.project.dto.UserDTO;
import com.project.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserdauImplUsingJdbc();
	// private UserDao userDao = new UserDaoImplUsingHibernate();

	@Override
	public boolean saveUser(UserDTO user) {
		return userDao.saveUser(user);
	}

	@Override
	public boolean updateUser(UserDTO user) {

		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(String email) {
		return userDao.deleteUser(email);
	}

	@Override
	public List<UserDTO> searchUser(String email) {

		return userDao.searchUser(email);
	}

	@Override
	public List<UserDTO> showAllUser() {
		return userDao.showAllUser();
	}

}
