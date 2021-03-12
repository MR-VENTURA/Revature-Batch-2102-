package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	//private Logger log = Logger.getLogger(PersonPostgres.class);

	
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	public User add(User p) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByUsername(String username) {
		//TODO method for Console
		//log.info("Hit getByUsername in UserPostgres");
		User p = null;
		try (Connection conn = cu.getConnection())
		{  
			String sql = "select * from users where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()); 
			{
				p = new User();
				p.setId(rs.getInt("user_id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("psswrd"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEmail(rs.getString("email"));
			}
		
		}catch (Exception e) {e.printStackTrace();}
		return p;
	}

	@Override
	public Set<User> getEditors(Integer rank) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
