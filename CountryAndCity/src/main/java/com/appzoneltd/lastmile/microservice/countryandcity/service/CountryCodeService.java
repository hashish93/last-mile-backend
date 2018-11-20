package com.appzoneltd.lastmile.microservice.countryandcity.service;

import com.appzoneltd.lastmile.microservice.countryandcity.dao.CountryCodesEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.countryandcity.dto.CountryCode;
import com.appzoneltd.lastmile.microservice.countryandcity.dto.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 3/8/2017.
 */
@Service
public class CountryCodeService {
    private final CountryCodesRepository countryCodesRepository;


    @Autowired
    public CountryCodeService(CountryCodesRepository countryCodesRepository) {
        this.countryCodesRepository = countryCodesRepository;
    }

    public List<CountryCode> getCountryCodes() {
        return ((List<CountryCodesEntity>) countryCodesRepository.findAllByOrderByNicenameAsc()).stream()
                .map(Mapper::CountryCodeToDto).collect(Collectors.toList());
    }
}
