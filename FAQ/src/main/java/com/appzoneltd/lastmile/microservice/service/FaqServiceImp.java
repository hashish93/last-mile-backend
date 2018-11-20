package com.appzoneltd.lastmile.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.DTO.FaqDto;
import com.appzoneltd.lastmile.microservice.Entity.FaqEntity;
import com.appzoneltd.lastmile.microservice.Entity.FaqLanguageEntity;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.repository.FaqLanguageRepository;
import com.appzoneltd.lastmile.microservice.repository.FaqRepository;

/**
 * Created by ibrahim.ali 
 */
@Service
public class FaqServiceImp implements FaqService {
	@Autowired
	private FaqRepository faqRepository;
	@Autowired
	private FaqLanguageRepository faqLanguageRepository;
	@Autowired
	private MessageSource messageSource;
	/*
    @Override
    public List<Message> addFaq(FaqDto faqDto) {
        List<Message> message = new ArrayList<Message>();
        Long id;
        FaqEntity faqEntity = new FaqEntity();
        if(faqDto.getId() !=null){
            id=faqDto.getId();
        }else {
            id = Utils.generateUUID();
        }

        faqEntity.setId(id);
        faqEntity.setAnswer(faqDto.getAnswer());
        faqEntity.setQuestion(faqDto.getQuestion());
        FaqLanguageEntity faqLanguageEntity = faqLanguageRepository.findOne(faqDto.getLanguageId());

        if(faqLanguageEntity !=null){
            System.out.println(">>>>>>>>>>>>>> faqLanguageEntity "+faqLanguageEntity.toString());
            faqEntity.setFaqLanguageEntity(faqLanguageEntity);
            faqRepository.save(faqEntity);
            return null;
        }
        else{
            message.add(new Message(MessageType.ERROR, "languageId", messageSource.getMessage("faq.lang.notexists",
                    null, "faq.lang.notexists", LocaleContextHolder.getLocale())));
            return message;
        }
    }
	 */
	@Override
	public boolean deleteFaq(Long id) {
		FaqEntity faqEntity = faqRepository.findOne(id);
		if(faqEntity !=null){
			faqRepository.delete(faqEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<FaqEntity> getAllFaq(PageRequest pageRequest) {

		if (pageRequest.getPageNumber() <= 0) {
			return (List<FaqEntity>) faqRepository.findAll();
		}
		else {
			PageRequest pageRequest2 = new PageRequest(pageRequest.getPageNumber()-1, pageRequest.getPageSize(), pageRequest.getSort());
			return faqRepository.findAll(pageRequest2).getContent();
		}

	}

	@Override
	public List<FaqEntity> getAllFaqsByLang(String lang, PageRequest pageRequest) {

		return  faqRepository.findAllByLanguage(lang,pageRequest).getContent();


	}

	@Override
	public List<Message> saveFAQ(FaqDto faqDto) throws EntityNotFoundException {
		
		
		Long ideeOfLanguage = validateLanguageString(faqDto.getLanguage());

		if (faqDto.getId() != null){

			FaqEntity entity = faqRepository.findOne(faqDto.getId());

			if (entity == null) {
				entity = new FaqEntity();
				entity.setQuestion(faqDto.getQuestion());
				entity.setAnswer(faqDto.getAnswer());
				entity.setLanguage(faqDto.getLanguage());
				entity.setLanguageId(ideeOfLanguage);

				faqRepository.save(entity);
			} else {
				entity.setQuestion(faqDto.getQuestion());
				entity.setAnswer(faqDto.getAnswer());
				entity.setLanguage(faqDto.getLanguage().trim());
				entity.setLanguageId(ideeOfLanguage);

				faqRepository.save(entity);
			}
		} 
		else {
			FaqEntity entity = new FaqEntity();
			entity.setQuestion(faqDto.getQuestion());
			entity.setAnswer(faqDto.getAnswer());
			entity.setLanguage(faqDto.getLanguage());
			entity.setLanguageId(ideeOfLanguage);

			faqRepository.save(entity);
		}

		return null;
	}

	private Long validateLanguageString(String language) throws EntityNotFoundException {
		
		if (language == null || language.trim().isEmpty()) {
			throw new EntityNotFoundException(language+" not found");
		}
		
		List<FaqLanguageEntity> lista = faqLanguageRepository.findByLanguageCode(language.trim());
		
		if (lista == null || lista.isEmpty()) {
			throw new EntityNotFoundException(language+" not supported");
		}
		//else
		return lista.get(0).getId();
	}

	@Override
	public List<FaqLanguageEntity> getAllAvailableLangs() {
		// TODO Auto-generated method stub
		return faqLanguageRepository.findAll();
	}

	@Override
	public Long getAllFAQsCount() {

		return faqRepository.count();
	}

	@Override
	public FaqEntity getFAQById(Long idee) {

		return faqRepository.findOne(idee);
	}
	
	@Override
	public List<FaqEntity> searchFAQsByKeyWordAndLanguage(String language , String keyWord) {
		if (language == null || language.trim().isEmpty()) {
			language = "ar";
		}
		if (keyWord == null || keyWord.trim().isEmpty()) {
			return new ArrayList<>();
		}
		return faqRepository.searchForString(keyWord, language);
	}
}
