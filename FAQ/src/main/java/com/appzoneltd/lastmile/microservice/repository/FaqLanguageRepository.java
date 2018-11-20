package com.appzoneltd.lastmile.microservice.repository;

import com.appzoneltd.lastmile.microservice.Entity.FaqLanguageEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hashish on 4/25/2017.
 */
@Repository
public interface FaqLanguageRepository 	extends JpaRepository<FaqLanguageEntity, Long> {

//	@Query("select * from FaqLanguageEntity where languageCode= :langCode")
	List<FaqLanguageEntity> findByLanguageCode(String langCode);
}
