package com.appzoneltd.lastmile.microservices.syncengine.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "reportsEntityManagerFactory", transactionManagerRef = "reportsTransactionManager", basePackages = {
		"com.appzoneltd.lastmile.microservices.syncengine.stats.dao" })
public class LastmileReportDbConfig {

	@Bean(name = "reportsDataSource")
	@ConfigurationProperties(prefix = "datasource.db_lastmile_reporting")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "reportsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("reportsDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.appzoneltd.lastmile.microservices.syncengine.stats.entity")
				.persistenceUnit("reports").build();
	}

	@Bean(name = "reportsTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("reportsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
