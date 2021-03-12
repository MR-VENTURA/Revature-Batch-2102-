package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.InfoForm;
import com.revature.beans.RejectForm;
import com.revature.beans.StoryPitch;
import com.revature.beans.User;
import com.revature.services.AuthorService;
import com.revature.services.AuthorServiceImpl;
import com.revature.services.InfoService;
import com.revature.services.InfoServiceImpl;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;
import com.revature.services.RejectService;
import com.revature.services.RejectServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

/*
 * Endpoints:
 *  /author - (GET) retrieves all changed pitches
 *       - (POST) adds a pitch
 *  /author/draft - Get all Approved Pitches. 
 *  /author/:id - (GET) gets a pitch by id
 *           - (PUT) updates a pitch
 *           - (DELETE) deletes a pitch
 *  /author/info - (GET) gets a pitch by id
 *           - (PUT) updates a pitch
 *  /author/hold - (GET) gets a pitch by id       
 */

public class AuthorDelegate implements FrontControllerDelegate {
	
	private AuthorService aServ = new AuthorServiceImpl();
	
	private PitchService pServ = new PitchServiceImpl();
	private InfoService iServ = new InfoServiceImpl();
	private RejectService rServ = new RejectServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = (String) request.getAttribute("path");
		
		if (path == null || path.equals("")) { 
			switch (request.getMethod()) {
				case "GET":
					User u = (User) request.getSession().getAttribute("user");
					System.out.println(u.getUsername() + " has Id: " + u.getId());
					Integer uId = u.getId();
					System.out.println(uId + " this id to fetch pitches");
					
					response.getWriter().write(om.writeValueAsString(pServ.getByUserStatus(4, uId)));	
					
					break;
				default:
					response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
				}
		} else if (path.contains("draft")) {
			if ("GET".equals(request.getMethod())) {
				User u = (User) request.getSession().getAttribute("user");
				System.out.println(u.getUsername() + " has Id: " + u.getId());
				Integer uId = u.getId();
				System.out.println(uId + " this id to fetch pitches");
				
				response.getWriter().write(om.writeValueAsString(pServ.getByUserStatus(5, uId)));	
			}
		} else if (path.contains("upload")) {
			if ("GET".equals(request.getMethod())) {
				User u = (User) request.getSession().getAttribute("user");
				System.out.println(u.getUsername() + " has Id: " + u.getId());
				Integer uId = u.getId();
				System.out.println(uId + " Trying to upload draft");
				
					
			}
		} else if (path.contains("info")) {
			switch (request.getMethod()) {
			case "GET":
				User u = (User) request.getSession().getAttribute("user");
				Integer uID = u.getId();
				
				Integer id = 24; //placeholder. Change so id varies according to user.
				response.getWriter().write(om.writeValueAsString(iServ.getInfoByPitch(id)));
//				
//				InfoForm i = iServ.getInfoByPitch(id);
//				if (i != null)
//					response.getWriter().write(om.writeValueAsString(i));
				break;
			case "PUT":
				Integer id2 = 2; //placeholder. Change so id varies according to user.
				InfoForm i = iServ.getInfoByPitch(id2);
				
				
				//i = om.readValue(request.getInputStream(), InfoForm.class);	
				
				
				String response2 = request.getParameter("response");
				i.setResponse(response2);
				System.out.println(i.getId() + "is the id for pitch");
				i.setId(3);
				
				iServ.updateInfoForm(i);
				response.getWriter().write(om.writeValueAsString(i));

				break;
			}
		} else if (path.contains("reject")) {
			if ("GET".equals(request.getMethod())) {
				User u = (User) request.getSession().getAttribute("user");
				Integer uID = u.getId();
				Set<StoryPitch> p = pServ.getByUserStatus(6, uID);
				//response.getWriter().write(om.writeValueAsString(p));
				//iterate through set and grab first value
				
				Integer id = 26; //placeholder. Change so id varies according to user.
				response.getWriter().write(om.writeValueAsString(rServ.getRejectByPitch(id)));
			}
		} else if (path.contains("hold")) {
			if ("GET".equals(request.getMethod())) {
				User u = (User) request.getSession().getAttribute("user");
				System.out.println(u.getUsername() + " has Id: " + u.getId());
				Integer uId = u.getId();
				
				response.getWriter().write(om.writeValueAsString(pServ.getByUserStatus(0, uId)));
			}
		} else {
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
				
				User u = (User) request.getSession().getAttribute("user");
				Integer approveLevel = 5;
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
