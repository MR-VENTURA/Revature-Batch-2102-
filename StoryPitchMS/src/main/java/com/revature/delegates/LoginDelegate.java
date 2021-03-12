package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class LoginDelegate implements FrontControllerDelegate{

	private UserService uServ = new UserServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = (String) request.getAttribute("path");
		
		if (path == null || path.equals("")) {
			if ("POST".equals(request.getMethod())) {
				System.out.println(request.getInputStream());
				Map register = om.readValue(request.getInputStream(), Map.class);
				System.out.println("test");
				User u = (User) om.readValue(request.getInputStream(), User.class); 
				System.out.println("u");
				
//				String firstname = request.getParameter("firstname");
//				String lastname = request.getParameter("lastname");
//		        String username = request.getParameter("username");
//		        String password = request.getParameter("password");
//		        String email = request.getParameter("email");
//				User u = new User();
				try {
					u.setId(uServ.addUser(u));
//					u.setFirstname(firstname);
//					u.setLastname(lastname);
//				    u.setUsername(username);
//				    u.setPassword(password);
//				    u.setEmail(email);
//				    uServ.addUser(u);
				} catch (NonUniqueUsernameException e) {
					response.sendError(HttpServletResponse.SC_CONFLICT, "Username already exists");
				}
				if (u.getId() == 0) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} else {
					response.getWriter().write(om.writeValueAsString(u));
					response.setStatus(HttpServletResponse.SC_CREATED);
				}
			} else if ("GET".equals(request.getMethod())) {
				User u = (User) request.getSession().getAttribute("user");
				response.getWriter().write(om.writeValueAsString(u));
				response.setStatus(200);
			} else {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}else if (path.contains("login")) {
			if ("POST".equals(request.getMethod()))
			logIn(request, response);
		else if ("DELETE".equals(request.getMethod()))
			request.getSession().invalidate();
		else
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	} else {
		userWithId(request, response, Integer.valueOf(path));
	}
	}
	
	private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User u = uServ.getUserByUsername(username);
		if (u != null) {
			if (u.getPassword().equals(password)) {
				request.getSession().setAttribute("user", u);
				response.getWriter().write(om.writeValueAsString(u));
				response.getWriter().write("Logged IN");
				System.out.println("Logged IN");
				
				Integer uRank = u.getRank();
				System.out.println("User rank is " + uRank);
				if (uRank == 1) {
				response.sendRedirect("http://localhost:8081/StoryPitchMS/Static/author.html");
				} else {
					response.sendRedirect("http://localhost:8081/StoryPitchMS/Static/editor.html");
				}
//				RequestDispatcher dispatcher = request.getRequestDispatcher("editor.html");
//		        dispatcher.forward(request, response);
			} else {
				response.sendError(404, "Incorrect password.");
				System.out.println("It is incorrect password.");
			}
		} else {
			response.sendError(404, "No user found with that username.");
		}
	}
	
	private void userWithId(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
		switch (request.getMethod()) {
			case "GET":
			User u = uServ.getUserById(id);
			if (u != null) {
				response.getWriter().write(om.writeValueAsString(u));
			} else {
				response.sendError(404, "User not found.");
			}
			break;
			case "PUT":
				String password = request.getParameter("password");
				User users = (User) request.getSession().getAttribute("username");
				if (users != null) {
					users.setPassword(password);
					uServ.updateUser(users);
				} else
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				break;
			case "DELETE":
				User user = om.readValue(request.getInputStream(),User.class);
				uServ.deleteUser(user);
				break;
			default:
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
		}
}	
}
