package com.appzoneltd.lastmile.microservice.manualdistribution.service.saveplan;

import com.appzoneltd.lastmile.microservice.manualdistribution.dao.PlanRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.SavePlanOrderRepo;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.NotificationModel;
import com.appzoneltd.lastmile.microservice.manualdistribution.dto.SavePlanOrderDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PlanEntity;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SavePlanOrderEntity;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.SavePlanOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.TodayPlan;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.PrincipalService;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SavePlanServiceImpl implements SavePlanService {
    private final static String PUSH_NOTIFICATION = "push-notification";

    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final PrincipalService principalService;
    private SavePlanOrderRepo savePlanOrderRepo;
    private PlanRepo planRepo;

    @Autowired
    public SavePlanServiceImpl(SavePlanOrderRepo savePlanOrderRepo, PlanRepo planRepo,
                               ActiveVehicleJpaRepository activeVehicleJpaRepository, KafkaTemplate<Long, String> kafkaTemplate,
                               PrincipalService principalService) {
        this.savePlanOrderRepo = savePlanOrderRepo;
        this.planRepo = planRepo;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.principalService = principalService;
        this.objectMapper = new ObjectMapper();
    }

    public Long savePlan(List<SavePlanOrderDto> savePlanOrders, Long hubId) throws JsonProcessingException {
        Long planId = Utils.generateUUID();

        PlanEntity plan = new PlanEntity();
        plan.setId(planId);
        plan.setCreated(new Date());
        plan.setBuildingId(hubId);
        planRepo.save(plan);
        for (SavePlanOrderDto savePlanOrder : savePlanOrders) {
            for (SavePlanOrder job : savePlanOrder.getJobOrders()) {
                Long Id = Utils.generateUUID();
                SavePlanOrderEntity savePlan = new SavePlanOrderEntity();
                savePlan.setPlanId(planId);
                savePlan.setId(Id);
                savePlan.setActiveVehicleId(savePlanOrder.getActiveVehicleId());
                savePlan.setEstimatedTimeForArrival(job.getEstimatedTimeForArrival());
                savePlan.setPriorty(job.getPriority());
                savePlan.setOrderId(job.getOrderId());
                savePlan.setPackageId(job.getPackageId());
                savePlan.setJobOrderType(job.getOrderType());
                savePlan.setArrivalTime(job.getArrivalTime());
                savePlan.setDepartureTime(job.getDepartureTime());
                savePlan.setStatus(job.getStatus());
                savePlanOrderRepo.save(savePlan);
            }
            if (savePlanOrder.getJobOrders() != null && !savePlanOrder.getJobOrders().isEmpty())
                kafkaTemplate.send(PUSH_NOTIFICATION,
                        objectMapper.writeValueAsString(buildPushNotificationModelToSendToDriver(
                                activeVehicleJpaRepository.findOne(savePlanOrder.getActiveVehicleId()).getDriver()
                                        .getId(),
                                savePlanOrder.getJobOrders().size())));
        }

        return planId;
    }

    private NotificationModel buildPushNotificationModelToSendToDriver(Long customerId, int sizeOfOrders) {

        NotificationModel model = new NotificationModel();

        model.setPackageId(null);
        model.setRecipientType("DRIVER");

        Long[] userIds = {customerId};
        model.setUserIds(userIds);
        Map<String, Object> notificationData = new HashMap<>();

        notificationData.put("notification_title", "LastMile");
        notificationData.put("notification_body", "Loading Orders");
        notificationData.put("notification_item_title", "New Packages");
        notificationData.put("notification_item_body", "You have : (" + sizeOfOrders + ") to load in your vehicle. ");
        notificationData.put("type", "LOADING_ORDERS");
        notificationData.put("time", new Date().getTime());

        notificationData.put("payload", null);
        model.setData(notificationData);
        return model;
    }

    @Override
    public TodayPlan checkPlanForToday(Long hubId) {
        Date creationDate = creationDateForTodayPlan(hubId);
        TodayPlan todayPlan = new TodayPlan();
        todayPlan.setCreationDate(creationDate);
        todayPlan.setCheckPlanExist(false);
        if (creationDate == null)
            return todayPlan;

        Date dateNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        if (sdf.format(dateNow).equals(sdf.format(creationDate)))
            todayPlan.setCheckPlanExist(true);

        return todayPlan;
    }

    private Date creationDateForTodayPlan(Long hubId) {
        Date creationDateForTodayPlan = null;

        List<PlanEntity> planEntity = (List<PlanEntity>) planRepo.findLatest(hubId);
        if (!planEntity.isEmpty()) {
            if (planEntity.get(0).getCreated() != null) {
                creationDateForTodayPlan = planEntity.get(0).getCreated();
            }
        }

        return creationDateForTodayPlan;
    }

}
