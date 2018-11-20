package com.appzoneltd.lastmile.microservice.customer.dto;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.customer.dao.CustomerRepository;
import com.appzoneltd.lastmile.microservice.customer.dao.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImp extends AbstractDao<CustomerInfo> implements CustomerRepository {

    @Autowired
    public CustomerRepositoryImp(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, new CustomerRowMapper(), SQL_INSERT_USER, SQL_UPDATE_USER, SQL_DEACTIVATE_QUERY,
                null, null, null, COUNT_ALL_CUSTOMERS);

    }

    public int createCustomer(Customer customer) {
        int result = this.save(customer.getUserId(), customer.getFirstName() + " " + customer.getLastName(), customer.getPassword(),
                customer.getFirstName(), customer.getLastName(), customer.getCountryCodeId(), customer.getPhone(), customer.getEmail().toLowerCase(),
                customer.getPersonalPhoto(), customer.getDescription(), customer.getUserTypeId());
        if (result == 1)
            return jdbcTemplate.update(SQL_INSERT_CUSTOMER, customer.getUserId(), customer.getCountryId(),
                    customer.getCityId(), customer.getGender(), customer.getBirthdate(), customer.getCountryCodeId());
        else
            return 0;
    }

//    public int updateCustomer(Customer customer) {
//        int result = this.update(customer.getFirstName() + " " + customer.getLastName(), customer.getFirstName(), customer.getLastName(),
//                customer.getPhone(), customer.getEmail().toLowerCase(), customer.getEnabled(), customer.getPersonalPhoto(),
//                customer.getEntityStatus().getValue(), customer.getDescription(), customer.getVersion(),
//                customer.getUserId());
//        if (result == 1)
//            return jdbcTemplate.update(SQL_UPDATE_CUSTOMER, customer.getCountryId(), customer.getCityId(),
//                    customer.getGender(), customer.getBirthdate(), customer.getUserId());
//        else
//            return 0;
//    }

    public Long getAuthonticatedUserId(String principal) {
        return jdbcTemplate.queryForObject(FIND_USER_ID, Long.class, principal, principal);
    }
}
