package com.appzoneltd.lastmile.microservice.hubconfig.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.SizesConfigEntity;

public interface SizesConfigDao extends CrudRepository<SizesConfigEntity, Long>  {
	
	
	@Query("SELECT s FROM SizesConfigEntity s WHERE s.sizeName = :sizeNameExist AND s.status='ACTIVE' ")
	public List<SizesConfigEntity> CheckSizesNameExist(@Param("sizeNameExist") String sizeName);
	
	@Query("SELECT s FROM SizesConfigEntity s WHERE s.status='ACTIVE'  ORDER BY s.created DESC ")
    public List<SizesConfigEntity> findAllOrderByDate ();
    

	
}
