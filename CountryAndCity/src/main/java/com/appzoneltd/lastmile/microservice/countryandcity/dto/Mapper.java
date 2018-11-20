package com.appzoneltd.lastmile.microservice.countryandcity.dto;

import com.appzoneltd.lastmile.microservice.countryandcity.dao.CityEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CountryCodesEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CountryEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CoveredCityEntity;

import java.util.*;

public class Mapper {

    public static City cityMapper(CityEntity cityEntity, Locale locale) {
        City city = null;

        if (locale.getLanguage().equalsIgnoreCase("ar"))
            city = new City(cityEntity.getCityId(), cityEntity.getNameAr(), cityEntity.getCountryId());
        else
            city = new City(cityEntity.getCityId(), cityEntity.getNameEn(), cityEntity.getCountryId());
        return city;
    }

    public static List<City> cityMapper(List<CityEntity> cityEntities, Locale locale) {
        List<City> cities = new ArrayList<City>();
        City city = null;
        for (CityEntity cityEntity : cityEntities) {
            if (locale.getLanguage().equalsIgnoreCase("ar"))
                city = new City(cityEntity.getCityId(), cityEntity.getNameAr(), cityEntity.getCountryId());
            else
                city = new City(cityEntity.getCityId(), cityEntity.getNameEn(), cityEntity.getCountryId());
            cities.add(city);
        }

        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return cities;
    }
    
    public static List<City> cityMapperFromCoveredCities(List<CoveredCityEntity> cityEntities, Locale locale) {
        List<City> cities = new ArrayList<City>();
        City city = null;
        
        for (CoveredCityEntity cityEntity : cityEntities) {
        	
            if (locale.getLanguage().equalsIgnoreCase("ar")) {
                city = new City(cityEntity.getCityId(), cityEntity.getNameAr(), cityEntity.getCountryId());
            }
            else {
                city = new City(cityEntity.getCityId(), cityEntity.getNameEn(), cityEntity.getCountryId());
            }
            
            cities.add(city);
        }

        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return cities;
    }

    public static Country countryMapper(CountryEntity countryEntity, Locale locale) {
        Country country = null;
        if (locale.getLanguage().equalsIgnoreCase("ar"))
            country = new Country(countryEntity.getCountryId(), countryEntity.getNameAr());
        else
            country = new Country(countryEntity.getCountryId(), countryEntity.getNameEn());
        return country;
    }

    public static List<Country> countryMapper(List<CountryEntity> countryEntities, Locale locale) {
        List<Country> countries = new ArrayList<Country>();
        Country country = null;
        for (CountryEntity countryEntity : countryEntities) {
            if (locale.getLanguage().equalsIgnoreCase("ar"))
                country = new Country(countryEntity.getCountryId(), countryEntity.getNameAr());
            else
                country = new Country(countryEntity.getCountryId(), countryEntity.getNameEn());
            countries.add(country);
        }

        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return countries;
    }

    public static CountryCode CountryCodeToDto(CountryCodesEntity countryCodesEntity) {
        return new CountryCode(countryCodesEntity.getId(), countryCodesEntity.getNicename(), 
        		countryCodesEntity.getPhonecode().toString() ,
        		countryCodesEntity.getIso());
    }

}
