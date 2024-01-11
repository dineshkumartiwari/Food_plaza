package com.project.dao;

import java.util.List;

import com.project.dto.UserDTO;
import com.project.dto.ProductDTO;

public interface UserDao {
	boolean saveUser(UserDTO user);

	boolean updateUser(UserDTO user);

	boolean deleteUser(String email);

	List<UserDTO> searchUser(String email);

	List<UserDTO> showAllUser();
}
