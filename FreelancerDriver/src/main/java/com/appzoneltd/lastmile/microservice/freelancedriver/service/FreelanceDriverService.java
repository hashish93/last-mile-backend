package com.appzoneltd.lastmile.microservice.freelancedriver.service;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.FreelanceFilterParameter;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.FreelancerDriverDto;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.FreelancerDriverListInfoDto;
import com.appzoneltd.lastmile.microservice.freelancedriver.model.FreelancerDriverstatus;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface FreelanceDriverService {

    List<FreelancerDriverListInfoDto> getAllFreelancerDriver(int page, int maxResult, OrderBy orderBy);

    long countAllfreeLancerDriver();

    FreelancerDriverDto findFreelancerById(Long id) throws EntityNotFoundException;

    Object acceptFreelancerDriverInfo(FreelancerDriverDto freelancerDriverDto) throws EntityNotFoundException;

    void rejectFreelancerDriverInfo(FreelancerDriverDto freelancerDriverDto) throws EntityNotFoundException;

    void sendEmailWithMissingdoucments(FreelancerDriverDto freelancerDriverDto) throws JsonProcessingException;

    List<FreelancerDriverstatus> getFreelancerStatus();

    List<FreelancerDriverListInfoDto> filter(FreelanceFilterParameter endPointParameter);
}
