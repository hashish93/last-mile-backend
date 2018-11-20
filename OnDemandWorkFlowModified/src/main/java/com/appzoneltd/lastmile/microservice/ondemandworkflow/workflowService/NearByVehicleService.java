package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.NearByVehicleEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.Utils;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowDao.NearByVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearByVehicleService {

    @Autowired
    private NearByVehicleRepository nearByVehicleDao;

    public void saveNearByVehicles(Long packageId, Long requestId, List<Long> vehicles) {
        List<NearByVehicleEntity> nearByVehicleModels = nearByVehicleDao
                .findAllNearVehiclesForPackageId(packageId);
        if (!nearByVehicleModels.isEmpty()) {
            nearByVehicleModels.removeAll(nearByVehicleModels);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> vehicles ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> vehicles " + vehicles.toString());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> vehicles ");
        for (Long driverId : vehicles) {
            NearByVehicleEntity nearByVehicleEntity = nearByVehicleDao.getNearbyVehicleForPackage(packageId, driverId);
            if (nearByVehicleEntity == null) {
                nearByVehicleEntity = new NearByVehicleEntity();
                Long generatedId = Utils.generateUUID();
                nearByVehicleEntity.setId(generatedId);
            }
            nearByVehicleEntity.setActiveVehicleId(driverId);
            nearByVehicleEntity.setPackageId(packageId);
            nearByVehicleEntity.setRequestId(requestId);
            nearByVehicleDao.save(nearByVehicleEntity);
        }
    }

    public NearByVehicleEntity save(NearByVehicleEntity nearByVehicleModel) {
        return nearByVehicleDao.save(nearByVehicleModel);
    }

    public List<NearByVehicleEntity> findAllNearVehiclesForPackageId(Long packageId) {
        return nearByVehicleDao.findAllNearVehiclesForPackageId(packageId);
    }

    public List<NearByVehicleEntity> findAcceptedVehiclesForPackage(Long packageId) {
        return nearByVehicleDao.findAcceptedVehiclesForPackage(packageId);
    }

    public List<NearByVehicleEntity> findRejectedVehiclesForPackage(Long packageId) {
        return nearByVehicleDao.findRejectedVehiclesForPackage(packageId);
    }

    public NearByVehicleEntity getNearbyVehicleForPackage(Long packageId, Long activeVehicleId) {
        return nearByVehicleDao.getNearbyVehicleForPackage(packageId, activeVehicleId);
    }

}
