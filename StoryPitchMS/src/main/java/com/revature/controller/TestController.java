package com.revature.controller;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

//Simply to test that database is being accessed and things are working.

public class TestController {
	
	private static Scanner scan = new Scanner(System.in);
	private static UserService userServ = new UserServiceImpl();


	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		boolean userActive = true;		
		LoginMenuloop: while (userActive){
			System.out.println("Testing for Story Pitch");
			User loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("=========================");
				System.out.println("1. Register an account\n" 
								+ "2. Log in\n"
								+ "Exit. Exit");
				String userInput = scan.nextLine();
			
				switch (userInput) {
				case "1":
					loggedInUser = registerUser();
					break;
				case "2":
					loggedInUser = logInUser();
					break;
				case "Exit":
					//log.info("User is exiting the application.");
					System.out.println("Goodbye!");
					userActive = false;
					break LoginMenuloop;
				}
		}

			mainMenuLoop: while (true) {
				User user = userServ.getUserByUsername(loggedInUser.getUsername());
				
				System.out.println("Logged IN!!");	
			}
	}
	}


	private static User registerUser() {
		// TODO Auto-generated method stub
		return null;
	}


	private static User logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			User user = userServ.getUserByUsername(username);
			if (user == null) {
				//log.debug("User entered a username that doesn't exist.");
				System.out.println("Sorry, that username doesn't exist.");
			} else if (user.getPassword().equals(password)) {
				//log.debug("User logged in successfully.");
				//log.debug(user + "is logged in");
				System.out.println("Welcome back!");
				return user;
			} else {
				//log.debug("User entered an incorrect password.");
				System.out.println("Sorry, that password is incorrect.");
			}
			System.out.println("Do you want to try again? y for yes, n for no.");
			String input = scan.nextLine();
			if (!("y".equals(input)))
				return null;
		}
	}
	}


