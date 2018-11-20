package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import static com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.PackageJpaRepository.GET_FILTERED_PACKAGES;
import static com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.PackageJpaRepository.GET_FILTERED_PACKAGES_WITH_OFFSET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestTypeEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.RequestTypeRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.PackageRequestRowMapper;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.MyPrincipal;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageDetailsDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageRequestDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageStatus;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageTimeLineDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PagingParameters;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.RequestType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PackageRequestInfoServiceImpl implements PackageRequestInfoService {

	@Autowired
	private RequestHistoryJpaRepository requestHistoryRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private RequestTypeRepository requestTypeRepository;

	@Override
	public List<PackageRequestDto> getPackageRequests(PagingParameters pagingParameters) {
		MyPrincipal myPrincipal = getAuthenticatedUser();
		if (pagingParameters.getPage() > 0) {
			return getAllPackageRequestInfoWithOffset(pagingParameters, myPrincipal.getHubs());
		} else
			return getAllPackageRequest(pagingParameters, myPrincipal.getHubs());
	}

	private MyPrincipal getAuthenticatedUser() {
		MyPrincipal myPrincipal = null;
		try {

			myPrincipal = objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getName(),
					MyPrincipal.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myPrincipal;
	}

	private List<PackageRequestDto> getAllPackageRequestInfoWithOffset(PagingParameters pagingParameters,
			List<Long> hubs) {
		int offset = 0;
		if (pagingParameters.getPage() > 0) {
			offset = (pagingParameters.getPage() - 1) * pagingParameters.getPageSize();
		}
		final MapSqlParameterSource parameterSource = adjustPagingParameters(pagingParameters).addValue("hubs", hubs)
				.addValue("pageSize", pagingParameters.getPageSize()).addValue("offset", offset);
		return namedParameterJdbcTemplate.query(GET_FILTERED_PACKAGES_WITH_OFFSET, parameterSource,
				new PackageRequestRowMapper());
	}

	private MapSqlParameterSource adjustPagingParameters(PagingParameters pagingParameters) {

		PagingParameters parameters = new PagingParameters();

		if (pagingParameters.getRequestId() != null)
			parameters.setRequestId("%" + pagingParameters.getRequestId() + "%");

		if (pagingParameters.getPackageId() != null)
			parameters.setPackageId("%" + pagingParameters.getPackageId() + "%");

		if (pagingParameters.getPackageTrackingNumber() != null)
			parameters.setPackageTrackingNumber("%" + pagingParameters.getPackageTrackingNumber() + "%");

		if (pagingParameters.getRecipientName() != null)
			parameters.setRecipientName("%" + pagingParameters.getRecipientName() + "%");

		if (pagingParameters.getRecipientPhone() != null)
			parameters.setRecipientPhone("%" + pagingParameters.getRecipientPhone() + "%");

		if (pagingParameters.getPackageType() != null)
			parameters.setPackageType("%" + pagingParameters.getPackageType() + "%");

		if (pagingParameters.getRequestStatus() != null)
			parameters.setRequestStatus("%" + pagingParameters.getRequestStatus() + "%");

		if (pagingParameters.getRequesterName() != null)
			parameters.setRequesterName("%" + pagingParameters.getRequesterName() + "%");

		if (pagingParameters.getSenderPhone() != null)
			parameters.setSenderPhone("%" + pagingParameters.getSenderPhone() + "%");

		if (pagingParameters.getStatus() != null)
			parameters.setStatus("%" + pagingParameters.getStatus() + "%");

		if (pagingParameters.getRequestType() != null)
			parameters.setRequestType("%" + pagingParameters.getRequestType() + "%");

		parameters.setMaxResult(pagingParameters.getMaxResult());
		parameters.setOrderBy(pagingParameters.getOrderBy());
		parameters.setPage(pagingParameters.getPage());

		System.out.println(parameters.toString());
		return new MapSqlParameterSource().addValue("requestId", parameters.getRequestId())
				.addValue("packageId", parameters.getPackageId()).addValue("packageType", parameters.getPackageType())
				.addValue("status", parameters.getStatus()).addValue("recipientName", parameters.getRecipientName())
				.addValue("recipientPhone", parameters.getRecipientPhone())
				.addValue("requesterName", parameters.getRequesterName())
				.addValue("senderPhone", parameters.getSenderPhone())
				.addValue("packageTrackingNumber", parameters.getPackageTrackingNumber())
				.addValue("requestType", parameters.getRequestType())
				.addValue("requestStatus", parameters.getRequestStatus());
	}

	@Override
	public List<PackageRequestDto> getAllPackageRequest(PagingParameters pagingParameters, List<Long> hubs) {

		final MapSqlParameterSource parameterSource = adjustPagingParameters(pagingParameters).addValue("hubs", hubs);
		return namedParameterJdbcTemplate.query(GET_FILTERED_PACKAGES, parameterSource, new PackageRequestRowMapper());
	}

	@Override
	public Object[] getRequestTypes() {
		return RequestType.values();
	}

	@Override
	public Object[] getPackageStatus() {
		return PackageStatus.values();
	}

	;

	@Override
	public List<RequestTypeEntity> getRequestTypeStatus() {
		return (List<RequestTypeEntity>) requestTypeRepository.findAll();
	}

	public List<PackageDetailsDto> getPackageRequestDetails(Long packageId) {

		List<PackageDetailsDto> packageDetailsDtos = new ArrayList<>();
		List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByPackageId(packageId);

		Set<String> requestTypes = new HashSet<>();

		for (RequestHistoryEntity requestHistoryEntity : requestHistoryEntities) {
			if (!requestTypes.contains(requestHistoryEntity.getRequestType())) {
				PackageDetailsDto packageDetailsDto = new PackageDetailsDto();
				packageDetailsDto.setRequestType(requestHistoryEntity.getRequestType());
				packageDetailsDto.setRequestDate(requestHistoryEntity.getCreated());
				// ADD TO LIST
				packageDetailsDtos.add(packageDetailsDto);
				// ADD TO SET
				requestTypes.add(requestHistoryEntity.getRequestType());
			}
		}

		Collections.reverse(packageDetailsDtos);

		return packageDetailsDtos;
	}

	public List<PackageTimeLineDto> getTimeLineForPackage(Long packageId) {
		List<PackageTimeLineDto> PackageTimeLineDtos = new ArrayList<>();
		if (packageId != null) {
			List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository
					.listRequestHistoryTimeLine(packageId);
			for (RequestHistoryEntity entity : requestHistoryEntities) {
				PackageTimeLineDto packageTimeLineDto = new PackageTimeLineDto();
				packageTimeLineDto.setStatus(entity.getRequestStatus());
				packageTimeLineDto.setCreationDate(entity.getCreated());
				PackageTimeLineDtos.add(packageTimeLineDto);
			}
			return PackageTimeLineDtos;
		}
		return new ArrayList<>();
	}

}
