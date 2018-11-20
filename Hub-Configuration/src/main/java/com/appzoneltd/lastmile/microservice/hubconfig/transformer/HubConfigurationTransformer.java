package com.appzoneltd.lastmile.microservice.hubconfig.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.hubconfig.dto.HubCofigurationDto;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.HubConfigurationEntity;

public class HubConfigurationTransformer {

	public static List<HubCofigurationDto> getHubConfigurationDtosFromEntities(
			List<HubConfigurationEntity> hubConfigurationEntities) {
		List<HubCofigurationDto> hubCofigurationDtos = new ArrayList<>();
		if (hubConfigurationEntities != null) {
			for (HubConfigurationEntity hubConfigurationEntity : hubConfigurationEntities) {
				hubCofigurationDtos.add(getHubConfigurationDtoFromEntity(hubConfigurationEntity));
			}
		}
		return hubCofigurationDtos;
	}

	public static HubCofigurationDto getHubConfigurationDtoFromEntity(HubConfigurationEntity hubConfigurationEntity) {

		if (hubConfigurationEntity == null) {
			return null;
		}

		HubCofigurationDto hubCofigurationDto = new HubCofigurationDto();
		hubCofigurationDto.setValue(hubConfigurationEntity.getValue());
		hubCofigurationDto.setTextValue(hubConfigurationEntity.getTextValue());
		if (hubConfigurationEntity.getSystemConfig() != null) {
			hubCofigurationDto.setConfigId(hubConfigurationEntity.getSystemConfig().getConfigId());
			hubCofigurationDto.setDisplayname(hubConfigurationEntity.getSystemConfig().getDisplayname());
			hubCofigurationDto.setDescription(hubConfigurationEntity.getSystemConfig().getDescription());
			hubCofigurationDto.setStatus(hubConfigurationEntity.getSystemConfig().getStatus());
			hubCofigurationDto.setUnit(hubConfigurationEntity.getSystemConfig().getUnit());
			hubCofigurationDto.setCreated(hubConfigurationEntity.getSystemConfig().getCreated());
			hubCofigurationDto.setVersion(hubConfigurationEntity.getSystemConfig().getVersion());
			hubCofigurationDto.setConfigType(hubConfigurationEntity.getSystemConfig().getConfigType());
			if(hubConfigurationEntity.getBuilding()!=null){
				hubCofigurationDto.setHubId(hubConfigurationEntity.getBuilding().getBuildingId());
			}
		}
		return hubCofigurationDto;
	}
}
