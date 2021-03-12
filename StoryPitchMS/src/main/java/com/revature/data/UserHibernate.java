package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.StoryPitch;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

public class UserHibernate implements UserDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getAll() {
		Session s = hu.getSession();
		String query = "FROM User where rank != 1";
		Query<User> q = s.createQuery(query, User.class);
		List<User> editorList = q.getResultList();
		Set<User> editors = new HashSet<>();
		editors.addAll(editorList);
		s.close();
		return editors;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User add(User u) throws NonUniqueUsernameException {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(u);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		} return u;
		
	}

	@Override
	public User getByUsername(String username) {
//		Session s = hu.getSession();
//		User u= s.get(User.class, username);
//		s.close();
//		return u;
		
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		
		Predicate predicateForUsername = cb.equal(root.get("username"), username);
		
		criteria.select(root).where(predicateForUsername);
		
		User u = s.createQuery(criteria).getSingleResult();
		return u;
	}

	@Override
	public Set<User> getEditors(Integer rank) {
		Session s = hu.getSession();
		String query = "FROM User where rank = :rank";
		Query<User> q = s.createQuery(query, User.class);
		q.setParameter("rank", rank);
		List<User> editorList = q.getResultList();
		Set<User> editors = new HashSet<>();
		editors.addAll(editorList);
		s.close();
		return editors;
	}

}
