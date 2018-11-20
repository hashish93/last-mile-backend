package com.appzoneltd.lastmile.microservices.SizesConfiguration.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.SizesConfiguration.model.SizesConfig;

public interface SizesConfigDao extends CrudRepository<SizesConfig, Long>  {
	
	
	@Query("SELECT s FROM SizesConfig s WHERE s.sizeName = :sizeNameExist AND s.status='ACTIVE' ")
	public List<SizesConfig> CheckSizesNameExist(@Param("sizeNameExist") String sizeName);
	
	@Query("SELECT s FROM SizesConfig s WHERE s.status='ACTIVE'  ORDER BY s.created DESC ")
    public List<SizesConfig> findAllOrderByDate ();
    

	
}
