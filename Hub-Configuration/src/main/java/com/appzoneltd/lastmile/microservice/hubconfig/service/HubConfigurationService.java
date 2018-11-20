package com.appzoneltd.lastmile.microservice.hubconfig.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.hubconfig.dao.HubConfigurationDao;
import com.appzoneltd.lastmile.microservice.hubconfig.dao.SystemConfigDao;
import com.appzoneltd.lastmile.microservice.hubconfig.dto.HubCofigurationDto;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.HubConfigurationEntity;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.SystemConfigEntity;
import com.appzoneltd.lastmile.microservice.hubconfig.transformer.HubConfigurationTransformer;
import com.appzoneltd.lastmile.microservice.hubconfig.transformer.SystemConfigTransformer;

@Service
public class HubConfigurationService {

	@Autowired
	private HubConfigurationDao hubConfigurationDao;

	@Autowired
	private SystemConfigDao systemConfigDao;

	@Autowired
	private PrincipalService principalService;

	public boolean updateHubConfiguration(HubCofigurationDto hubCofigurationDto, Principal principal) {
		if (hubCofigurationDto != null) {
			if (hubCofigurationDto.getConfigId() != null) {
				if (principalService.isSuperAdmin(principal)) {
					if (hubCofigurationDto.getHubId() != null) {
						return updateHubSystemConfiuration(hubCofigurationDto);
					} else {
						return updateSharedSystemConfiguration(hubCofigurationDto);
					}
				} else {
					return updateHubSystemConfiuration(hubCofigurationDto);
				}
			}
		}
		return false;
	}

	@Transactional
	public boolean updateSharedSystemConfiguration(HubCofigurationDto hubCofigurationDto) {
		SystemConfigEntity systemConfigEntity = systemConfigDao
				.findSharedConfiugrationwithConfig(hubCofigurationDto.getConfigId());
		if (systemConfigEntity != null) {
			systemConfigEntity.setValue(hubCofigurationDto.getValue());
			systemConfigEntity.setTextValue(hubCofigurationDto.getTextValue());
			systemConfigDao.save(systemConfigEntity);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean updateHubSystemConfiuration(HubCofigurationDto hubCofigurationDto) {
		List<HubConfigurationEntity> hubConfigurationEntities = hubConfigurationDao
				.findSystemConfigurationForHub(hubCofigurationDto.getHubId(), hubCofigurationDto.getConfigId());
		if (hubConfigurationEntities != null && hubConfigurationEntities.size() > 0) {
			HubConfigurationEntity hubConfigurationEntity = hubConfigurationEntities.get(0);
			hubConfigurationEntity.setValue(hubCofigurationDto.getValue());
			hubConfigurationEntity.setTextValue(hubCofigurationDto.getTextValue());
			hubConfigurationDao.save(hubConfigurationEntity);
			return true;
		}
		return false;
	}

	public SystemConfigEntity getSharedConfigurationById(Long configId){
		return systemConfigDao.findSharedConfiugrationwithConfig(configId);
	}
	
	public List<SystemConfigEntity> getAllSharedConfigurations() {
		return systemConfigDao.findAllSharedConfigurations();
	}
	
	public HubConfigurationEntity getHubConfigurationById(Long hubId,Long configId){
		List<HubConfigurationEntity> hubConfigurationEntities = hubConfigurationDao.findSystemConfigurationForHub(hubId, configId);
		if (hubConfigurationEntities != null && hubConfigurationEntities.size() > 0) {
			return hubConfigurationEntities.get(0);
		}		
		return null;
	}

	public List<HubCofigurationDto> getAllConfigurationEntities(Long hubId, Principal principal,String configType) {
		List<HubCofigurationDto> hubCofigurationDtos = new ArrayList<>();

		if (principalService.isSuperAdmin(principal)) {
			if (hubId != null) {
				hubCofigurationDtos.addAll(getConfigurationsForHubId(hubId));
			}else{
				List<SystemConfigEntity> systemConfigEntities= systemConfigDao.findAllSharedConfigurations();
				List<HubCofigurationDto> cofigurationDtos=SystemConfigTransformer.getConfigurationDtosFromSystemConfigEntities(systemConfigEntities);
				hubCofigurationDtos.addAll(cofigurationDtos);
			}			
		} else {
			hubId = principalService.getHubIdIfFound(principal);
			if (hubId != null) {
				hubCofigurationDtos.addAll(getConfigurationsForHubId(hubId));
			}
		}
		if(configType !=null && !hubCofigurationDtos.isEmpty()){
			hubCofigurationDtos=getConfigByConfigType(hubCofigurationDtos,configType);
		}
		return hubCofigurationDtos;
	}

	private List<HubCofigurationDto> getConfigByConfigType(List<HubCofigurationDto> cofigurationDtos,String configType){
		List<HubCofigurationDto> filteredCofigurationDtos = new ArrayList<>();
		for(HubCofigurationDto hubCofigurationDto : cofigurationDtos){
			if(configType.equalsIgnoreCase(hubCofigurationDto.getConfigType())){
				filteredCofigurationDtos.add(hubCofigurationDto);
			}
		}
		return filteredCofigurationDtos;
	}
	public int countAllConfiguration(Long hubId, Principal principal,String configType) {
		List<HubCofigurationDto> hubCofigurationDtos = getAllConfigurationEntities(hubId, principal,configType);
		return hubCofigurationDtos.size();
	}

	public List<HubCofigurationDto> getConfigurationsForHubId(Long hubId) {
		List<HubCofigurationDto> hubCofigurationDtos = new ArrayList<>();

		List<SystemConfigEntity> systemConfigEntities = getAllSharedConfigurations();
		List<HubConfigurationEntity> hubConfigurationEntities = hubConfigurationDao.findAllConfigurationForHub(hubId);

		hubCofigurationDtos
				.addAll(HubConfigurationTransformer.getHubConfigurationDtosFromEntities(hubConfigurationEntities));
		hubCofigurationDtos
				.addAll(SystemConfigTransformer.getConfigurationDtosFromSystemConfigEntities(systemConfigEntities));
		return hubCofigurationDtos;
	}

	public HubCofigurationDto findConfigurationById(Long hubId,Long configId , Principal principal) {
			if (configId != null) {
				if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
					if (hubId == null) {
						SystemConfigEntity systemConfigEntity=getSharedConfigurationById(configId);
						return SystemConfigTransformer.getConfigurationDtoFromSystemConfigEntity(systemConfigEntity);
					} else {
						HubConfigurationEntity hubConfigurationEntity=getHubConfigurationById(hubId, configId);
						return HubConfigurationTransformer.getHubConfigurationDtoFromEntity(hubConfigurationEntity);
					}
				} else {
					if(!principalService.isCustomer(principal)) {
						hubId = principalService.getHubIdIfFound(principal);
					}					
					HubConfigurationEntity hubConfigurationEntity=getHubConfigurationById(hubId, configId);
					return HubConfigurationTransformer.getHubConfigurationDtoFromEntity(hubConfigurationEntity);
				}
			}
		return null;
	}
	
	
}
