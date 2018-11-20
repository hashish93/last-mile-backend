package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.Employee;
import com.appzoneltd.lastmile.microservice.employee.dao.EmployeeRepositoryImp;
import com.appzoneltd.lastmile.microservice.employee.dao.UserEntity;
import com.appzoneltd.lastmile.microservice.employee.dao.UserJpaRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.dao.UserTypeRepository;
import com.appzoneltd.lastmile.microservice.employee.dto.SearchEndPoint;
import com.appzoneltd.lastmile.microservice.employee.dto.UserProfileDTO;
import com.appzoneltd.lastmile.microservice.employee.dto.UserTypeDto;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepositoryImp employeeRepositoryImp;
    private final MessageSource messageSource;
    private final UserJpaRepository userJpaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeServiceImp(EmployeeRepositoryImp employeeRepositoryImp, UserJpaRepository userJpaRepository,
                              MessageSource messageSource) {
        this.employeeRepositoryImp = employeeRepositoryImp;
        this.messageSource = messageSource;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Object saveEmployee(Employee employee) {
        Long employeeID = Utils.generateUUID();
        employee.setUserId(employeeID);
        List<Message> messages = new ArrayList<Message>();

        boolean isNationalIdExist = employeeRepositoryImp.isAlreadyExistsWithoutStatus(
                "lastmile_core.back_office_employee", "national_id", employee.getNationalId());
        boolean isNationalIdExistInDriver = employeeRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_core.driver",
                "national_id", employee.getNationalId());
        boolean isEmailExist = employeeRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_authorization_server.users",
                "email", employee.getEmail().toLowerCase());
        boolean isMobileExist = employeeRepositoryImp
                .isAlreadyExistsWithoutStatus("lastmile_authorization_server.users", "phone", employee.getPhone());

        if ((isNationalIdExist || isNationalIdExistInDriver) && isEmailExist && isMobileExist) {
            messages.add(new Message(MessageType.ERROR, "username",
                    messageSource.getMessage("employee.userid.exist", null, LocaleContextHolder.getLocale())));
        } else {
            if (isNationalIdExist || isNationalIdExistInDriver) {
                messages.add(new Message(MessageType.ERROR, "nationalId",
                        messageSource.getMessage("employee.nationalid.exist", null, LocaleContextHolder.getLocale())));
            }
            if (isEmailExist) {
                messages.add(new Message(MessageType.ERROR, "email",
                        messageSource.getMessage("employee.email.emailexist", null, LocaleContextHolder.getLocale())));
            }
            if (isMobileExist) {
                messages.add(new Message(MessageType.ERROR, "mobile", messageSource
                        .getMessage("employee.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
            }
        }
        if (!messages.isEmpty()) {
            return messages;
        } else {
            Integer result = employeeRepositoryImp.createBackOfficeEmployee(employee);
            if (result == 1) {
                return employeeID;
            } else {
                return null;
            }

        }
    }

    // public DriverDTO getDriverProfile(Principal principal){
    // Long employeeId = getUserId(principal.getName());
    // Employee employee = findEmployeeById(employeeId);
    // DriverDTO driverDTO = null;
    // if (employee != null) {
    // driverDTO =new DriverDTO();
    // driverDTO.setDriverId(employee.getUserId());
    // driverDTO.setEmail(employee.getEmail());
    // driverDTO.setFirstName(employee.getFirstName());
    // driverDTO.setImageId(employee.getPersonalPhotoId());
    // driverDTO.setLastName(employee.getLastName());
    // driverDTO.setNationalId(employee.getNationalId());
    // driverDTO.setPhoneNumber(employee.getPhone());
    // }
    // return driverDTO;
    // }

    public UserProfileDTO getEmployeeByPrincipal(Principal principal) {
        Long employeeId = getUserId(principal.getName());
        Employee employee = findEmployeeById(employeeId);
        UserProfileDTO userProfileDTO = null;
        if (employee != null) {
            userProfileDTO = new UserProfileDTO();
            userProfileDTO.setUserId(employeeId);
            userProfileDTO.setFirstName(employee.getFirstName());
            userProfileDTO.setLastName(employee.getLastName());
            userProfileDTO.setEmail(employee.getEmail());
            userProfileDTO.setNationalId(employee.getNationalId());
            userProfileDTO.setPhoneNumber(employee.getPhone());
            userProfileDTO.setImageId(employee.getPersonalPhotoId());
        }
        return userProfileDTO;
    }

    public List<Message> updateUserPassword(UserProfileDTO userProfileDTO) {
        List<Message> messages = new ArrayList<Message>();
        UserEntity userEntity = userJpaRepository.findByUserId(userProfileDTO.getUserId());

        if (!userEntity.getPassword().equals(userProfileDTO.getOldPassword())) {
            messages.add(new Message(MessageType.ERROR, "oldPassword",
                    messageSource.getMessage("employee.update.oldpassword", null, LocaleContextHolder.getLocale())));
        }

        if (!userProfileDTO.getNewPassword().equalsIgnoreCase(userProfileDTO.getRepeatedPassword())) {
            messages.add(new Message(MessageType.ERROR, "newPassword",
                    messageSource.getMessage("employee.update.newpassword", null, LocaleContextHolder.getLocale())));
        }

        if (messages.isEmpty()) {
            userEntity.setPassword(userProfileDTO.getNewPassword());
            userJpaRepository.save(userEntity);

        }

        return messages;
    }

    public Object updateEmployeeProfile(UserProfileDTO userProfileDTO) {
        UserEntity userEntity = userJpaRepository.findByUserId(userProfileDTO.getUserId());
        List<Message> messages = new ArrayList<Message>();

        if (!userProfileDTO.getEmail().toLowerCase().equals(userEntity.getEmail().toLowerCase())) {
            boolean isEmailExist = employeeRepositoryImp.isAlreadyExistsWithoutStatus(
                    "lastmile_authorization_server.users", "email", userProfileDTO.getEmail());
            if (isEmailExist) {
                messages.add(new Message(MessageType.ERROR, "email",
                        messageSource.getMessage("employee.email.emailexist", null, LocaleContextHolder.getLocale())));
            }
        }

        if (!userProfileDTO.getPhoneNumber().equals(userEntity.getPhone())) {
            boolean isMobileExist = employeeRepositoryImp.isAlreadyExistsWithoutStatus(
                    "lastmile_authorization_server.users", "phone", userProfileDTO.getPhoneNumber());
            if (isMobileExist) {
                messages.add(new Message(MessageType.ERROR, "mobile", messageSource
                        .getMessage("employee.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
            }

        }

        if (!messages.isEmpty()) {
            return messages;
        } else {
            userEntity.setFirstname(userProfileDTO.getFirstName());
            userEntity.setLastname(userProfileDTO.getLastName());
            userEntity.setEmail(userProfileDTO.getEmail());
            userEntity.setCountryCodeId(userProfileDTO.getCountryCodeId());
            userEntity.setPhone(userProfileDTO.getPhoneNumber());
            userEntity.setPersonalPhotoId(userProfileDTO.getImageId());
            userEntity.setUsername(userProfileDTO.getUserName());

            return userJpaRepository.save(userEntity);

        }

    }

    @Override
    public List<Employee> searchByKey(SearchEndPoint searchEndPoint) {
        String key = searchEndPoint.getKey();
        if (key == null) {
            key = "";
        }
        PageRequest pageRequest = null;
        if (searchEndPoint.getPage() != 0) {
            pageRequest = new PageRequest(searchEndPoint.getPage() - 1, searchEndPoint.getMaxResult(),
                    Sort.Direction.fromString(searchEndPoint.getOrderBy().getOrderBy()), "created");
        }
        return mapUserToEmployee(userJpaRepository.findByKey("%" + key + "%", pageRequest));
    }

    private List<Employee> mapUserToEmployee(List<UserEntity> users) {
        if (!users.isEmpty()) {
            List<Employee> employees = new ArrayList<>();
            for (UserEntity user : users) {
                Employee employee = new Employee();
                employee.setUserId(user.getUserId());
                employee.setUsername(user.getUsername());
                employee.setCreated(user.getCreated());
                employee.setEmail(user.getEmail());
                employee.setPhone(user.getPhone());
                employee.setUserType(UserType.valueOf(user.getUserType()));
                employee.setCreated(user.getCreated());
                employees.add(employee);
            }
            return employees;
        }
        return null;
    }

    @Override
    public Integer countSearchByKey(String key) {
        return userJpaRepository.findCountByKey("%" + key + "%").size();
    }

    @Override
    public Object updateEmployee(Employee employee) throws EntityNotFoundException {
        isCustomerExistsAndActive(employee.getUserId());
        List<Message> messages = new ArrayList<Message>();

        Employee oldEmployee = employeeRepositoryImp.queryforObject(employee.getUserId());

        if (!employee.getNationalId().equals(oldEmployee.getNationalId())) {
            boolean isNationalIdExist = employeeRepositoryImp.isAlreadyExistsWithoutStatus(
                    "lastmile_core.back_office_employee", "national_id", employee.getNationalId());
            boolean isNationalIdExistInDriver = employeeRepositoryImp
                    .isAlreadyExistsWithoutStatus("lastmile_core.driver", "national_id", employee.getNationalId());
            if (isNationalIdExist || isNationalIdExistInDriver) {
                messages.add(new Message(MessageType.ERROR, "nationalId",
                        messageSource.getMessage("employee.nationalid.exist", null, LocaleContextHolder.getLocale())));
            }
        }

        if (!employee.getEmail().toLowerCase().equals(oldEmployee.getEmail().toLowerCase())) {
            boolean isEmailExist = employeeRepositoryImp
                    .isAlreadyExistsWithoutStatus("lastmile_authorization_server.users", "email", employee.getEmail());
            if (isEmailExist) {
                messages.add(new Message(MessageType.ERROR, "email",
                        messageSource.getMessage("employee.email.emailexist", null, LocaleContextHolder.getLocale())));
            }
        }

        if (!employee.getPhone().equals(oldEmployee.getPhone())) {
            boolean isMobileExist = employeeRepositoryImp
                    .isAlreadyExistsWithoutStatus("lastmile_authorization_server.users", "phone", employee.getPhone());
            if (isMobileExist) {
                messages.add(new Message(MessageType.ERROR, "mobile", messageSource
                        .getMessage("employee.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
            }

        }

        if (!messages.isEmpty()) {
            return messages;
        } else {
            return employeeRepositoryImp.updateBackOfficeEmployee(employee);
        }

    }

    @Override
    public Employee findEmployeeById(Long employeeId) {
        return employeeRepositoryImp.queryforObject(employeeId);
    }

    @Override
    public List<Employee> findAllEmployeesByPage(int pageCount, int pageNum, OrderBy orderType) {
        return employeeRepositoryImp.queryWithPaging(pageNum, pageCount, orderType);
    }

    @Override
    public int markDeleteEmployee(Long employeeId) throws JsonProcessingException {
        return employeeRepositoryImp.deactivate(employeeId);
    }

    @Override
    public int countAllEmployees() {
        return employeeRepositoryImp.count();
    }

    private void isCustomerExistsAndActive(Long customerId) throws EntityNotFoundException {
        if (!employeeRepositoryImp.isAlreadyExists("lastmile_authorization_server.users", "user_id", customerId)) {
            throw new EntityNotFoundException("customer.notfound");
        }
    }

    private Long getUserId(String credentials) {
        return jdbcTemplate.queryForObject(
                "SELECT user_id FROM lastmile_authorization_server.users WHERE email=? OR phone=?", Long.class,
                credentials, credentials);
    }
    
    @Autowired  
    private UserTypeRepository userTypeRepository;    

@Override 
    public List<UserTypeDto> getUserTypesDtos(){ 
      List<UserTypeEntity>userTypeEntities =  (List<UserTypeEntity>) userTypeRepository.findAll(); 
      return getUserTypeDtosFromEntities(userTypeEntities); 
    } 
     
    private List<UserTypeDto> getUserTypeDtosFromEntities(List<UserTypeEntity> userTypeEntities){ 
      List<UserTypeDto> userTypeDtos = new ArrayList<>(); 
      for(UserTypeEntity userTypeEntity : userTypeEntities){ 
        UserTypeDto userTypeDto = new UserTypeDto(); 
        userTypeDto.setName(userTypeEntity.getName()); 
        userTypeDto.setValue(userTypeEntity.getId()); 
        userTypeDtos.add(userTypeDto); 
      } 
      return userTypeDtos; 
    } 
}
