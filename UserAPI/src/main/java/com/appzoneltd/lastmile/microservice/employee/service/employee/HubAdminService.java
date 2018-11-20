package com.appzoneltd.lastmile.microservice.employee.service.employee;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.entity.RoleEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.service.RoleService;
import com.appzoneltd.lastmile.microservice.employee.service.UserHubService;
import com.appzoneltd.lastmile.microservice.employee.service.UserTypeService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class HubAdminService extends EmployeeAbstractService{

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserHubService userHubService;

	
	public List<Message> saveOrUpdate(Employee employee, Principal principal) {
		List<Message> messages = checkHubAdminExists(employee, principal);
		if (messages != null && messages.size() > 0)
			return messages;
		else {
			UserTypeEntity userTypeEntity = userTypeService.getUserTypeEntity(employee.getUserTypeId());
			if(userTypeEntity !=null){
				RoleEntity roleEntity = roleService.getRoleByName(userTypeEntity.getName());
				if(roleEntity !=null){
					List<Long> roles= employee.getRoles();
					if(roles == null){
						roles = new ArrayList<>();
					}
					roles.add(roleEntity.getRoleId());
					employee.setRoles(roles);
				}
			}
			if(employee.getUserId()==null){
				employee.setUserId(Utils.generateUUID());
			}
			return saveUserData(employee);
		}
	}
	

	private List<Message> checkHubAdminExists(Employee employee, Principal principal) {
		List<Message> messages = new ArrayList<>();		
		if (employee.getHubs() == null || employee.getHubs().size() == 0) {
			messages.add(new Message(MessageType.ERROR, "hubId",
					messageSource.getMessage("user.hubId.hubNotAvailable", null, LocaleContextHolder.getLocale())));
			return messages;
		}
		if(userHubService.isUserRelatedToHub(employee.getUserId(), employee.getHubs().get(0)))
			return null;
		if (userHubService.isHubAdminExistsForHub(employee.getHubs().get(0))) {
			messages.add(new Message(MessageType.ERROR, "hubId",
					messageSource.getMessage("user.hubId.hubAlreadyAssigned", null, LocaleContextHolder.getLocale())));
		}
		return messages;
	}

}
