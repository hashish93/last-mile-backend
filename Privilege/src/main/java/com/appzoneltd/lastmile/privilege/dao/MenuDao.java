package com.appzoneltd.lastmile.privilege.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.privilege.model.Menu;

@Transactional
public interface MenuDao extends CrudRepository<Menu, Long> {

	@Query("SELECT m FROM Menu m WHERE m.base = true AND m.active = true ORDER BY m.id ASC ")
	public List<Menu> getAllBaseMenus();
	
}
