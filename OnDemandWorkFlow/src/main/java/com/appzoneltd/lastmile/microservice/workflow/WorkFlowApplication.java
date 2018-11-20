package com.appzoneltd.lastmile.microservice.workflow;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.appzoneltd.lastmile.microservice.workflow.listener.TriggerListener;
import com.appzoneltd.lastmile.microservice.workflow.listener.TriggerNotifier;

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
	
	public static void main(String[] args) throws InterruptedException, SQLException {
		SpringApplication.run(WorkFlowApplication.class, args);
		//initTrigger();
		DataSource dataSource=new DriverManagerDataSource("jdbc:postgresql://localhost:5432/lastmile_workflow", "postgres", "root");
		System.out.println(dataSource.getConnection().toString());
		/// Listener 
		TriggerListener listener=new TriggerListener(dataSource.getConnection());
		listener.setPriority(Thread.MAX_PRIORITY);
		listener.setDaemon(false);
		
		TriggerNotifier notifier=new TriggerNotifier(dataSource.getConnection());
		notifier.setPriority(Thread.MAX_PRIORITY);
		notifier.setDaemon(false);
		
		
		// Starting Listeneres
		listener.start();
		notifier.start();
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.appzoneltd.lastmile.microservice.workflow");
		factory.setPersistenceUnitName("persistenceUnit");
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.connection.url", DATABASE_URL);
		properties.setProperty("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		properties.setProperty("hibernate.connection.username", USER);
		properties.setProperty("hibernate.connection.password", PASSWORD);
		factory.setJpaProperties(properties);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}


}
