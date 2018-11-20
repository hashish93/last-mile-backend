package com.appzoneltd.lastmile.microservice.controller;

import com.appzoneltd.lastmile.microservice.DTO.FaqDto;
import com.appzoneltd.lastmile.microservice.DTO.OldPaging;
import com.appzoneltd.lastmile.microservice.DTO.PageRequestDto;
import com.appzoneltd.lastmile.microservice.Entity.FaqEntity;
import com.appzoneltd.lastmile.microservice.Entity.FaqLanguageEntity;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hashish on 4/25/2017.
 */
@RestController
public class FaqController {

    @Autowired
    private FaqService faqService;
    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasRole('editfaq')")
    @RequestMapping(value = "/saveFaq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFaq(@Validated @RequestBody FaqDto faqDto) {
        List<Message> messages = null;
        try {

            messages = faqService.saveFAQ(faqDto);

            if (messages != null) {

                return new ResponseEntity<List<?>>(messages, HttpStatus.BAD_REQUEST);

            } else {
                return new ResponseEntity<Message>(
                        new Message(MessageType.SUCCESS, "created", messageSource.getMessage("faq.created", null,
                                "faq.created", LocaleContextHolder.getLocale())),
                        HttpStatus.OK);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<Message>(new Message(MessageType.ERROR, "language",
                    messageSource.getMessage("invalid.language", null,
                            "invalid.language", LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAnyRole('listfaq','editfaq')")
    @RequestMapping(value = "/getAllFeq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> getAllFeq(@RequestBody OldPaging oldPagingObject) {

        List<FaqEntity> faqEntities = null;
        PageRequest page1 = new PageRequest(oldPagingObject.getPage(), oldPagingObject.getMaxResult(),
                Sort.Direction.fromString(oldPagingObject.getOrderBy()), "id");

        faqEntities = (List<FaqEntity>) faqService.getAllFaq(page1);
        return new ResponseEntity<>(faqEntities, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('editfaq')")
    @RequestMapping(value = "/deleteFaq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFaq(@Validated @RequestBody Map<String, Long> map) {
        boolean result = faqService.deleteFaq(map.get("id"));
        if (result) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "id", messageSource.getMessage("faq.deleted", null,
                            "faq.deleted", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Message>(
                new Message(MessageType.ERROR, "id", messageSource.getMessage("faq.notdeleted", null,
                        "faq.deleted", LocaleContextHolder.getLocale())),
                HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getFAQsByLanguage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> getFAQsByLanguage(@RequestBody PageRequestDto pageRequestDto) {

        List<FaqEntity> faqEntities = null;

        PageRequest page1 = new PageRequest(pageRequestDto.getPageOffset(), pageRequestDto.getPageSize(),
                Sort.Direction.fromString(pageRequestDto.getOrderBy()), "id");

        faqEntities = (List<FaqEntity>) faqService.getAllFaqsByLang(LocaleContextHolder.getLocale().getLanguage(), page1);

        return new ResponseEntity<>(faqEntities, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('listfaq','editfaq')")
    @RequestMapping(value = "/getAvailableLanguages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> getAvailableLanguages() {

        List<FaqLanguageEntity> languagesList = null;

        languagesList = faqService.getAllAvailableLangs();

        return new ResponseEntity<>(languagesList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('listfaq','editfaq')")
    @RequestMapping(value = "/getAllFAQsCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> getAllFAQsCount() {

        Long countAll = faqService.getAllFAQsCount();

        Map<String, Long> mapa = new HashMap<>();
        mapa.put("property", countAll);

        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('listfaq','editfaq')")
    @RequestMapping(value = "/getFaqById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FaqEntity> getFaqById(@Validated @RequestBody Map<String, Long> map) {

        Long idee = map.get("id");

        FaqEntity entity = faqService.getFAQById(idee);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    
    

    @RequestMapping(value = "/searchFaqs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> searchFAQs(@RequestBody Map<String, String> map) {

    	String keyWord = map.get("keyWord");
    	
        List<FaqEntity> outList = faqService.searchFAQsByKeyWordAndLanguage(LocaleContextHolder.getLocale().getLanguage() , keyWord);


        return new ResponseEntity<>(outList, HttpStatus.OK);
    }
}
