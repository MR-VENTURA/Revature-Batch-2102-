package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.InfoForm;
import com.revature.beans.RejectForm;
import com.revature.services.InfoServiceImpl;
import com.revature.services.RejectService;
import com.revature.services.RejectServiceImpl;

public class RejectHibernateTest {
	private static RejectService rejServ;
	private static RejectDAO rejDao;
	private static RejectForm reject;
	
	@BeforeAll
	public static void setup() {
		rejDao = new RejectHibernate();
		
	}
	
	@Test
	public void checkAddReject() {
		RejectForm r = new RejectForm();
		Integer testid = rejServ.addRejectForm(r);
		assertEquals(testid, r.getId());
	}
	
	@Test
	public void checkRejectById() {
		RejectForm r = rejServ.getRejectById(3);
		assertEquals(3, r.getId());
	}
	
	@Test
	public void checkRejectByPitch() {
		
	}
	
	@Test
	public void checkUpdateReject() {
		RejectServiceImpl r = new RejectServiceImpl();
		Integer testid = reject.getId();
		r.updateRejectForm(reject);
		assertEquals(testid, reject.getId());
	}
	
	@Test
	public void checkDeleteReject() {
		RejectForm dTest = new RejectForm();
		dTest.setId(2);
		rejServ.deleteRejectForm(dTest);
		assertEquals(2, dTest.getId());
	}

}
