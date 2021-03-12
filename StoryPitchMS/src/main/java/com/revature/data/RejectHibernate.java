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

import com.revature.beans.InfoForm;
import com.revature.beans.RejectForm;
import com.revature.beans.StoryPitch;
import com.revature.utils.HibernateUtil;

public class RejectHibernate implements RejectDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public RejectForm getById(Integer id) {
		Session s = hu.getSession();
		RejectForm i = s.get(RejectForm.class, id);
		s.close();
		return i;
	}

	@Override
	public Set<RejectForm> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RejectForm t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RejectForm t) {
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
	public RejectForm add(RejectForm r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		} return r;
	}

	@Override
	public RejectForm getByPitch(Integer pitchID) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<RejectForm> criteria = cb.createQuery(RejectForm.class);
		Root<RejectForm> root = criteria.from(RejectForm.class);
		
		Predicate predicateForPitch = cb.equal(root.get("pitchID"), pitchID);
		
		criteria.select(root).where(predicateForPitch);
		
		RejectForm r = s.createQuery(criteria).getSingleResult();
		return r;
	}

	@Override
	public RejectForm getByAuthor(Integer userID) {
		
		return null;
	}
	
	
}
