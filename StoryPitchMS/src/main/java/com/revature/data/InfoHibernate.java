package com.revature.data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.InfoForm;
import com.revature.utils.HibernateUtil;

public class InfoHibernate implements InfoDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public InfoForm getById(Integer id) {
		Session s = hu.getSession();
		InfoForm i = s.get(InfoForm.class, id);
		s.close();
		return i;
	}

	@Override
	public Set<InfoForm> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(InfoForm t) {
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
	public void delete(InfoForm t) {
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
	public InfoForm add(InfoForm i) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(i);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		} return i;
	}

	@Override
	public InfoForm getByPitch(Integer pitchID) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<InfoForm> criteria = cb.createQuery(InfoForm.class);
		Root<InfoForm> root = criteria.from(InfoForm.class);
		
		Predicate predicateForPitch = cb.equal(root.get("pitchID"), pitchID);
		
		criteria.select(root).where(predicateForPitch);
		
		InfoForm i = s.createQuery(criteria).getSingleResult();
		return i;
	}

	@Override
	public InfoForm getByAuthor(Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}
}
