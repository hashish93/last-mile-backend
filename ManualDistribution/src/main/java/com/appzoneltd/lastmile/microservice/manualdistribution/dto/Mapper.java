package com.appzoneltd.lastmile.microservice.manualdistribution.dto;

import com.appzoneltd.lastmile.microservice.manualdistribution.dao.*;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.OrderWeight;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PickupOrDeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    private PickupOrDeliveryReqRepo pickupOrDeliveryReqRepo;

    @Autowired
    private PickupJobOrderRepo pickupJobOrderRepo;

    @Autowired
    private DeliveryJobOrderRepo deliveryJobOrderRepo;

    @Autowired
    private ReturnJobOrderRepo returnJobOrderRepo;

    @Autowired
    private OrderWeightRepo orderWeightRepo;

    public JobOrderDto getJobOrdersOrderedByTimeDto(JobOrder job) {
        JobOrderDto jobGroupDto = new JobOrderDto();
        List<PickupOrDeliveryRequest> packageInfo = pickupOrDeliveryReqRepo.getOrderInfo(job.getJobOrderId());
        jobGroupDto.setActualWeight(getOrderWeight(job.getJobOrderId()));
        jobGroupDto.setId(job.getJobOrderId());
        jobGroupDto.setTimeFrom(job.getTimeFrom());
        jobGroupDto.setTimeTo(job.getTimeTo());
        jobGroupDto.setJobLongitude(job.getJobLongitude());
        jobGroupDto.setJobLatitude(job.getJobLatitude());
        jobGroupDto.setJobAddress(job.getJobAddress());
        jobGroupDto.setOrderPackageId(packageInfo.get(0).getPackageId());
        jobGroupDto.setJobType(packageInfo.get(0).getJobOrderType());
        return jobGroupDto;
    }

    public BigDecimal getOrderWeight(Long jobOrderId) {
        List<PickupOrDeliveryRequest> orderInfo = pickupOrDeliveryReqRepo.getOrderInfo(jobOrderId);
        if (!orderInfo.isEmpty()) {
            OrderWeight info = orderWeightRepo.JobOrderWeight(orderInfo.get(0).getPackageId());
            BigDecimal actualWeight = info.getActualWeight();
            return actualWeight;
        }
        return null;
    }

    public JobOrderTodayDto getJobOrderTodayDto(JobOrder job) {
        JobOrderTodayDto JobOrderTodayDto = new JobOrderTodayDto();
        JobOrderTodayDto.setId(job.getJobOrderId());
        JobOrderTodayDto.setActualWeight(getOrderWeight(job.getJobOrderId()));
        JobOrderTodayDto.setTimeFrom(job.getTimeFrom());
        JobOrderTodayDto.setTimeTo(job.getTimeTo());
        JobOrderTodayDto.setJobPackageType(getOrderPackageType(job.getJobOrderId()));
        JobOrderTodayDto.setJobAddress(job.getJobAddress());
        JobOrderTodayDto.setJobType(getOrderType(job.getJobOrderId()));
        return JobOrderTodayDto;
    }

    public String getOrderPackageType(Long jobOrderId) {
        List<PickupOrDeliveryRequest> orderInfo = pickupOrDeliveryReqRepo.getOrderInfo(jobOrderId);
        if (!orderInfo.isEmpty()) {
            OrderWeight info = orderWeightRepo.JobOrderWeight(orderInfo.get(0).getPackageId());
            String orderShapeType = info.getPackageType().getPackageType();
            return orderShapeType;
        }
        return null;
    }

    public String getOrderType(Long jobOrderId) {
        List<PickupOrDeliveryRequest> orderInfo = pickupOrDeliveryReqRepo.getOrderInfo(jobOrderId);
        if (!orderInfo.isEmpty()) {
            String orderType = orderInfo.get(0).getJobOrderType();
            return orderType;
        }
        return null;
    }

    public List<JobOrder> getJobsOfAllFroToday(Date today, Long hubId) {
        List<JobOrder> jobOrders = pickupJobOrderRepo.jobOrderAllIForToday(today, hubId);
        Map<Long, JobOrder> map = jobOrders.stream()
                .collect(Collectors.toMap(JobOrder::getJobOrderId, jobOrder -> jobOrder));

        List<JobOrder> deliveryJobOrders = deliveryJobOrderRepo.jobOrderAllIForToday(today, hubId);

        for (JobOrder deliveryOrder : deliveryJobOrders) {
            map.put(deliveryOrder.getJobOrderId(), deliveryOrder);
        }

        List<JobOrder> returnJobOrders = returnJobOrderRepo.jobOrderAllIForToday(today, hubId);
        for (JobOrder returnOrder : returnJobOrders) {
            map.put(returnOrder.getJobOrderId(), returnOrder);
        }

        return map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());

    }

    public List<JobOrder> getJobsOfAllTypes(List<Long> jobOrdersId) {
        List<JobOrder> jobOrders = pickupJobOrderRepo.jobOrderAllInfo(jobOrdersId);

        Map<Long, JobOrder> map = jobOrders.stream()
                .collect(Collectors.toMap(JobOrder::getJobOrderId, JobOrder -> JobOrder));

        List<JobOrder> deliveryJobOrders = deliveryJobOrderRepo.jobOrderAllInfo(jobOrdersId);

        for (JobOrder deliveryJobOrder : deliveryJobOrders) {

            map.put(deliveryJobOrder.getJobOrderId(), deliveryJobOrder);
        }

        List<JobOrder> returnJobOrders = returnJobOrderRepo.jobOrderAllInfo(jobOrdersId);
        for (JobOrder returnJobOrder : returnJobOrders) {
            map.put(returnJobOrder.getJobOrderId(), returnJobOrder);
        }

        List<JobOrder> result = map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());

        return result;
    }

}
