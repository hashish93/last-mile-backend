package com.appzoneltd.lastmile.microservice.customer.dto;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.UsersRepository;
import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by alaa.nabil on 3/20/2017.
 */
@Component
@Scope("singleton")
public class DtoMapper {
    private final UsersRepository usersRepository;

    @Autowired
    public DtoMapper(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public CustomerInfo mapToCustomer(CustomerEntity customerEntity) {
        if (customerEntity == null)
            return null;
        UsersEntity user = usersRepository.findOne(customerEntity.getCustomerId());
        CustomerInfo customerInfo = new CustomerInfo();

        customerInfo.setUserId(customerEntity.getCustomerId());
        customerInfo.setUsername(user.getUsername());
        customerInfo.setFirstName(user.getFirstname());
        customerInfo.setLastName(user.getLastname());
        customerInfo.setBirthdate(customerEntity.getBirthdate());
        customerInfo.setGender(customerEntity.getGender());
//		customerInfo.setPassword(rs.getString("password"));
        customerInfo.setPhone(user.getPhone());
        customerInfo.setEmail(user.getEmail());
        customerInfo.setEnabled(user.getEnabled());
        customerInfo.setCreated(user.getCreated());
        customerInfo.setPersonalPhoto(user.getStaticContent() == null ? null : user.getStaticContent().getContentId());
        customerInfo.setEntityStatus(EntityStatus.valueOf(user.getStatus()));
        customerInfo.setDescription(user.getDescription());
        if(user.getUserTypeEntity() !=null){
            customerInfo.setUserType(UserType.valueOf(user.getUserTypeEntity().getName()));
        }
        customerInfo.setVersion(user.getVersion());
        customerInfo.setCountryId(customerEntity.getCountry() != null ? customerEntity.getCountry().getCountryId() : null);
        customerInfo.setCityId(customerEntity.getCity() != null ? customerEntity.getCity().getCityId() : null);
        customerInfo.setCountryNameAr(customerEntity.getCountry() == null ? null : customerEntity.getCountry().getNameAr());
        customerInfo.setCountryNameEn(customerEntity.getCountry() == null ? null : customerEntity.getCountry().getNameEn());
        customerInfo.setCityNameAr(customerEntity.getCity() == null ? null : customerEntity.getCity().getNameAr());
        customerInfo.setCityNameEn(customerEntity.getCity() == null ? null : customerEntity.getCity().getNameEn());
        customerInfo.setVerified(customerEntity.getCustomerVerification() == null ? null : customerEntity.getCustomerVerification().getVerified());
        customerInfo.setCountryCodeId(customerEntity.getCountryCodes() != null ? customerEntity.getCountryCodes().getId() : null);
        customerInfo.setCountryCode(customerEntity.getCountryCodes() != null ? customerEntity.getCountryCodes().getPhonecode() : null);
        return customerInfo;
    }
}
