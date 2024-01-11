package com.project.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.project.dto.UserDTO;
import com.project.service.UserService;
import com.project.service.impl.UserServiceImpl;

public class UserController {

	private int optionUser = 0;
	Scanner sc = new Scanner(System.in);
	UserService userService = new UserServiceImpl();

	// start of instance block
	{
		do {
			System.out.println("1. Select 1 Add User\n2. Select 2 Delete User\n3. Select 3 Update User"
					+ "\n4. Select 4 to Show All User\n5. Select 5 to Show Any User\n6. Select 6 for Exit");
			optionUser = sc.nextInt();
			switch (optionUser) {
			case 1:
				addUser();
				break;
			case 2:
				deleteAnyUser();
				break;
			case 3:
				updateUser();
				break;
			case 4:
				showAllUserData();
				break;
			case 5:
				searchUser();
				break;
			case 6:
				System.out.println("Exited from User Section Successfully...");
				break;
			}
		} while (optionUser != 6);
	}// end of instance block

	private void addUser() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			System.out.println("Enter name");
			String name = br.readLine();
			System.out.println("Enter email");
			String email = br.readLine();
			System.out.println("Enter password");
			String pass = br.readLine();
			UserDTO user = new UserDTO(name, email, pass);
			boolean isUserSaved = userService.saveUser(user);
			if (isUserSaved) {
				System.out.println("Data has been saved successfully...");
				showAllUserData();
			} else {
				System.out.println("Something went wrong to save data..");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void searchUser() {
		try {
			System.out.println("Enter email: ");
			String email = sc.next();
			// UserDTO user = new UserDTO(email);

			List<UserDTO> users = userService.searchUser(email);

			for (UserDTO row : users) {
				System.out.println(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateUser() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			System.out.println("enter Id Whose details Wants to update: ");
			int id = sc.nextInt();
			System.out.println("Enter the new name to update: ");
			String name = br.readLine();
			System.out.println("Enter new email to update: ");
			String email = br.readLine();
			System.out.println("Enter new password to update: ");
			String password = br.readLine();
			UserDTO user = new UserDTO(id, name, email, password);
			boolean isUserUpdated = userService.updateUser(user);
			if (isUserUpdated) {
				System.out.println("Data has been updated successfully...");

			} else {
				System.out.println("Something went wrong to update data..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deleteAnyUser() {
		System.out.println("Enter your email id to delete record");
		String email = sc.next();
		boolean isUserDeleted = userService.deleteUser(email);
		if (isUserDeleted) {
			System.out.println("Record has been deleted successfully...");
			showAllUserData();
		} else {
			System.out.println("Something went wrong to delete record..");
		}

	}

	private void showAllUserData() {

		List<UserDTO> users = userService.showAllUser();
		System.out.println("------------------User Records are------------------");
		for (UserDTO user : users) {
			System.out.println(user);
		}

	}

}
