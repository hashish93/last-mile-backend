package com.appzoneltd.lastmile.microservice.lookup.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.appzoneltd.lastmile.microservice.lookup.dto.CityDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.CountryCodesDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.CountryDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.CityEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.CountryCodesEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.CountryEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.CoveredCitiesEntity;

public class CountryCityTransformer {
	
	public static List<CountryDto> getCountryDtoFromEntities(List<CountryEntity> countryEntities, Locale locale) {
		if (countryEntities == null) {
			return new ArrayList<>();
		}
		List<CountryDto> countryDtos=new ArrayList<>();		
		for (CountryEntity countryEntity : countryEntities) {
			countryDtos.add(getCountryDtoFromEntity(countryEntity, locale));
		}
		return countryDtos;
	}

	public static CountryDto getCountryDtoFromEntity(CountryEntity countryEntity, Locale locale) {
		CountryDto countryDto = new CountryDto();
		countryDto.setCountryId(countryEntity.getCountryId());
		if (locale.getLanguage().equalsIgnoreCase("ar")) {
			countryDto.setName(countryEntity.getNameAr());
		} else {
			countryDto.setName(countryEntity.getNameEn());
		}
		return countryDto;
	}

	public static List<CityDto> getCityDtoFromEntities(List<CityEntity> cityEntities, Locale locale) {
		if (cityEntities == null) {
			return new ArrayList<>();
		}
		List<CityDto> cityDtos=new ArrayList<>();		
		for (CityEntity cityEntity : cityEntities) {
			cityDtos.add(getCityDtoFromEntity(cityEntity, locale));
		}
		return cityDtos;
	}

	public static CityDto getCityDtoFromEntity(CityEntity cityEntity, Locale locale) {
		CityDto cityDto = new CityDto();
		cityDto.setCityId(cityEntity.getCityId());
		if(cityEntity.getCountry() !=null){
			cityDto.setCountryId(cityEntity.getCountry().getCountryId());
		}
		if (locale.getLanguage().equalsIgnoreCase("ar")) {
			cityDto.setName(cityEntity.getNameAr());
		} else {
			cityDto.setName(cityEntity.getNameEn());
		}
		return cityDto;
	}
	
	public static List<CityDto> getCityDtoFromCoverCityEntities(List<CoveredCitiesEntity> coveredCitiesEntities, Locale locale) {
		if (coveredCitiesEntities == null) {
			return new ArrayList<>();
		}
		List<CityDto> cityDtos=new ArrayList<>();		
		for (CoveredCitiesEntity coveredCitiesEntity : coveredCitiesEntities) {
			cityDtos.add(getCityDtoFromCoverCityEntity(coveredCitiesEntity, locale));
		}
		return cityDtos;
	}

	public static CityDto getCityDtoFromCoverCityEntity(CoveredCitiesEntity coveredCitiesEntity, Locale locale) {
		CityDto cityDto = new CityDto();
		if(coveredCitiesEntity.getCityEntity() !=null){
			cityDto.setCityId(coveredCitiesEntity.getCityEntity().getCityId());
			if(coveredCitiesEntity.getCityEntity().getCountry() !=null){
				cityDto.setCountryId(coveredCitiesEntity.getCityEntity().getCountry().getCountryId());
			}
			if (locale.getLanguage().equalsIgnoreCase("ar")) {
				cityDto.setName(coveredCitiesEntity.getCityEntity().getNameAr());
			} else {
				cityDto.setName(coveredCitiesEntity.getCityEntity().getNameEn());
			}
		}
		return cityDto;
	}
	
	
	public static List<CountryCodesDto> getCountryCodeDtoFromEntities(List<CountryCodesEntity> countryCodesEntities) {
		if (countryCodesEntities == null) {
			return new ArrayList<>();
		}
		List<CountryCodesDto> countryCodesDtos=new ArrayList<>();		
		for (CountryCodesEntity countryCodesEntity : countryCodesEntities) {
			countryCodesDtos.add(getCountryCodeDtoFromEntity(countryCodesEntity));
		}
		return countryCodesDtos;
	}

	public static CountryCodesDto getCountryCodeDtoFromEntity(CountryCodesEntity countryCodesEntity) {
		CountryCodesDto countryCodesDto = new CountryCodesDto();
		countryCodesDto.setId(countryCodesEntity.getId());
		countryCodesDto.setName(countryCodesEntity.getIso3());
		countryCodesDto.setCode(countryCodesEntity.getPhonecode().toString());
		return countryCodesDto;
	}
	
}
