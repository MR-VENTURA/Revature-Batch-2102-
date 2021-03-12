package com.revature.services;

import com.revature.beans.InfoForm;
import com.revature.data.DAOFactory;
import com.revature.data.InfoDAO;

public class InfoServiceImpl implements InfoService{
	
	private InfoDAO infoDao;
	
	public InfoServiceImpl() {
		infoDao = DAOFactory.getInfoDAO();
	}

	@Override
	public Integer addInfoForm(InfoForm i) {
		return infoDao.add(i).getId();
	}

	@Override
	public InfoForm getInfoById(Integer id) {
		return infoDao.getById(id);
	}

	@Override
	public InfoForm getInfoByPitch(Integer id) {
		return infoDao.getByPitch(id);
	}

	@Override
	public void updateInfoForm(InfoForm i) {
		if (getInfoById(i.getId()) != null)
			infoDao.update(i);
		
	}

	@Override
	public void deleteInfoForm(InfoForm i) {
		if (getInfoById(i.getId()) != null)
			infoDao.delete(i);
	}

}
