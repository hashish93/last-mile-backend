package com.appzoneltd.lastmile.microservice.SystemConfig.service;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.SystemConfig;
import com.appzoneltd.lastmile.microservice.SystemConfig.dao.SystemConfigRepositoryImp;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SystemConfigServiceImp implements SystemConfigService {

    private final SystemConfigRepositoryImp systemConfigRepositoryImp;
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SystemConfigServiceImp(SystemConfigRepositoryImp systemConfigRepositoryImp, KafkaTemplate<Integer, String> kafkaTemplate) {
        this.systemConfigRepositoryImp = systemConfigRepositoryImp;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public int updateSystemConfig(SystemConfig systemConfig) throws EntityNotFoundException, JsonProcessingException {
        int result = 0;
        if (!systemConfigRepositoryImp.isAlreadyExists("lastmile_core.system_config", "config_id",
                systemConfig.getConfigId()))
            throw new EntityNotFoundException("systemConfig.key.notExist");

        result = systemConfigRepositoryImp.update(systemConfig.getValue(), systemConfig.getTextValue(), systemConfig.getVersion(),
                systemConfig.getConfigId());

        if (result == 0)
            throw new EntityNotFoundException("systemConfig.key.notExist");
        else {
            if (systemConfig.getConfigId().equals(15L) || (new Long(14).equals(systemConfig.getConfigId()))) {
                System.out.println(systemConfig.toString());
                startAutomaticProducer(systemConfig);
            }
        }
        return result;

    }


    private void startAutomaticProducer(SystemConfig config) throws JsonProcessingException {
        kafkaTemplate.send("AUTOMATIC_CRON", objectMapper.writeValueAsString(config));
    }

    @Override
    public List<SystemConfig> findallSystemConfig() {
        List<SystemConfig> systemConfigs = systemConfigRepositoryImp.query();
        Collections.sort(systemConfigs, new Comparator<SystemConfig>() {
            @Override
            public int compare(SystemConfig o1, SystemConfig o2) {
                return o1.getDisplayname().compareTo(o1.getDisplayname());
            }
        });
        return systemConfigs;
    }

    @Override
    public SystemConfig findConfigById(Long systemConfigId) {
        SystemConfig systemConfig = systemConfigRepositoryImp.queryforObject(systemConfigId);
        return systemConfig;
    }

    @Override
    public int countAllSystemConfig() {
        return systemConfigRepositoryImp.count();
    }
}
