package com.appzoneltd.lastmile.microservice.customer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.PackageTypeEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.CustomerRepository;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.PackageRepository;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservice.customer.model.CustomerPackageStatisticsModel;
import com.appzoneltd.lastmile.microservice.customer.model.CustomerStatistics;

@Service
public class CustomerRequestStatisticsService {

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private DeliveryRequestRepository deliveryRequestRepository;

	@Autowired
	private ReturnRequestRepository returnRequestRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RequestHistoryRepository requestHistoryRepository;
	
	@Autowired
	private PackageRepository packageRepository;

	public List<CustomerStatistics> getCustomerPackagesStatistics(Long customerId) {
		CustomerEntity customerEntity = customerRepository.findOne(customerId);
		if (customerEntity == null) {
			return null;
		}

		List<PickupRequestEntity> pickupRequestEntities = pickupRequestRepository.findByRequesterId(customerId);

		List<DeliveryRequestEntity> deliveryRequestEntities = deliveryRequestRepository.findByRequesterId(customerId);

		List<ReturnRequestEntity> returnRequestEntities = returnRequestRepository.findByRequesterId(customerId);

		CustomerStatistics customerCanceledStatistics = new CustomerStatistics("CANCELED", 0L);
		CustomerStatistics customerInProgressStatistics = new CustomerStatistics("IN_PROGRESS", 0L);
		CustomerStatistics customerProcessedStatistics = new CustomerStatistics("PROCESSED", 0L);

		for (PickupRequestEntity pickupRequestEntity : pickupRequestEntities) {
			if ("CANCELED".equalsIgnoreCase(pickupRequestEntity.getRequestStatus())) {
				customerCanceledStatistics.setData(customerCanceledStatistics.getData() + 1);
				continue;
			}
			if (!"PICKEDUP".equalsIgnoreCase(pickupRequestEntity.getRequestStatus())) {
				customerInProgressStatistics.setData(customerInProgressStatistics.getData() + 1);
			}
		}

		for (DeliveryRequestEntity deliveryRequestEntity : deliveryRequestEntities) {
			if ("CANCELED_DELIVERY".equalsIgnoreCase(deliveryRequestEntity.getRequestStatus())) {
				customerCanceledStatistics.setData(customerCanceledStatistics.getData() + 1);
			} else if ("DELIVERED".equalsIgnoreCase(deliveryRequestEntity.getRequestStatus())) {
				customerProcessedStatistics.setData(customerProcessedStatistics.getData() + 1);
			} else if (!"ENDED".equalsIgnoreCase(deliveryRequestEntity.getRequestStatus())) {
				customerInProgressStatistics.setData(customerInProgressStatistics.getData() + 1);
			}
		}

		for (ReturnRequestEntity returnRequestEntity : returnRequestEntities) {
			if ("CANCELED".equalsIgnoreCase(returnRequestEntity.getRequestStatus())) {
				customerCanceledStatistics.setData(customerCanceledStatistics.getData() + 1);
			} else if ("RETURNED".equalsIgnoreCase(returnRequestEntity.getRequestStatus())) {
				customerProcessedStatistics.setData(customerProcessedStatistics.getData() + 1);
			} else {
				customerInProgressStatistics.setData(customerInProgressStatistics.getData() + 1);
			}
		}

		List<CustomerStatistics> customerStatisticsList = new ArrayList<>();
		customerStatisticsList.add(customerCanceledStatistics);
		customerStatisticsList.add(customerInProgressStatistics);
		customerStatisticsList.add(customerProcessedStatistics);
		return customerStatisticsList;
	}

	public List<CustomerPackageStatisticsModel> getCustomerPackageTypeStatistics(Long customerId) {
		CustomerEntity customerEntity = customerRepository.findOne(customerId);
		if (customerEntity == null) {
			return null;
		}
		List<PackageEntity> packageEntities= new ArrayList<>();
		HashMap<PackageTypeEntity, Long> map = new HashMap<>();
		List<PickupRequestEntity> pickupRequestEntities = pickupRequestRepository.findByRequesterId(customerId);
		for (PickupRequestEntity pickupRequestEntity : pickupRequestEntities) {
			Long packageId = requestHistoryRepository.getPackageId(pickupRequestEntity.getPickupRequestId());
			if(packageId !=null){
				PackageEntity packageEntity = packageRepository.findOne(packageId);
				if(packageEntity !=null){
					packageEntities.add(packageEntity);
				}
			}
		}
		
		for(PackageEntity packageEntity : packageEntities){
			if(packageEntity.getPackageType() != null){
				Long count = map.get(packageEntity.getPackageType());
				if(count == null){
					count=0L;
				}
				count ++;
				map.put(packageEntity.getPackageType(), count);
			}
		}
		List<CustomerPackageStatisticsModel> packageStatisticsModels = fillCustomerPackageStatisticsFromMap(map);
		Collections.sort(packageStatisticsModels, new Comparator<CustomerPackageStatisticsModel>() {
			@Override
			public int compare(CustomerPackageStatisticsModel c1, CustomerPackageStatisticsModel c2) {
				return c2.getData().compareTo(c1.getData());
			}
		});
		
		if(packageStatisticsModels.size()>=4){
			return new ArrayList<>(packageStatisticsModels.subList(0, 5));	
		}
		   
		return packageStatisticsModels;
	}
	
	private List<CustomerPackageStatisticsModel> fillCustomerPackageStatisticsFromMap(HashMap<PackageTypeEntity, Long> map){
		List<CustomerPackageStatisticsModel> customerPackageStatisticsModels = new ArrayList<>();
		for (HashMap.Entry<PackageTypeEntity, Long> entry : map.entrySet()) {
			CustomerPackageStatisticsModel customerPackageStatisticsModel = new CustomerPackageStatisticsModel();
			customerPackageStatisticsModel.setLabels(entry.getKey().getPackagetype());
			customerPackageStatisticsModel.setData(entry.getValue());
			customerPackageStatisticsModels.add(customerPackageStatisticsModel);
		}
		return customerPackageStatisticsModels;
	}

}