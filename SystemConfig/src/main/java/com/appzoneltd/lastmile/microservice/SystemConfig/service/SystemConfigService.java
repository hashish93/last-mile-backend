package com.appzoneltd.lastmile.microservice.SystemConfig.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.SystemConfig;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SystemConfigService {
	int updateSystemConfig(SystemConfig systemConfig) throws EntityNotFoundException, JsonProcessingException;

	List<SystemConfig> findallSystemConfig();

	SystemConfig findConfigById(Long systemConfigId);

	int countAllSystemConfig();

}
