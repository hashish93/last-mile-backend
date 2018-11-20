package com.appzoneltd.lastmile.microservice.service;

import com.appzoneltd.lastmile.microservice.DTO.FaqDto;
import com.appzoneltd.lastmile.microservice.Entity.FaqEntity;
import com.appzoneltd.lastmile.microservice.Entity.FaqLanguageEntity;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import org.springframework.data.domain.PageRequest;


import java.util.List;

/**
 * Created by hashish on 4/25/2017.
 */
public interface FaqService {
//    List<Message> addFaq(FaqDto faqDto);
    boolean deleteFaq(Long id);
    List<FaqEntity> getAllFaq(PageRequest pageRequest);
    
    List<Message> saveFAQ(FaqDto faqDto)throws EntityNotFoundException;
    public List<FaqEntity> getAllFaqsByLang(String lang, PageRequest pageRequest);
    
    public List<FaqLanguageEntity> getAllAvailableLangs();
	Long getAllFAQsCount();
	FaqEntity getFAQById(Long idee);
	
	public List<FaqEntity> searchFAQsByKeyWordAndLanguage(String language , String keyWord);
}
