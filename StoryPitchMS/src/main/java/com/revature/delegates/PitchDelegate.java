package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Genre;
import com.revature.beans.InfoForm;
import com.revature.beans.RejectForm;
import com.revature.beans.StoryPitch;
import com.revature.beans.User;
import com.revature.services.InfoService;
import com.revature.services.InfoServiceImpl;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;
import com.revature.services.RejectService;
import com.revature.services.RejectServiceImpl;

/*
 * Endpoints:
 *  /pitch - (GET) retrieves all available pitches
 *       - (POST) adds a pitch
 *  /pitch/:id - (GET) gets a pitch by id
 *           - (PUT) updates a pitch
 *           - (DELETE) deletes a pitch
 *  /pitch/:id/reject
 */

public class PitchDelegate implements FrontControllerDelegate {
	
	private PitchService pServ = new PitchServiceImpl();
	private RejectService rServ = new RejectServiceImpl();
	private InfoService iServ = new InfoServiceImpl();
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = (String) request.getAttribute("path");
		
		if (path == null || path.equals("")) { 
			switch (request.getMethod()) {
				case "POST":
					User us = (User) request.getSession().getAttribute("user");
					System.out.println("user Id is " + us.getId());
					
					String title = request.getParameter("title");
					String completionDate = request.getParameter("completionDate");
					String type = request.getParameter("storyType");
					String genre = request.getParameter("genre");
					String tagline = request.getParameter("tagline");
					String description = request.getParameter("description");
					//Attached Docs
					
					//Genre g = new Genre();
					//User u = new User(); //Not sure if neccesary
					StoryPitch p = new StoryPitch();
					Integer uID = us.getId();
					
					p.setId(pServ.addPitch(p));
					p.setUserID(uID);
					p.setTitle(title);
					p.setCompletionDate(completionDate);
					p.setGenre(genre);
					p.setType(type);
					p.setTagline(tagline);
					p.setDescription(description);
					p.setUserID(us.getId());
					
					//Calculate author points
					System.out.println("Author points = " + us.getPoints());
					Integer points = 0;
					if (type == "novel") {
						points = 50;
					} else if (type == "novella") {
						points = 25;
					} else if (type == "article") {
						points = 10;
					} else {
						points = 20;
					}
					Integer authorPoints = us.getPoints();
					us.setPoints(authorPoints + points);
					authorPoints = us.getPoints();
					System.out.println(us.getPoints() + " new points " + authorPoints);
					//Check if enough points, if not set status to pending (0)
					if (authorPoints > 100) {
						p.setStatus(0);
					}
					
					pServ.addPitch(p);
					
//					StoryPitch p = (StoryPitch) om.readValue(request.getInputStream(), StoryPitch.class);
//					p.setId(pServ.addPitch(p));

					response.getWriter().write(om.writeValueAsString(p));
					response.getWriter().write("You have " + authorPoints);
					response.setStatus(HttpServletResponse.SC_CREATED);
					response.sendRedirect("http://localhost:8081/StoryPitchMS/Static/author.html");
					
					//TODO go to another page.
					break;
				case "GET":
					User u2 = (User) request.getSession().getAttribute("user");
					System.out.println(u2.getId() + " has name: " + u2.getUsername());
					Integer uRank = u2.getRank();
					Integer uGenre = u2.getGenre();
					String pGenres = null;
					String generalGenre = "fiction";
					
					if (uGenre == 1) {
						pGenres = "fiction";
						generalGenre = "history";
					} else if (uGenre == 2) {
						pGenres = "biography";
					} else if (uGenre == 3) {
						pGenres = "current events";
					} else if (uGenre == 4) {
						pGenres = "history";
					} else if (uGenre == 5) {
						pGenres = "science";
					}
					System.out.println(pGenres + " the current genre");
					
					
					if (uRank == 2) {
						response.getWriter().write(om.writeValueAsString(pServ.getByStatus(1, pGenres)));
					} else if (uRank == 3) {
						response.getWriter().write(om.writeValueAsString(pServ.getByStatus(2, generalGenre)));
					} else if (uRank == 4) {
						response.getWriter().write(om.writeValueAsString(pServ.getByStatus(3, pGenres)));
					}
					break;
				default:
					response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
			} else if (path.contains("reject")) {
				if ("POST".equals(request.getMethod())) {
					RejectForm r = (RejectForm) om.readValue(request.getInputStream(), RejectForm.class);
					r.setId(rServ.addRejectForm(r));
					
					StoryPitch p  = null;
					p = pServ.getPitchById(r.getPitchID());
					System.out.println(p + " pitch");
					p.setStatus(6); //Set to Rejected (6)
					
					pServ.updatePitch(p);

				} else {
					response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
			} else if (path.contains("info")) {
				if ("POST".equals(request.getMethod())) {
					InfoForm i = (InfoForm) om.readValue(request.getInputStream(), InfoForm.class);
					//String request2 = request.getParameter("request");
					
					System.out.println("pitch/info reached");
					//i.setRequest(request2);
					i.setId(iServ.addInfoForm(i));


				} else {
					response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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
					
					p.setAssistantApprove(true);
					User u = (User) request.getSession().getAttribute("user");
					Integer approveLevel = u.getRank();// + 1;
					System.out.println("Status set at " + approveLevel);
					p.setStatus(approveLevel);
					
					pServ.updatePitch(p);
					response.getWriter().write(om.writeValueAsString(p));
					break;
				case "DELETE":
					p = om.readValue(request.getInputStream(), StoryPitch.class);
					pServ.deletePitch(p);
					break;
				default:
					response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
				} 
			}
			}
}


