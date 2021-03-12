package com.revature.services;

import com.revature.beans.InfoForm;

public interface InfoService {
	
	public Integer addInfoForm(InfoForm i);
	public InfoForm getInfoById(Integer id);
	public InfoForm getInfoByPitch(Integer id);
	public void updateInfoForm(InfoForm i);
	public void deleteInfoForm(InfoForm i);
}
