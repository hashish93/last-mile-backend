package com.appzoneltd.lastmile.microservice.ondemandworkflow.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.db_lastmile")
	public DataSource lastMileDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "datasource.db_workflow")
	public DataSource worflowDataSource() {
		return DataSourceBuilder.create().build();
	}

	
}
