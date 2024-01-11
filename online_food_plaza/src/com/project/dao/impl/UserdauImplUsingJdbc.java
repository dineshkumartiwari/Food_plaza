package com.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.UserDao;
import com.project.dto.UserDTO;
import com.project.utility.DbConnection;

public class UserdauImplUsingJdbc implements UserDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private Statement st;

	@Override
	public boolean saveUser(UserDTO user) {

		try {
			con = DbConnection.getConnection();
			ps = con.prepareStatement("insert into tbl_users(name, email, password) values(?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateUser(UserDTO user) {
		try {
			con = DbConnection.getConnection();
			StringBuilder query = new StringBuilder("UPDATE tbl_USERS SET ");
			if (user.getName() != null && !user.getName().equals("")) {
				query.append("NAME= ?, ");
			}
			if (user.getEmail() != null && !user.getEmail().equals("")) {
				query.append("EMAIL= ?, ");
			}
			if (user.getPassword() != null && !user.getPassword().equals("")) {
				query.append("PASSWORD= ?, ");
			}
			query.deleteCharAt(query.length() - 2);
			query.append(" WHERE ID= ?");

			ps = con.prepareStatement(query.toString());
			int index = 1;

			if (user.getName() != null && !user.getName().equals("")) {
				ps.setString(index, user.getName());
				index++;
			}
			if (user.getEmail() != null && !user.getEmail().equals("")) {
				ps.setString(index, user.getEmail());
				index++;
			}
			if (user.getPassword() != null && !user.getPassword().equals("")) {
				ps.setString(index, user.getPassword());
				index++;
			}
			if (user.getId() != null) {
				ps.setInt(index, user.getId());
				index++;
			}
			int rows = ps.executeUpdate();

			if (rows > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteUser(String email) {
		try {
			con = DbConnection.getConnection();
			ps = con.prepareStatement("delete from tbl_users where email = ?");
			ps.setString(1, email);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<UserDTO> searchUser(String email) {
		List<UserDTO> users = new ArrayList<>();
		try {
			con = DbConnection.getConnection();
			ps = con.prepareStatement("SELECT * FROM tbl_USERS WHERE EMAIL= ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String email1 = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				UserDTO user = new UserDTO(id, name, email1, password);
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;

	}

	@Override
	public List<UserDTO> showAllUser() {
		List<UserDTO> users = new ArrayList<>();

		try {
			con = DbConnection.getConnection();
			ps = con.prepareStatement("select * from tbl_users");
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				UserDTO user = new UserDTO(id, name, email, pass);
				users.add(user);
			}
		} catch (Exception e) {

		} finally {
			try {
				if (con != null) {
					rs.close();
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;

	}

}
