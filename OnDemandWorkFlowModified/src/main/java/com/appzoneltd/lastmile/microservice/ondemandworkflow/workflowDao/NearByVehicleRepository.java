package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowDao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.NearByVehicleEntity;

@Transactional
public interface NearByVehicleRepository extends CrudRepository<NearByVehicleEntity, Long>{
	
	@Query("SELECT near FROM NearByVehicleEntity near WHERE near.packageId= :packageId ORDER BY near.id ASC")
	public List<NearByVehicleEntity> findAllNearVehiclesForPackageId(@Param("packageId") Long packageId);
	
	@Query("SELECT near FROM NearByVehicleEntity near WHERE near.packageId= :packageId AND response='ACCEPT' ORDER BY near.id ASC")
	public List<NearByVehicleEntity> findAcceptedVehiclesForPackage(@Param("packageId") Long packageId);
	
	@Query("SELECT near FROM NearByVehicleEntity near WHERE near.packageId= :packageId AND response='REJECT' ORDER BY near.id ASC")
	public List<NearByVehicleEntity> findRejectedVehiclesForPackage(@Param("packageId") Long packageId);
	
	@Query("SELECT near FROM NearByVehicleEntity near WHERE near.packageId= :packageId AND near.activeVehicleId= :activeVehicleId")
	public NearByVehicleEntity getNearbyVehicleForPackage(@Param("packageId") Long packageId,@Param("activeVehicleId") Long activeVehicleId);
	
}
