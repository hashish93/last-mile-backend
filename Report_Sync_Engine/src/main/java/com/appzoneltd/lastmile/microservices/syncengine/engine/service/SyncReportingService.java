package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.ModuleExecutionModel;
import com.appzoneltd.lastmile.microservices.syncengine.engine.scheduler.ModuleSchedular;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class SyncReportingService {

	@Autowired
	private SyncEngineDao syncEngineDao;

	@Autowired
	private ModuleSchedular moduleSchedular;

	@PostConstruct
	public void Construct() throws SchedulerException {
		startEngine();
	}

	public void startEngine() throws SchedulerException {

		List<SyncEngineEntity> syncEngineEntities = (List<SyncEngineEntity>) syncEngineDao.findAll();

		for (SyncEngineEntity syncEngineEntity : syncEngineEntities) {
			if (!syncEngineEntity.isDeleted()) {
				ModuleExecutionModel moduleExecution = new ModuleExecutionModel(syncEngineEntity.getId(),
						syncEngineEntity.getModuleName(), syncEngineEntity.getTimeInterval(),syncEngineEntity.getLastSyncTime());
				moduleSchedular.startJob(moduleExecution);
			}
		}
	}

	public void stopEngine() throws SchedulerException {

		List<SyncEngineEntity> syncEngineEntities = (List<SyncEngineEntity>) syncEngineDao.findAll();

		for (SyncEngineEntity syncEngineEntity : syncEngineEntities) {
			if (!syncEngineEntity.isDeleted()) {	
				ModuleExecutionModel moduleExecution = new ModuleExecutionModel(syncEngineEntity.getId(),
						syncEngineEntity.getModuleName(), syncEngineEntity.getTimeInterval(),syncEngineEntity.getLastSyncTime());
				moduleSchedular.stopJob(moduleExecution);
			}
		}
	}

	
	public void restartEngine() throws SchedulerException{
		stopEngine();
		startEngine();
	}
}
