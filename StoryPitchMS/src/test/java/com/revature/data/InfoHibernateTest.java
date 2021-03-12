package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.InfoForm;
import com.revature.services.InfoService;
import com.revature.services.InfoServiceImpl;

public class InfoHibernateTest {
	private static InfoService infoServ;
	private static InfoDAO infoDao;
	private static InfoForm info;
	
	@BeforeAll
	public static void setup() {
		infoDao = new InfoHibernate();
		
	}
	
	@Test
	public void checkAddInfo() {
		InfoForm i = new InfoForm();
		Integer testid = infoServ.addInfoForm(i);
		assertEquals(testid, i.getId());
	}
	
	@Test
	public void checkInfoById() {
		InfoForm i = infoServ.getInfoById(3);
		assertEquals(3, i.getId());
	}
	
	@Test
	public void checkInfoByPitch() {
		
	}
	
	@Test
	public void checkUpdateInfo() {
		InfoServiceImpl i = new InfoServiceImpl();
		Integer testid = info.getId();
		i.updateInfoForm(info);
		assertEquals(testid, info.getId());
	}
	
	@Test
	public void checkDeleteInfo() {
		InfoForm dTest = new InfoForm();
		dTest.setId(2);
		infoServ.deleteInfoForm(dTest);
		assertEquals(2, dTest.getId());
	}
	
	

}
