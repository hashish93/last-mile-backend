package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.OnDemandDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.MyPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OnDemandService {
	private final PickupRequestRepositoryImp pickupRequestRepositoryImp;
	private final ObjectMapper objectMapper;

	@Autowired
	public OnDemandService(PickupRequestRepositoryImp pickupRequestRepositoryImp) {
		this.pickupRequestRepositoryImp = pickupRequestRepositoryImp;
		objectMapper = new ObjectMapper();
	}

	public List<OnDemandDto> findTakenOnDemand(OrderBy orderBy) {
		MyPrincipal myPrincipal = getAuthenticatedUser();
		// TODO This code will perform poorly with a lot of data. A long query
		// should be written and one row mapper should be used for Dto
		List<PickupRequestInfo> infos = pickupRequestRepositoryImp.findTakenOnDemand(orderBy, myPrincipal.getHubs());
		infos.sort(new Comparator<PickupRequestInfo>() {
			@Override
			public int compare(PickupRequestInfo o1, PickupRequestInfo o2) {
				if (o1.getOrderStatus().equals(o2.getOrderStatus())) {
					return (o1.getRequestTime().getTime() > o2.getRequestTime().getTime()) ? 1 : -1;
				} else {
					return o1.getOrderStatus().getOrderStatus().compareTo(o2.getOrderStatus().getOrderStatus());
				}
			}
		});

		return onDemandDtoInfo(infos);
	}

	public MyPrincipal getAuthenticatedUser() {
		MyPrincipal myPrincipal = null;
		try {
			myPrincipal = objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getName(),
					MyPrincipal.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return myPrincipal;
	}

	public List<OnDemandDto> onDemandDtoInfo(List<PickupRequestInfo> pickupRequestInfos) {
		List<OnDemandDto> onDemandtos = new ArrayList<OnDemandDto>();
		for (PickupRequestInfo data : pickupRequestInfos) {
			OnDemandDto onDemandDto = new OnDemandDto();
			onDemandDto.setPickupRequestId(data.getPickupRequestId());
			onDemandDto.setPickupFormatedAddress(data.getPickupFormatedAddress());
			onDemandDto.setPackageType(data.getPackageType());
			onDemandDto.setActualWeight(data.getActualWeight());
			onDemandDto.setReceivedFrom(data.getReceivedFrom());
			onDemandDto.setOrderStatus(data.getOrderStatus());
			onDemandDto.setBuildingName(data.getBuildingName());
			onDemandDto.setHubId(data.getHubId());
			onDemandtos.add(onDemandDto);

		}
		return onDemandtos;

	}

	public List<OnDemandDto> findUntakenOnDemand(OrderBy orderBy) {
		MyPrincipal myPrincipal = getAuthenticatedUser();
		List<PickupRequestInfo> infos = pickupRequestRepositoryImp.findUntakenOnDemand(orderBy, myPrincipal.getHubs());
		return onDemandDtoInfo(infos);
	}

	public int count() {
		return pickupRequestRepositoryImp.countAllOnDemandRequest(getAuthenticatedUser().getHubs());
	}
}
