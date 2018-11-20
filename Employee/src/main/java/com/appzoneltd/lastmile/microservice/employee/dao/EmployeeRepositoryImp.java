package com.appzoneltd.lastmile.microservice.employee.dao;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImp extends AbstractDao<Employee> implements EmployeeRepository {

    @Autowired
    public EmployeeRepositoryImp(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, new EmployeeRowMapper(), SQL_INSERT_USER, SQL_UPDATE_USER, SQL_DEACTIVATE_QUERY,
                SELECT_ALL_EMPLOYEES, SELECT_EMPLOYEE_BY_ID, null, COUNT_ALL_EMPLOYEES);
    }

    public Integer createBackOfficeEmployee(Employee employee) {
        Integer result = this.save(employee.getUserId(), employee.getUsername(), employee.getPassword(),
                employee.getFirstName().toLowerCase(), employee.getLastName().toLowerCase(), employee.getCountryCodeId(), employee.getPhone(),
                employee.getEmail().toLowerCase(), employee.getPersonalPhotoId(), employee.getDescription(),
                employee.getUserType().getValue());
        if (result == 1)
            return jdbcTemplate.update(SQL_INSERT_EMPLOYEE, employee.getUserId(), employee.getNationalId());
        else
            return null;
    }

    public Integer updateBackOfficeEmployee(Employee employee) {
        Integer result = this.update(employee.getUsername(), employee.getFirstName().toLowerCase(),
                employee.getLastName().toLowerCase(), employee.getCountryCodeId(), employee.getPhone(), employee.getEmail().toLowerCase(), employee.getEnabled(),
                employee.getPersonalPhotoId(), employee.getEntityStatus().getValue(), employee.getDescription(),
                employee.getVersion(), employee.getUserId());

        result = jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, employee.getNationalId(), employee.getUserId());
        if (result == 1)
            return result;
        else
            return null;
    }


}
