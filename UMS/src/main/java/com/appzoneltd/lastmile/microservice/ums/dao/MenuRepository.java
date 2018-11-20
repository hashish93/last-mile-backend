package com.appzoneltd.lastmile.microservice.ums.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.MenuEntity;

/**
 * Repository : MenuItem.
 */
public interface MenuRepository extends PagingAndSortingRepository<MenuEntity, Long> {

	@Query("SELECT m FROM MenuEntity m WHERE m.base = true AND m.active = true ORDER BY m.id ASC ")
	public List<MenuEntity> getAllBaseMenus();

}
