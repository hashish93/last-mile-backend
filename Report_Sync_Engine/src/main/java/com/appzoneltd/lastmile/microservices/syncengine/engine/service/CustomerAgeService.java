package com.appzoneltd.lastmile.microservices.syncengine.engine.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.syncengine.engine.enums.CustomerAgeEnum;
import com.appzoneltd.lastmile.microservices.syncengine.engine.enums.PeriodEnum;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.CustomerAgeSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.CustomerRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao.UserRepository;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.UserEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.CustomerAgeDetailsRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.CustomerAgeSummeryRepository;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.CustomerAgeDetailsEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.CustomerAgeSummeryEntity;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Service
public class CustomerAgeService {

	@Autowired
	private SyncEngineDao syncEngineDao;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerAgeDetailsRepository customerAgeDetailsRepository;

	@Autowired
	private CustomerAgeSummeryRepository customerAgeSummeryRepository;

	public void getAllCustomerDetails(Date lastTimeSync) {

		syncAgesToDetailsTable(lastTimeSync);

		fillCustomerAgeDetailsToSummery();
	}

	private void syncAgesToDetailsTable(Date lastTimeSync) {
		List<CustomerEntity> customerEntities = null;

		if (lastTimeSync == null) {
			customerEntities = (List<CustomerEntity>) customerRepository.getOrderedCustomerEntites();
		} else {
			customerEntities = (List<CustomerEntity>) customerRepository.getlastUpdatedCustomersFrom(lastTimeSync);
		}

		System.out.println("CustomerAge effected rows " + customerEntities.size());

		if (customerEntities != null && customerEntities.size() > 0) {

			for (CustomerEntity customerEntity : customerEntities) {

				CustomerAgeDetailsEntity customerAgeDetailsEntity = new CustomerAgeDetailsEntity();

				if (lastTimeSync != null) {
					customerAgeDetailsEntity = customerAgeDetailsRepository
							.findByCustomerId(customerEntity.getCustomerId());
					if (customerAgeDetailsEntity == null) {
						customerAgeDetailsEntity = new CustomerAgeDetailsEntity();
					}
				}

				//				UserEntity userEntity = userRepository.findOne(customerEntity.getCustomerId());
				//				if (userEntity != null) {
				//					customerAgeDetailsEntity.setCreated(userEntity.getCreated());
				//				}
				customerAgeDetailsEntity.setCustomerId(customerEntity.getCustomerId());
				LocalDate birthday = new LocalDate(customerEntity.getBirthdate());
				LocalDate now = new LocalDate(new Date());
				Years age = Years.yearsBetween(birthday, now);

				if (age.getYears() > 0) {
					customerAgeDetailsEntity.setCustomerAge(new Long(age.getYears()));
				}
				customerAgeDetailsRepository.save(customerAgeDetailsEntity);
			}
			SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(2L);
			syncEngineEntity.setLastSyncTime(customerEntities.get(0).getUpdated());
			syncEngineDao.save(syncEngineEntity);
		}

	}

	private void fillCustomerAgeDetailsToSummery() {


		HashMap<String, Long> allCustomersSummaryMap = prepareCustomerReports();

		customerAgeSummeryRepository.deleteAll();

		saveSummeryReport(allCustomersSummaryMap);
	}

	private HashMap<String, Long> prepareCustomerReports() {

		List<CustomerAgeSummeryReportModel> customerAgeSummeryReportModels = 
				customerAgeDetailsRepository.findByAgeGrouped();

		HashMap<String, Long> map = new HashMap<>();

		for (CustomerAgeSummeryReportModel customerAgeSummeryReportModel : customerAgeSummeryReportModels) {
			String AgeCategory = null;

			if (customerAgeSummeryReportModel.getAge() == null) {

				AgeCategory = CustomerAgeEnum.OTHERS.name();

			} else if (customerAgeSummeryReportModel.getAge() < 21) {

				AgeCategory = CustomerAgeEnum.AGE_LESS_THAN_21.name();

			} else if (customerAgeSummeryReportModel.getAge() >= 21 && customerAgeSummeryReportModel.getAge() < 31) {

				AgeCategory = CustomerAgeEnum.AGE_BETWEEN_21_AND_30.name();

			} else if (customerAgeSummeryReportModel.getAge() >= 31 && customerAgeSummeryReportModel.getAge() < 45) {

				AgeCategory = CustomerAgeEnum.AGE_BETWEEN_31_AND_45.name();

			} else {
				AgeCategory = CustomerAgeEnum.AGE_ABOVE_45.name();
			}

			Long count = map.get(AgeCategory);
			if (count == null) {
				count = 0L;
			}
			count += customerAgeSummeryReportModel.getCount();
			map.put(AgeCategory, count);
		}
		return map;
	}

	private void saveSummeryReport(HashMap<String, Long> map) {

		CustomerAgeSummeryEntity customerAgeSummeryEntity = new CustomerAgeSummeryEntity();

		for (HashMap.Entry<String, Long> entry : map.entrySet()) {

			String ageCategory = entry.getKey();
			Long count = entry.getValue();


			if (CustomerAgeEnum.AGE_LESS_THAN_21.name().equalsIgnoreCase(ageCategory)) {

				customerAgeSummeryEntity.setAgeLessThan21(count);

			} else if (CustomerAgeEnum.AGE_BETWEEN_21_AND_30.name().equalsIgnoreCase(ageCategory)) {

				customerAgeSummeryEntity.setAgeBetween21And30(count);

			} else if (CustomerAgeEnum.AGE_BETWEEN_31_AND_45.name().equalsIgnoreCase(ageCategory)) {

				customerAgeSummeryEntity.setAgeBetween31And45(count);

			} else if (CustomerAgeEnum.AGE_ABOVE_45.name().equalsIgnoreCase(ageCategory)) {

				customerAgeSummeryEntity.setAgeAbove45(count);

			} else {
				customerAgeSummeryEntity.setOthers(count);
			}

		}
		customerAgeSummeryRepository.save(customerAgeSummeryEntity);
	}

	private Date getDateFromPreviousDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		Date end = cal.getTime();
		return end;
	}
}
