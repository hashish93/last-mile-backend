package com.appzoneltd.lastmile.microservice.deliveryworkflow.config;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration(
			@Qualifier("worflowDataSource") DataSource workflowDataSource,
			PlatformTransactionManager transactionManager) {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setDataSource(workflowDataSource);
		processEngineConfiguration.setTransactionManager(transactionManager);
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		processEngineConfiguration.setJobExecutorActivate(true);
		processEngineConfiguration.setDatabaseType("postgres");
		processEngineConfiguration.setAsyncExecutorEnabled(true);
		processEngineConfiguration.setAsyncExecutorActivate(true);
		processEngineConfiguration.setJpaHandleTransaction(true);
		processEngineConfiguration.setJpaCloseEntityManager(true);
		Resource[] resources = new Resource[1];
		resources[0] = new ClassPathResource("/processes/DeliveryWorkflow.bpmn");
		processEngineConfiguration.setDeploymentResources(resources);
		processEngineConfiguration.setProcessEngineName("LastMile_WF_Delivery_Engine");
		return processEngineConfiguration;
	}

	@Bean
	public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
		ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
		return processEngineFactoryBean;
	}

	@Bean
	public RepositoryService repositoryService(ProcessEngine processEngine) throws Exception {
		return processEngine.getRepositoryService();
	}

	@Bean
	public RuntimeService runtimeService(ProcessEngine processEngine) throws Exception {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		return runtimeService;
	}

	@Bean
	public TaskService taskService(ProcessEngine processEngine) throws Exception {
		return processEngine.getTaskService();
	}

	@Bean
	public HistoryService historyService(ProcessEngine processEngine) throws Exception {
		return processEngine.getHistoryService();
	}

	@Bean
	public ManagementService managementService(ProcessEngine processEngine) throws Exception {
		return processEngine.getManagementService();
	}

	@Bean
	public FormService formService(ProcessEngine processEngine) throws Exception {
		return processEngine.getFormService();
	}

	@Bean
	public IdentityService identityService(ProcessEngine processEngine) throws Exception {
		return processEngine.getIdentityService();
	}
}
