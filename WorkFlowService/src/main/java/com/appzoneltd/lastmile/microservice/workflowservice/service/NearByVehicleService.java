package com.appzoneltd.lastmile.microservice.workflowservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.appzoneltd.lastmile.microservice.workflowservice.dao.NearByVehicleDao;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflowservice.model.NearByVehicleModel;

@Service
public class NearByVehicleService {

	@Autowired
	private NearByVehicleDao nearByVehicleDao;
	
	public void saveNearByVehicles(WorkflowNearByVehicles workflowNearByVehicles){
		// Deleting Old Vehicles If Found 
	    List<NearByVehicleModel> nearByVehicleModels=nearByVehicleDao.findAllNearVehiclesForPackageId(workflowNearByVehicles.getPackageId());
	    // Check if Data is Empy 
	    if(!nearByVehicleModels.isEmpty()){
	    	// Delete All Entites 
	    	nearByVehicleModels.removeAll(nearByVehicleModels);
	    }//end if Condition 
		/// Saving New NearBy Vehicles
		for(Long activeVehicleId:workflowNearByVehicles.getVehicles()){
			// Creating Model to Persist 
			NearByVehicleModel nearByVehicleModel=new NearByVehicleModel();
			// generate Automatic ID
			Long generatedId = Utils.generateUUID();
			// TODO Check if the generatedId is Taken before 
			nearByVehicleModel.setId(generatedId);
			nearByVehicleModel.setActiveVehicleId(activeVehicleId);
			nearByVehicleModel.setPackageId(workflowNearByVehicles.getPackageId());
			nearByVehicleModel.setRequestId(workflowNearByVehicles.getRequestId());
			/// Saving the nearByVehicleModel
			nearByVehicleDao.save(nearByVehicleModel);
		}//end for Loop		
	}//end saveNearByVehicles 
	
	public NearByVehicleModel save(NearByVehicleModel nearByVehicleModel){
		return nearByVehicleDao.save(nearByVehicleModel);
	}
	
	public List<NearByVehicleModel> findAllNearVehiclesForPackageId(Long packageId){
		return nearByVehicleDao.findAllNearVehiclesForPackageId(packageId);
	}
	
	public List<NearByVehicleModel> findAcceptedVehiclesForPackage(Long packageId){
		return nearByVehicleDao.findAcceptedVehiclesForPackage(packageId);
	}
	
	public List<NearByVehicleModel> findRejectedVehiclesForPackage(Long packageId){
		return nearByVehicleDao.findRejectedVehiclesForPackage(packageId);
	}
	
	public NearByVehicleModel getNearbyVehicleForPackage(Long packageId,Long activeVehicleId){
		return nearByVehicleDao.getNearbyVehicleForPackage(packageId, activeVehicleId);
	}
	
}//End Class NearByVehicleService
