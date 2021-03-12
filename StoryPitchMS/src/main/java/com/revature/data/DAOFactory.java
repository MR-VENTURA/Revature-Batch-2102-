package com.revature.data;

public class DAOFactory { //TODO return hibernate rather than postgres

	 public static UserDAO getUserDAO() {
		 	return new UserHibernate();
	    	//return new UserPostgres();
	    }
	 
	 public static PitchDAO getPitchDAO() {
		 return new PitchHibernate();
	 }
	 
	 public static InfoDAO getInfoDAO() {
		 return new InfoHibernate();
	 }
	 
	 public static RejectDAO getRejectDAO() {
		 return new RejectHibernate();
	 }
}
