package com.appzoneltd.lastmile.microservice.hubconfig.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.hubconfig.dao.ContactUsEmailJpaRepository;
import com.appzoneltd.lastmile.microservice.hubconfig.dao.ContactUsJpaRepository;
import com.appzoneltd.lastmile.microservice.hubconfig.dto.ContactUsEmailDto;
import com.appzoneltd.lastmile.microservice.hubconfig.dto.ContactusDto;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.ContactUsEmailEntity;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.ContactUsEntity;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class ContactUsServiceImp implements ContactUsService {

	private final ContactUsJpaRepository contactUsJpaRepository;

	private final ContactUsEmailJpaRepository contactUsEmailJpaRepository;

	@Autowired
	public ContactUsServiceImp(ContactUsJpaRepository contactUsJpaRepository,
			ContactUsEmailJpaRepository contactUsEmailJpaRepository) {

		this.contactUsJpaRepository = contactUsJpaRepository;
		this.contactUsEmailJpaRepository = contactUsEmailJpaRepository;
	}

	@Override
	public ContactUsEntity saveContactUs(ContactusDto contactusDto) {
		ContactUsEntity contactUsEntity = contactUsJpaRepository.findByLanguage(contactusDto.getLang().toLowerCase());
		if (contactUsEntity == null ) {
			contactUsEntity = savingContact(contactusDto);
		}
		contactUsEntity = updateContactUs(contactusDto);
		return contactUsEntity;

	}

	public ContactUsEntity savingContact(ContactusDto contactusDto) {
		Long id = Utils.generateUUID();
		ContactUsEmailEntity contactUsEmailEntity = null;
		ContactUsEntity contactUsEntity = new ContactUsEntity();
		contactUsEntity.setId(id);
		contactUsEntity.setDailyWorkingHoursFrom(contactusDto.getDailyWorkingHoursFrom());
		contactUsEntity.setDailyWorkingHoursTo(contactusDto.getDailyWorkingHoursTo());
		contactUsEntity.setHotlinenumber(contactusDto.getHotlineNumber());
		contactUsEntity.setHotlinetitle(contactusDto.getHotlineTitle());
		contactUsEntity.setLanguage(contactusDto.getLang().toLowerCase());
		contactUsEntity.setVacationWorkingHoursFrom(contactusDto.getVacationWorkingHoursFrom());
		contactUsEntity.setVacationWorkingHoursTo(contactusDto.getVacationWorkingHoursTo());

		contactUsJpaRepository.save(contactUsEntity);
		for (ContactUsEmailDto contactUsEmailDto : contactusDto.getEmails()) {
			contactUsEmailEntity = new ContactUsEmailEntity();
			contactUsEmailEntity.setId(Utils.generateUUID());
			contactUsEmailEntity.setEmailAddress(contactUsEmailDto.getEmailAddress());
			contactUsEmailEntity.setEmailTitle(contactUsEmailDto.getEmailTitle());
			contactUsEmailEntity.setContactUs(contactUsEntity);
			contactUsEmailJpaRepository.save(contactUsEmailEntity);

		}

		return contactUsEntity;

	}

	@Override
	public ContactusDto findContactUsByLanguage(String language) {
		List<ContactUsEmailDto> contactUsEmailDtos = new ArrayList<>();
		ContactUsEmailDto contactUsEmailDto = null;

		ContactUsEntity contactUsEntity = contactUsJpaRepository.findByLanguage(language.toLowerCase());

		if (contactUsEntity != null) {
			ContactusDto contactusDto = new ContactusDto();
			List<ContactUsEmailEntity> contactUsEmailEntities = contactUsEmailJpaRepository
					.findAllByContactUsId(contactUsEntity.getId());

			for (ContactUsEmailEntity emailEntity : contactUsEmailEntities) {
				contactUsEmailDto = new ContactUsEmailDto();
				contactUsEmailDto.setEmailAddress(emailEntity.getEmailAddress());
				contactUsEmailDto.setEmailTitle(emailEntity.getEmailTitle());
				contactUsEmailDtos.add(contactUsEmailDto);

			}

			contactusDto.setLang(contactUsEntity.getLanguage());
			contactusDto.setDailyWorkingHoursFrom(contactUsEntity.getDailyWorkingHoursFrom());
			contactusDto.setDailyWorkingHoursTo(contactUsEntity.getDailyWorkingHoursTo());
			contactusDto.setEmails(contactUsEmailDtos);
			contactusDto.setHotlineNumber(contactUsEntity.getHotlinenumber());
			contactusDto.setHotlineTitle(contactUsEntity.getHotlinetitle());
			contactusDto.setVacationWorkingHoursFrom(contactUsEntity.getVacationWorkingHoursFrom());
			contactusDto.setVacationWorkingHoursTo(contactUsEntity.getVacationWorkingHoursTo());

			return contactusDto;

		}

		return null;
	}

	@Override
	public ContactUsEntity updateContactUs(ContactusDto contactusDto) {

		ContactUsEntity contactUsEntity = contactUsJpaRepository.findByLanguage(contactusDto.getLang().toLowerCase());

		List<ContactUsEmailEntity> contactUsEmailEntities = contactUsEmailJpaRepository
				.findAllByContactUsId(contactUsEntity.getId());

		for (ContactUsEmailEntity emailEntity : contactUsEmailEntities) {
			contactUsEmailJpaRepository.delete(emailEntity);
		}

		contactUsJpaRepository.delete(contactUsEntity);

		ContactUsEntity entity = savingContact(contactusDto);

		return entity;

	}

}
