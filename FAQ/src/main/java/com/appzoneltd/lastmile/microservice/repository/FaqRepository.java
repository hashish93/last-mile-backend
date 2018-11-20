package com.appzoneltd.lastmile.microservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.Entity.FaqEntity;

/**
 * Created by hashish on 4/25/2017.
 */
@Repository
public interface FaqRepository 	extends PagingAndSortingRepository<FaqEntity, Long> {

	public Page<FaqEntity> findAllByLanguage(String lan, Pageable page);

	@Query("SELECT f FROM FaqEntity f WHERE f.language =:lang and ( f.question LIKE CONCAT('%',:strToFind,'%') or f.answer LIKE CONCAT('%',:strToFind,'%') )")
	public List<FaqEntity> searchForString(
			@Param("strToFind") String strToFind ,
			@Param("lang") String lang);
}
