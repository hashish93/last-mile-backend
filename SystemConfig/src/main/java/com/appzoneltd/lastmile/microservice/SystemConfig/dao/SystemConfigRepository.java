package com.appzoneltd.lastmile.microservice.SystemConfig.dao;

public interface SystemConfigRepository {

	String CREATE_SYSTEM_CONFIG_QUERY = "INSERT INTO lastmile_core.system_config(config_id, value, text_value, displayname, description, unit) VALUES (?, ?, ?, ?, ?, ?)";

	String UPDATE_SYSTEM_CONFIG_QUERY = "UPDATE lastmile_core.system_config SET value=?, text_value=?, version=version+1 WHERE version=? AND config_id=?";

	String FIND_ALL_SYSTEM_CONFIG_QUERY = "SELECT config_id, value, text_value, displayname, description, status, created, version, unit FROM lastmile_core.system_config WHERE status='ACTIVE' ";

	String FIND_SYSTEM_CONFIG_BY_ID = "SELECT config_id, value, text_value, displayname, description, status, created, version, unit FROM lastmile_core.system_config WHERE status='ACTIVE' AND config_id=?";

	String COUNT_ALL_QUERY = "SELECT COUNT(config_id) FROM lastmile_core.system_config WHERE status='ACTIVE' ";

}
