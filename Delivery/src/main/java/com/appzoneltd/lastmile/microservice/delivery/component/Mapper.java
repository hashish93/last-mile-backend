package com.appzoneltd.lastmile.microservice.delivery.component;

import com.appzoneltd.lastmile.microservice.delivery.dao.repository.BuildingRepository;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by alaa.nabil on 10/30/2017.
 */
@Component
@Scope(value = "singleton")
public class Mapper {

    private final PickupRequestJpaRepository pickupRequestJpaRepository;
    private final RequestHistoryRepository requestHistoryRepository;
    private final BuildingRepository buildingRepository;

    @Autowired
    public Mapper(PickupRequestJpaRepository pickupRequestJpaRepository, RequestHistoryRepository requestHistoryRepository, BuildingRepository buildingRepository) {
        this.pickupRequestJpaRepository = pickupRequestJpaRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.buildingRepository = buildingRepository;
    }


}
