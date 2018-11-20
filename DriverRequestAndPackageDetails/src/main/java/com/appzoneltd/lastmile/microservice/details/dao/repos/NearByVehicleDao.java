package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.NearByVehicleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NearByVehicleDao extends CrudRepository<NearByVehicleModel, Long> {

    @Query("SELECT near FROM NearByVehicleModel near WHERE near.packageId= :packageId AND response='ACCEPT' ORDER BY near.id ASC")
    List<NearByVehicleModel> findAcceptedVehiclesForPackage(@Param("packageId") Long packageId);

    List<NearByVehicleModel> findAllByActiveVehicleIdAndResponseNot(Long activeVehicleId, String response);
}
