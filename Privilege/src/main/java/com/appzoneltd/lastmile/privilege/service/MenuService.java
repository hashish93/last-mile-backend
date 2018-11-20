package com.appzoneltd.lastmile.privilege.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.privilege.dao.MenuDao;
import com.appzoneltd.lastmile.privilege.model.Menu;

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	public List<Menu> getAllBaseMenus(){
		return menuDao.getAllBaseMenus();
	}
	
}
