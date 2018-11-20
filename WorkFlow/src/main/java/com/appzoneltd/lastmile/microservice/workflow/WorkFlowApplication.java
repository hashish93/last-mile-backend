package com.appzoneltd.lastmile.microservice.workflow;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
@PropertySources({ @PropertySource("classpath:application.yml"),
	@PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true) })
@EnableKafka
public class WorkFlowApplication {

    @Value("${spring.datasource.url}")
	private String DATABASE_URL;

    @Value("${spring.datasource.username}")
    private String USER;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(WorkFlowApplication.class, args);
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.appzoneltd.lastmile.microservice.workflow");
		factory.setPersistenceUnitName("persistenceUnit");
		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.connection.url", DATABASE_URL);
		properties.setProperty("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		properties.setProperty("hibernate.connection.username", USER);
		properties.setProperty("hibernate.connection.password", PASSWORD);
		factory.setJpaProperties(properties);
		factory.afterPropertiesSet();
		return factory.getObject();
	}


	 @Bean public PlatformTransactionManager transactionManager() {
	 JpaTransactionManager txManager = new JpaTransactionManager();
	  txManager.setEntityManagerFactory(entityManagerFactory()); return
	  txManager; }
	 

	// @Bean
	// InitializingBean usersAndGroupsInitializer(final IdentityService
	// identityService) {
	//
	// return new InitializingBean() {
	// public void afterPropertiesSet() throws Exception {
	//
	// Group group = identityService.newGroup("user");
	// group.setName("users");
	// group.setType("security-role");
	// identityService.saveGroup(group);
	//
	// User admin = identityService.newUser("admin");
	// admin.setPassword("admin");
	// identityService.saveUser(admin);
	//
	// }
	// };
	// }
	//
}
