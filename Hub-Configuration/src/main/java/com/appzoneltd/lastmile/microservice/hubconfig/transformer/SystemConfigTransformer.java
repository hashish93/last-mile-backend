package com.appzoneltd.lastmile.microservice.hubconfig.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.hubconfig.dto.HubCofigurationDto;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.SystemConfigEntity;

public class SystemConfigTransformer {

	public static List<HubCofigurationDto> getConfigurationDtosFromSystemConfigEntities(
			List<SystemConfigEntity> systemConfigEntities) {
		List<HubCofigurationDto> hubCofigurationDtos = new ArrayList<>();
		if (systemConfigEntities != null) {
			for (SystemConfigEntity sytemConfigEntity : systemConfigEntities) {
				hubCofigurationDtos.add(getConfigurationDtoFromSystemConfigEntity(sytemConfigEntity));
			}
		}
		return hubCofigurationDtos;
	}

	public static HubCofigurationDto getConfigurationDtoFromSystemConfigEntity(SystemConfigEntity sytemConfigEntity) {		
		if(sytemConfigEntity==null){
			return null;
		}
		HubCofigurationDto hubCofigurationDto = new HubCofigurationDto();
		hubCofigurationDto.setValue(sytemConfigEntity.getValue());
		hubCofigurationDto.setTextValue(sytemConfigEntity.getTextValue());
		hubCofigurationDto.setConfigId(sytemConfigEntity.getConfigId());
		hubCofigurationDto.setDisplayname(sytemConfigEntity.getDisplayname());
		hubCofigurationDto.setDescription(sytemConfigEntity.getDescription());
		hubCofigurationDto.setStatus(sytemConfigEntity.getStatus());
		hubCofigurationDto.setUnit(sytemConfigEntity.getUnit());
		hubCofigurationDto.setCreated(sytemConfigEntity.getCreated());
		hubCofigurationDto.setVersion(sytemConfigEntity.getVersion());
		hubCofigurationDto.setConfigType(sytemConfigEntity.getConfigType());
		return hubCofigurationDto;
	}

}
