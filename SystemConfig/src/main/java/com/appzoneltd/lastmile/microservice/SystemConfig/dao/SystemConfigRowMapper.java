package com.appzoneltd.lastmile.microservice.SystemConfig.dao;

import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemConfigRowMapper implements RowMapper<SystemConfig> {

    @Override
    public SystemConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        SystemConfig systemConfig = new SystemConfig();

        systemConfig.setConfigId(rs.getLong("config_id"));
        systemConfig.setDescription(rs.getString("description"));
        systemConfig.setDisplayname(rs.getString("displayname"));
        systemConfig.setStatus(EntityStatus.valueOf(rs.getString("status")));
        systemConfig.setUnit(rs.getString("unit"));
        systemConfig.setValue(rs.getBigDecimal("value"));
        systemConfig.setTextValue(rs.getString("text_value"));
        systemConfig.setVersion(rs.getLong("version"));
        systemConfig.setCreated(rs.getTimestamp("created"));

        return systemConfig;
    }
}
