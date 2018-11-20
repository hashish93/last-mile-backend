package com.appzoneltd.lastmile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.manualdistribution.dao.PlanRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.PlanTmpRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.SavePlanOrderRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.SavePlanOrderTmpRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PlanEntity;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PlanEntityTmp;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SavePlanOrderEntity;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SavePlanOrderEntityWithStatus;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.automaticservice.SavePlanTmpToPlan;
import com.appzoneltd.lastmile.microservice.utility.Utils;

/**
 * Created by hashish on 4/9/2017.
 */
@Service
public class SavePlanTmpToPlanImpl implements SavePlanTmpToPlan {

    @Autowired
    PlanTmpRepo planTmpRepo;
    @Autowired
    PlanRepo planRepo;
    @Autowired
    SavePlanOrderTmpRepo savePlanOrderTmpRepo;
    @Autowired
    SavePlanOrderRepo savePlanOrderRepo;


    private PlanEntityTmp getPlan(){
        List<PlanEntityTmp> planEntityTmps = (List<PlanEntityTmp>) planTmpRepo.findAll();
        if(!planEntityTmps.isEmpty()){
            PlanEntityTmp planEntityTmp = planEntityTmps.get(0);
            return planEntityTmp;
        }
        return null;
    }

    private Long SaveOriginalPlan(PlanEntityTmp planEntityTmp ,  List<SavePlanOrderEntityWithStatus> planDetails){
        PlanEntity planEntity = planTmpEntityToPlanEntity(planEntityTmp);
        List<SavePlanOrderEntity> savePlanOrderEntities = savePlanToSavePlanStatus(planDetails);
        planRepo.save(planEntity);
        savePlanOrderRepo.save(savePlanOrderEntities);
        savePlanOrderTmpRepo.deleteAll();
        planTmpRepo.deleteAll();
        Long id = planRepo.findLatest().get(0).getId();
        return id;
    }

    private PlanEntity planTmpEntityToPlanEntity(PlanEntityTmp planEntityTmp){
        PlanEntity planEntity = new PlanEntity();
        planEntity.setId(planEntityTmp.getId());
        return planEntity;
    }

    private List<SavePlanOrderEntity> savePlanToSavePlanStatus(List<SavePlanOrderEntityWithStatus> planDetailsWithStatus){
        List<SavePlanOrderEntity> planDetails = new ArrayList<>();
        for (SavePlanOrderEntityWithStatus planItemWithStatus : planDetailsWithStatus){
            if(!"FAIL".equalsIgnoreCase(planItemWithStatus.getStatus())){
                SavePlanOrderEntity savePlanOrderEntity = new SavePlanOrderEntity();
                savePlanOrderEntity.setId(Utils.generateUUID());
                savePlanOrderEntity.setActiveVehicleId(planItemWithStatus.getActiveVehicleId());
                savePlanOrderEntity.setArrivalTime(planItemWithStatus.getArrivalTime());
                savePlanOrderEntity.setDepartureTime(planItemWithStatus.getDepartureTime());
                savePlanOrderEntity.setEstimatedTimeForArrival(planItemWithStatus.getEstimatedTimeForArrival());
                savePlanOrderEntity.setJobOrderType(planItemWithStatus.getJobOrderType());
                savePlanOrderEntity.setOrderId(planItemWithStatus.getOrderId());
                savePlanOrderEntity.setPackageId(planItemWithStatus.getPackageId());
                savePlanOrderEntity.setPlanId(planItemWithStatus.getPlanId());
                savePlanOrderEntity.setPriorty(planItemWithStatus.getPriorty());
                planDetails.add(savePlanOrderEntity);
            }

        }
        return planDetails;
    }

    @Override
    public Long save() {
        PlanEntityTmp planEntityTmp =getPlan();
        if(planEntityTmp !=null){
            List<SavePlanOrderEntityWithStatus> planDetails = savePlanOrderTmpRepo.getPlanDetailsByPlanId(planEntityTmp.getId());
            return SaveOriginalPlan(planEntityTmp , planDetails);
        }
        return null;
    }
}
