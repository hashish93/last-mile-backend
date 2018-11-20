package com.appzoneltd.lastmile.microservice.workflowservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.workflowservice.model.NearByVehicleModel;

@Transactional
public interface NearByVehicleDao extends CrudRepository<NearByVehicleModel, Long>{
	
	@Query("SELECT near FROM NearByVehicleModel near WHERE near.packageId= :packageId ORDER BY near.id ASC")
	public List<NearByVehicleModel> findAllNearVehiclesForPackageId(@Param("packageId") Long packageId);
	
	@Query("SELECT near FROM NearByVehicleModel near WHERE near.packageId= :packageId AND response='ACCEPT' ORDER BY near.id ASC")
	public List<NearByVehicleModel> findAcceptedVehiclesForPackage(@Param("packageId") Long packageId);
	
	@Query("SELECT near FROM NearByVehicleModel near WHERE near.packageId= :packageId AND response='REJECT' ORDER BY near.id ASC")
	public List<NearByVehicleModel> findRejectedVehiclesForPackage(@Param("packageId") Long packageId);
	
	@Query("SELECT near FROM NearByVehicleModel near WHERE near.packageId= :packageId AND near.activeVehicleId= :activeVehicleId")
	public NearByVehicleModel getNearbyVehicleForPackage(@Param("packageId") Long packageId,@Param("activeVehicleId") Long activeVehicleId);
	
}
