package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.StoryPitch;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

public class PitchHibernateTest {
	private static PitchService pitchServ;
	private static PitchDAO pitchDao;
	private static StoryPitch pitch;
	
	@BeforeAll
	public static void setup() {
		pitchDao = new PitchHibernate();
	}
	
	@Test
	public void checkAddPitch() {
		StoryPitch addP = new StoryPitch();
		Integer testid = pitchServ.addPitch(addP);
		assertEquals(testid, addP.getId());
	}
	
	@Test
	public void checkGetPitchById() {
		StoryPitch p = pitchDao.getById(1);
		System.out.println(p);
		assertEquals(2, p.getId());
	}
	
	@Test
	public void getAvailablePitches() {
		Set<StoryPitch> testPitch = pitchServ.getAvailablePitches(1);
		for (StoryPitch pitch : testPitch) {
			assertEquals(false, pitch.getStatus());
		}
	}
	
	@Test
	public void checkUpdatePitch() {
		PitchServiceImpl pitchT = new PitchServiceImpl();
		Integer ID = pitch.getId();
		pitchT.updatePitch(pitch);
		assertEquals(ID, pitch.getId());
	}
	
	@Test
	public void checkDeletePitch() {
		StoryPitch deleteTest = new StoryPitch();
		deleteTest.setId(2);
		pitchServ.deletePitch(deleteTest);
		assertEquals(2, deleteTest.getId());
	}

}
