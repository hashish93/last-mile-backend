package com.appzoneltd.lastmile.microservice.employee.controller;

import com.appzoneltd.lastmile.microservice.employee.dto.HubDto;
import com.appzoneltd.lastmile.microservice.employee.dto.NewEmail;
import com.appzoneltd.lastmile.microservice.employee.dto.UserProfileDTO;
import com.appzoneltd.lastmile.microservice.employee.dto.UserTypeDto;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.model.Driver;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.model.Parameter;
import com.appzoneltd.lastmile.microservice.employee.service.ActiveVehicleService;
import com.appzoneltd.lastmile.microservice.employee.service.BuildingService;
import com.appzoneltd.lastmile.microservice.employee.service.UserService;
import com.appzoneltd.lastmile.microservice.employee.service.UserTypeService;
import com.appzoneltd.lastmile.microservice.employee.service.employee.EmployeeService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ActiveVehicleService activeVehicleService;


    @RequestMapping(method = RequestMethod.POST, value = "/getemployeebyid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserById(@RequestBody Parameter parameter) {
        if ((parameter.getUserId() != null) && (parameter.getUserTypeId() != null)) {
            UserTypeEntity userTypeEntity = userTypeService.getUserTypeEntity(parameter.getUserTypeId());
            if (userTypeEntity != null) {
                if ("CORPORATE_DRIVER".equalsIgnoreCase(userTypeEntity.getName())) {
                    Driver driver = userService.getDriverById(parameter.getUserId());
                    if (driver != null) {
                        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
                    } else {
                        Message message = new Message(MessageType.ERROR, "Driver", "Driver not found");
                        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
                    }
                } else {
                    Employee employee = userService.getEmployeeById(parameter.getUserId());
                    if (employee != null) {
                        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
                    } else {
                        Message message = new Message(MessageType.ERROR, "User", "User not found");
                        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
        Message message = new Message(MessageType.ERROR, "Data Missed", "Data Missed");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_deleteemployee')")
    @RequestMapping(method = RequestMethod.POST, value = "/deleteEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEmployee(@RequestBody Parameter parameter) {
        if ((parameter.getUserId() != null)) {

            List<Message> messages = userService.deleteUser(parameter.getUserId(), parameter.getStatus());
            if (messages != null) {
                return new ResponseEntity<>(messages, HttpStatus.CONFLICT);
            } else {
                Message message = new Message(MessageType.SUCCESS, "User", "User deleted Successfully");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }

        }
        Message message = new Message(MessageType.ERROR, "Data Missed", "Data Missed");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employeeWithActiveVehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeeWithActiveVehicle(@RequestBody Parameter parameter) {
        if ((parameter.getUserId() != null)) {
            Driver driver = userService.getDriverById(parameter.getUserId());
            if (driver == null) {
                Message message = new Message(MessageType.ERROR, "User", "User not found");
                return new ResponseEntity<>(message, HttpStatus.CONFLICT);
            }
            boolean result = activeVehicleService.isDriverRelatedToActiveVehicle(driver.getUserId());
            if (result == true) {
                Message message = new Message(MessageType.ERROR, "User", "User related to active vehicle Successfully");
                return new ResponseEntity<>(message, HttpStatus.CONFLICT);
            } else {
                Message message = new Message(MessageType.SUCCESS, "User",
                        "User not related to active vehicle Successfully");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }
        Message message = new Message(MessageType.ERROR, "Data Missed", "Data Missed");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findAvailableHubs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAvailableHubs(@RequestBody Parameter parameter) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (parameter.getUserTypeId() != null) {
            List<HubDto> hubDtos = buildingService.getAllBuildings(parameter.getUserTypeId(), parameter.getUserId(), principal);
            if (hubDtos != null) {
                return new ResponseEntity<List<HubDto>>(hubDtos, HttpStatus.OK);
            }
            Message message = new Message(MessageType.ERROR, "UserType", "UserType not found");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        Message message = new Message(MessageType.ERROR, "UserType", "UserType not found");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employeetypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEmployees() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        List<UserTypeDto> userTypeDtos = userTypeService.getUserTypeDtos(principal);
        return new ResponseEntity<List<UserTypeDto>>(userTypeDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_listemployee')")
    @RequestMapping(method = RequestMethod.POST, value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchUsers(@RequestBody Parameter parameter) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        List<Employee> result = userService.getEmployeesByKey(parameter, principal);
        if (result == null || result.isEmpty()) {
            result = new ArrayList<>();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_listemployee')")
    @RequestMapping(method = RequestMethod.POST, value = "/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchUsersCount(@RequestBody Parameter parameter) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        parameter.setPage(0);
        List<Employee> result = userService.getEmployeesByKey(parameter, principal);
        if (result == null || result.isEmpty()) {
            result = new ArrayList<>();
        }

        return new ResponseEntity<>(new Message(MessageType.SUCCESS, Integer.toString(result.size()),
                messageSource.getMessage("employee.countall.success", null, "employee.countall.success",
                        LocaleContextHolder.getLocale())),
                HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_addeditemployee')")
    @RequestMapping(value = "/createemployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createemployee(@Validated @RequestBody Employee employee) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        List<Message> messages = employeeService.saveOrUpdateEmployee(employee, principal);
        if (messages == null || messages.size() == 0) {
            return new ResponseEntity<>(
                    new Message(MessageType.SUCCESS, "SUCCESS", messageSource.getMessage("employee.save.success", null,
                            "employee.save.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_addeditemployee')")
    @RequestMapping(value = "/createDriver", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDriver(@Validated @RequestBody Driver driver) throws JsonProcessingException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        List<Message> messages = employeeService.saveOrUpdateDriver(driver, principal);
        if (messages == null || messages.size() == 0) {
            return new ResponseEntity<>(
                    new Message(MessageType.SUCCESS, "SUCCESS", messageSource.getMessage("employee.save.success", null,
                            "employee.save.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_listemployee')")
    @RequestMapping(method = RequestMethod.POST, value = "/getalldrivers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDrivers(@RequestBody Parameter parameter) {
        List<Employee> drivers = null;
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        drivers = employeeService.getAllDrivers(parameter, principal);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_listemployee')")
    @RequestMapping(method = RequestMethod.POST, value = "/getalldriversforhub", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDriversForHub(@RequestBody Parameter parameter) {
        List<Employee> drivers = null;
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        drivers = employeeService.getAllDriversForHub(parameter, principal);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeeProfile() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        UserProfileDTO userProfileDTO = employeeService.getEmployeeByPrincipal(principal);
        if (userProfileDTO != null) {
            return new ResponseEntity<UserProfileDTO>(userProfileDTO, HttpStatus.OK);
        }
        Message message = new Message(MessageType.ERROR, "userId", "User not found");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateprofile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployeeProfile(@RequestBody UserProfileDTO userProfileDTO) {
        Object result = employeeService.updateEmployeeProfile(userProfileDTO);

        if (result instanceof List) {
            @SuppressWarnings("unchecked")
            List<Message> messages = (List<Message>) result;
            return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<UserProfileDTO>(HttpStatus.OK);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateuserpassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserPassword(@RequestBody UserProfileDTO userProfileDTO) {
        List<Message> result = employeeService.updateUserPassword(userProfileDTO);
        if (!result.isEmpty()) {

            return new ResponseEntity<List<Message>>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<UserProfileDTO>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmail(@Validated @RequestBody NewEmail newEmail)
            throws DuplicatedKeyException, EntityNotFoundException, JsonProcessingException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        boolean result = employeeService.updateEmail(newEmail.getEmail(), principal);
        if (result) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("update.success",
                            null, "update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verifyUpdatedEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> verifyCustomer(@RequestBody Map<String, String> body)
            throws EntityNotFoundException {
        Boolean isVerified = employeeService.verifyUpdatedEmail(body.get("code"));
        if (isVerified)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, messageSource.getMessage("verified.success"
                    , null, LocaleContextHolder.getLocale())), HttpStatus.OK);

        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verified.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }

}
