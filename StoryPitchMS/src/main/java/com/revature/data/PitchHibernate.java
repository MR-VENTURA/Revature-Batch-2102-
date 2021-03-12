package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.StoryPitch;
import com.revature.utils.HibernateUtil;

public class PitchHibernate implements PitchDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public StoryPitch getById(Integer id) {
		Session s = hu.getSession();
		StoryPitch p = s.get(StoryPitch.class, id);
		s.close();
		return p;
	}

	@Override
	public Set<StoryPitch> getAll() {
		Session s = hu.getSession();
		String query = "FROM StoryPitch";
		Query<StoryPitch> q = s.createQuery(query, StoryPitch.class);
		List<StoryPitch> pitchList = q.getResultList();
		Set<StoryPitch> pitchs = new HashSet<>();
		pitchs.addAll(pitchList);
		s.close();
		return pitchs;
	}

	@Override
	public void update(StoryPitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(StoryPitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public StoryPitch add(StoryPitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return p;
	}

	@Override
	public Set<StoryPitch> getAvailablePitches(Integer status) {
		Session s = hu.getSession();
		String query = "FROM StoryPitch P where status = :status";
		Query<StoryPitch> q = s.createQuery(query, StoryPitch.class);
		q.setParameter("status", status);
		List<StoryPitch> pitchList = q.getResultList();
		Set<StoryPitch> pitches = new HashSet<>();
		pitches.addAll(pitchList);
		s.close();
		return pitches;
	}

	@Override
	public Set<StoryPitch> getByStatus(Integer status, String genre) {
		Session s = hu.getSession();
		String query = "FROM StoryPitch where status = :status and genre = :genre";
		Query<StoryPitch> q = s.createQuery(query, StoryPitch.class);
		q.setParameter("status", status);
		q.setParameter("genre", genre);
		List<StoryPitch> pitchList = q.getResultList();
		Set<StoryPitch> pitches = new HashSet<>();
		pitches.addAll(pitchList);
		s.close();
		return pitches;
	}

	@Override  //TODO check that this works
	public Set<StoryPitch> getByUserStatus(Integer status, Integer userID) {
		Session s = hu.getSession();
		String query = "FROM StoryPitch where status = :status and userID = :userID";
		Query<StoryPitch> q = s.createQuery(query, StoryPitch.class);
		q.setParameter("status", status);
		q.setParameter("userID", userID);
		List<StoryPitch> pitchList = q.getResultList();
		Set<StoryPitch> pitches = new HashSet<>();
		pitches.addAll(pitchList);
		s.close();
		return pitches;
	}

//	@Override
//	public Set<StoryPitch> getByStatus(Integer Status) {
//		Session s = hu.getSession();
//		String query = "FROM StoryPitch where status = :status";
//		Query<StoryPitch> q = s.createQuery(query, StoryPitch.class);
//		q.setParameter("status", Status);
//		List<StoryPitch> pitchList = q.getResultList();
//		Set<StoryPitch> pitches = new HashSet<>();
//		pitches.addAll(pitchList);
//		s.close();
//		return pitches;
//	}
}
