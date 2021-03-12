package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.StoryPitch;
import com.revature.beans.User;
import com.revature.services.InfoService;
import com.revature.services.InfoServiceImpl;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;
import com.revature.services.RejectService;
import com.revature.services.RejectServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;


public class EditorDelegate implements FrontControllerDelegate {
	
	private PitchService pServ = new PitchServiceImpl();
	private UserService uServ = new UserServiceImpl();
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String path = (String) request.getAttribute("path");
		
		//Testing 
		if (path == null || path.equals("")) {
			if ("GET".equals(request.getMethod())) {

				response.getWriter().write(om.writeValueAsString(uServ.getAll()));
				System.out.println("Hit GET in editor delegate");
				 
			} else if ("POST".equals(request.getMethod())) {
				 PrintWriter out = response.getWriter();
				 out.println("Wanted a post- T. Editor");
				 
				 String firstname = request.getParameter("firstname");
				 String lastname = request.getParameter("lastname");
				 
			        System.out.println("firstname: " + firstname);
			        System.out.println("lastname: " + lastname);
			        
			        PrintWriter out2 = response.getWriter();
			    	String htmlResponse = "<html>";
			    	htmlResponse += "<h2>Name is: " + firstname + " " + lastname + "</h2>";
			    	htmlResponse += "</html>";
			    	 
			    	out2.println(htmlResponse);
			}
	} else if (path.contains("draft")) {
		if ("GET".equals(request.getMethod())) {
			
			response.getWriter().write(om.writeValueAsString(pServ.getAvailablePitches(5)));	
		}
	}  else {
		int pitchID = Integer.valueOf(path);
		StoryPitch p  = null;
		switch (request.getMethod()) {
		case "GET":
			p = pServ.getPitchById(pitchID);
			if (p != null)
				response.getWriter().write(om.writeValueAsString(p));
			else
				response.sendError(404, "Pitch not Found.");
			break;
		case "PUT":
			p = om.readValue(request.getInputStream(), StoryPitch.class);
			
			Integer approveLevel = 7;
			System.out.println("Status set at " + approveLevel);
			p.setStatus(approveLevel);
			
			pServ.updatePitch(p);
			response.getWriter().write(om.writeValueAsString(p));
			break;
		case "DELETE":
			p = om.readValue(request.getInputStream(), StoryPitch.class);
			pServ.deletePitch(p);
			break;
		}
	}
}
}
