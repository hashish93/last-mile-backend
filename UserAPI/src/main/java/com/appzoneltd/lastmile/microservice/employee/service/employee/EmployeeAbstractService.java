package com.appzoneltd.lastmile.microservice.employee.service.employee;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserRepository;
import com.appzoneltd.lastmile.microservice.employee.entity.CountryCodesEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.service.PrincipalService;
import com.appzoneltd.lastmile.microservice.employee.service.UserService;
import com.appzoneltd.lastmile.microservice.employee.service.UserTypeService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@Service
public abstract class EmployeeAbstractService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PrincipalService principalService;
	
	@Autowired
	private CountryCodesRepository countryCodesRepository;
	
	protected abstract List<Message> saveOrUpdate(Employee employee, Principal principal);
	
	protected List<Message> saveUserData(Employee employee) {
		UserEntity userEntity = null;
		if (employee.getUserId() != null) {
			userEntity = userRepository.findActiveUserById(employee.getUserId());
		}

		if (userEntity == null) {
			userEntity = new UserEntity();
			userEntity.setPassword(employee.getPassword());
			userEntity.setCreated(new Date());
			userEntity.setVersion(0L);
		}

		List<Message> messages = checkIfFieldsExistance(employee);
		if (messages.size() > 0) {
			return messages;
		}

		userEntity.setUserId(employee.getUserId());
		userEntity.setFirstname(employee.getFirstName());
		userEntity.setLastname(employee.getLastName());
		userEntity.setUsername(employee.getUsername());
		userEntity.setStatus(employee.getUserEntityStatus().getValue());
		userEntity.setEmail(employee.getEmail());
		userEntity.setNationalId(employee.getNationalId());
		userEntity.setPhone(employee.getPhone());
		userEntity.setDescription(employee.getDescription());
		userEntity.setPersonalPhotoId(employee.getPersonalPhotoId());
		userEntity.setEnabled(employee.getEnabled());

		CountryCodesEntity countryCodesEntity = countryCodesRepository.findOne(employee.getCountryCodeId());
		userEntity.setCountryCodes(countryCodesEntity);

		UserTypeEntity userTypeEntity = userTypeService.getUserTypeEntity(employee.getUserTypeId());
		userEntity.setUserType(userTypeEntity);

		userRepository.save(userEntity);

		userEntity = userService.assignHubsForUser(userEntity, employee.getHubs());
		userEntity = userService.assignRolesForUser(userEntity, employee.getRoles());

		userRepository.save(userEntity);

		return null;
	}
	
	protected Employee SetHubForEmployee(Employee employee, Principal principal) {
		Long hubId = principalService.getHubIdIfFound(principal);
		if (hubId != null) {
			employee.setHubs(null);
			List<Long> userHubs = new ArrayList<>();
			userHubs.add(hubId);
			employee.setHubs(userHubs);
		}
		return employee;
	}
	
	protected List<Message> checkIfFieldsExistance(Employee employee) {
		List<Message> messages = new ArrayList<Message>();
		boolean isNationalIdExist = checkNationalIdExists(employee);
		boolean isEmailExist = checkEmailExists(employee);
		boolean isMobileExist = checkPhoneExists(employee);
		boolean isUserNameExist = checkUserNameExists(employee);

		if (isNationalIdExist && isEmailExist && isMobileExist && isUserNameExist) {
			messages.add(new Message(MessageType.ERROR, "username",
					messageSource.getMessage("user.userid.exist", null, LocaleContextHolder.getLocale())));
		} else {
			if (isNationalIdExist) {
				messages.add(new Message(MessageType.ERROR, "nationalId",
						messageSource.getMessage("user.nationalid.exist", null, LocaleContextHolder.getLocale())));
			}
			if (isEmailExist) {
				messages.add(new Message(MessageType.ERROR, "email",
						messageSource.getMessage("user.email.emailexist", null, LocaleContextHolder.getLocale())));
			}
			if (isMobileExist) {
				messages.add(new Message(MessageType.ERROR, "mobile",
						messageSource.getMessage("user.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
			}
			if (isUserNameExist) {
				messages.add(new Message(MessageType.ERROR, "userName", messageSource
						.getMessage("user.username.usernameexist", null, LocaleContextHolder.getLocale())));
			}
		}

		return messages;
	}

	private boolean checkUserNameExists(Employee employee) {
		UserEntity userEntity = userRepository.checkUserNameExists(employee.getUserId(), employee.getUsername());
		return userEntity != null;
	}

	private boolean checkNationalIdExists(Employee employee) {
		UserEntity userEntity = userRepository.checkNationalIdExists(employee.getUserId(), employee.getNationalId());
		return userEntity != null;
	}

	private boolean checkEmailExists(Employee employee) {
		UserEntity userEntity = userRepository.checkEmailExists(employee.getUserId(), employee.getEmail());
		return userEntity != null;
	}

	private boolean checkPhoneExists(Employee employee) {
		UserEntity userEntity = userRepository.checkPhoneExists(employee.getUserId(), employee.getPhone());
		return userEntity != null;
	}

}
