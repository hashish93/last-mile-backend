package com.appzoneltd.lastmile.microservice.employee.controller;

import com.appzoneltd.lastmile.microservice.employee.dao.Driver;
import com.appzoneltd.lastmile.microservice.employee.dao.Employee;
import com.appzoneltd.lastmile.microservice.employee.dao.FindByIdControllerRequestObjectModel;
import com.appzoneltd.lastmile.microservice.employee.dto.SearchEndPoint;
import com.appzoneltd.lastmile.microservice.employee.dto.UserProfileDTO;
import com.appzoneltd.lastmile.microservice.employee.dto.UserTypeDto;
import com.appzoneltd.lastmile.microservice.employee.service.DriverService;
import com.appzoneltd.lastmile.microservice.employee.service.EmployeeService;
import com.appzoneltd.lastmile.microservice.employee.service.UserServiceImp;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class EmployeeController {

	private final DriverService driverService;
	private final EmployeeService employeeService;
	private final MessageSource messageSource;
	private final UserServiceImp userService;
	
	

	@Autowired
	public EmployeeController(DriverService driverService, EmployeeService employeeService, MessageSource messageSource,
			UserServiceImp userService) {
		this.driverService = driverService;
		this.employeeService = employeeService;
		this.messageSource = messageSource;
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employeetypes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		List <UserTypeDto> userTypeDtos = employeeService.getUserTypesDtos();
		return new ResponseEntity<List<UserTypeDto>>(userTypeDtos, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> employeeProfile() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		UserProfileDTO userProfileDTO = employeeService.getEmployeeByPrincipal(principal);
		return new ResponseEntity<UserProfileDTO>(userProfileDTO, HttpStatus.OK);
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

	@PreAuthorize("hasRole('ROLE_listemployee')")
	@RequestMapping(method = RequestMethod.POST, value = "/searchUsers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchUsers(@RequestBody SearchEndPoint searchEndPoint) {
		List<Employee> result = employeeService.searchByKey(searchEndPoint);
		if (result == null || result.isEmpty()) {
			result = new ArrayList<>();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_listemployee')")
	@RequestMapping(method = RequestMethod.POST, value = "/searchUsersCount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchUsersCount(@RequestBody SearchEndPoint searchEndPoint) {
		Integer result = employeeService.countSearchByKey(searchEndPoint.getKey());
		return new ResponseEntity<>(new Message(MessageType.SUCCESS, Integer.toString(result), messageSource.getMessage(
				"employee.countall.success", null, "employee.countall.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	/**
	 * Create Employee Service
	 *
	 * @throws EntityNotFoundException
	 **/
	@PreAuthorize("hasRole('ROLE_addeditemployee')")
	@RequestMapping(value = "/createemployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveEmployee(@Validated @RequestBody Driver driver)
			throws DuplicatedKeyException, EntityNotFoundException, JsonProcessingException {
		Object result = null;
		if (driver.getUserType().equals(UserType.BACK_OFFICE_EMPLOYEE))
			result = employeeService.saveEmployee(driver);
		if (driver.getUserType().equals(UserType.DRIVER))
			result = driverService.saveDriver(driver);

		if (result == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		if (result instanceof List) {
			@SuppressWarnings("unchecked")
			List<Message> messages = (List<Message>) result;
			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(
					new Message(MessageType.SUCCESS, result instanceof Long ? ((Long) result).toString() : null,
							messageSource.getMessage("employee.create.added", null, LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	@PreAuthorize("hasRole('ROLE_addeditemployee')")
	@RequestMapping(value = "/updateemployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(@Validated @RequestBody Driver driver) throws EntityNotFoundException {
		Object result = null;
		if (driver.getUserType().equals(UserType.BACK_OFFICE_EMPLOYEE))
			result = employeeService.updateEmployee(driver);
		if (driver.getUserType().equals(UserType.DRIVER))
			result = driverService.updateDriver(driver);
		if (result == null)
			throw new EntityNotFoundException("employee.notfound");

		if (result instanceof List) {
			@SuppressWarnings("unchecked")
			List<Message> messages = (List<Message>) result;
			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		} else {

			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("employee.update.updated",
							null, "employee.update.updated", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	@PreAuthorize("hasRole('ROLE_deleteemployee')")
	@RequestMapping(method = RequestMethod.POST, value = "/deleteemployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> deleteEmployee(@RequestBody FindByIdControllerRequestObjectModel requestModel)
			throws JsonProcessingException {
		int result = 0;
		if (requestModel.getUserType().equals(UserType.BACK_OFFICE_EMPLOYEE))
			result = employeeService.markDeleteEmployee(requestModel.getId());
		if (requestModel.getUserType().equals(UserType.DRIVER))
			result = driverService.markDeleteDriver(requestModel.getId());

		if (result == 0)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, Integer.toString(result), messageSource.getMessage(
							"employee.remove.error", null, "employee.remove.error", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, Integer.toString(result), messageSource.getMessage(
						"employee.remove.success", null, "employee.remove.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/employeeWithActiveVehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> chkDriverRelatedToActiveVehicle(@RequestBody EndPointParameter requestModel) {
		boolean result = driverService.checkDriverRelatedToActiveVehicle(requestModel.getId());
		if (result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, requestModel.getId().toString(),
							messageSource.getMessage("driver.related.activevehicle", null,
									"driver.related.activevehicle", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, requestModel.getId().toString(),
					messageSource.getMessage("employee.remove.success", null, "employee.remove.success",
							LocaleContextHolder.getLocale())),
					HttpStatus.OK);

	}

	/**
	 * May 15, 2016 Method to Get All Employee in DB
	 *
	 * @param companyId
	 * @param pageCount
	 * @param pageNum
	 * @param orderType
	 * @return ResponseEntity<?>
	 */
	@PreAuthorize("hasRole('ROLE_listemployee')")
	@RequestMapping(method = RequestMethod.POST, value = "/getallemployees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees(@RequestBody EndPointParameter requestModel) {
		List<Employee> employees = null;
		try {
			// employees = employeeService.findAllEmployeesByPage(
			// (requestModel.getMaxResult() / 2) + (requestModel.getMaxResult()
			// % 2), requestModel.getPage(),
			// requestModel.getOrderBy());
			// employees.addAll(driverService.findAllDriversByPage(requestModel.getMaxResult()
			// / 2, requestModel.getPage(),
			// requestModel.getOrderBy()));

			employees = userService.findAllEmployeeWithPage(requestModel.getPage(), requestModel.getMaxResult(),
					requestModel.getOrderBy());

			if (employees == null)
				return new ResponseEntity<List<Employee>>(employees, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return new ResponseEntity<>(sortAndPage(employees, requestModel),
		// HttpStatus.OK);
		return new ResponseEntity<>(employees, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_listemployee')")
	@RequestMapping(method = RequestMethod.POST, value = "/getalldrivers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllDrivers(@RequestBody EndPointParameter requestModel) {
		List<? extends Employee> employees = null;
		try {

			employees = driverService.findAllDriversByPage(requestModel.getMaxResult(), requestModel.getPage(),
					requestModel.getOrderBy());

			if (employees == null)
				return new ResponseEntity<List<? extends Employee>>(employees, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<List<? extends Employee>>(employees, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	/**
	 * May 15, 2016 Method to count all Employee in DB
	 *
	 * @param companyId
	 * @return ResponseEntity<?>
	 */
	@RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> countAllEmployee() {
		int countAllBackOfficeEmployees = employeeService.countAllEmployees();
		int countAllDrivers = driverService.countAllDrivers();
		return new ResponseEntity<Object>(
				new Message(MessageType.SUCCESS, Integer.toString(countAllBackOfficeEmployees + countAllDrivers),
						messageSource.getMessage("employee.countall.success", null, "employee.countall.success",
								LocaleContextHolder.getLocale())),
				(HttpStatus.OK));

	}

	/**
	 * May 15, 2016 Method to Get Employee by ID in DB
	 *
	 * @param Id
	 * @param companyId
	 * @return ResponseEntity<?>
	 * @throws DuplicatedKeyException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getemployeebyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeeByID(@RequestBody FindByIdControllerRequestObjectModel requestModel) {
		Employee employee = null;
		if (requestModel.getUserType().equals(UserType.BACK_OFFICE_EMPLOYEE))
			employee = employeeService.findEmployeeById(requestModel.getId());
		if (requestModel.getUserType().equals(UserType.DRIVER))
			employee = driverService.findDriverById(requestModel.getId());
		if (employee == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	private List<Employee> sortAndPage(List<Employee> employees, EndPointParameter requestModel) {
		employees.sort(new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getCreated().compareTo(o2.getCreated());
			}
		});

		if (requestModel.getOrderBy() != null && requestModel.getOrderBy() == OrderBy.DESC)
			employees.sort(Collections.reverseOrder(new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					return o1.getCreated().compareTo(o2.getCreated());
				}
			}));

		if (requestModel.getPage() > 0) {
			if (requestModel.getMaxResult() > employees.size())
				return employees;
			else
				return employees.subList(0, requestModel.getMaxResult());
		} else
			return employees;
	}

}
