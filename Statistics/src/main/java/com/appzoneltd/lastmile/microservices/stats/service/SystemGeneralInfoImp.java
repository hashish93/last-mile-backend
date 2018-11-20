package com.appzoneltd.lastmile.microservices.stats.service;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.CustomerGender;
import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.FreelancerStatus;
import com.appzoneltd.lastmile.microservices.stats.lastmile.repo.CustomerRepository;
import com.appzoneltd.lastmile.microservices.stats.lastmile.repo.DriverRepository;
import com.appzoneltd.lastmile.microservices.stats.lastmile.repo.FreelancerDriverJpaRepository;
import com.appzoneltd.lastmile.microservices.stats.lastmile.repo.PackageRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemGeneralInfoImp implements SystemGeneralInfo {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private FreelancerDriverJpaRepository freelancerDriverJpaRepository;

    @Override
    public List<StatsResponse> getSystemGeneralInfo() {
        List<StatsResponse> result = new ArrayList<>();

        result.add(getPackagesCount());
        result.add(getCorporateDriversCount());
        result.add(getAcceptedFreelancerDriversCount());
        result.add(getMaleCustomersCount());
        result.add(getFemaleCustomersCount());
        result.add(getAllCustomersCount());

        return result;
    }

    private StatsResponse getPackagesCount() {
        Long packagesCount = packageRepository.count();
        return new StatsResponse("PACKAGES", packagesCount);

    }

    private StatsResponse getCorporateDriversCount() {
        Long corporateDriversCount = driverRepository.count();
        return new StatsResponse("CORPORATEDRIVERS", corporateDriversCount);

    }

    private StatsResponse getAcceptedFreelancerDriversCount() {
        Long freelancerDriversCount = freelancerDriverJpaRepository.countAllByStatus(FreelancerStatus.ACCEPTED.name());
        return new StatsResponse("FREELANCERDRIVERS", freelancerDriversCount);

    }

    private StatsResponse getMaleCustomersCount() {
        Long maleCustomers = customerRepository.getMaleCustomers(CustomerGender.Male.name());
        return new StatsResponse("MALECUSTOMERS", maleCustomers);

    }

    private StatsResponse getFemaleCustomersCount() {
        Long femaleCustomers = customerRepository.getMaleCustomers(CustomerGender.Female.name());
        return new StatsResponse("FEMALECUSTOMERS", femaleCustomers);

    }

    private StatsResponse getAllCustomersCount() {

        Long allCustomers = customerRepository.count();
        return new StatsResponse("ALLCUSTOMER", allCustomers);

    }

}
