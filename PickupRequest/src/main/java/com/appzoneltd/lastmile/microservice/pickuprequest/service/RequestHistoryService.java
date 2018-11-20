package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistory;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryDTO;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryRepositoryImp;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryTimeLine;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.MyPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestHistoryService {

    private final PickupRequestRepositoryImp pickupRequestRepositoryImp;
    private final ObjectMapper objectMapper;
    @Autowired
    private RequestHistoryRepositoryImp requestHistoryRepositoryImp;

    @Autowired
    public RequestHistoryService(PickupRequestRepositoryImp pickupRequestRepositoryImp) {
        this.pickupRequestRepositoryImp = pickupRequestRepositoryImp;
        objectMapper = new ObjectMapper();
    }

    public List<RequestHistoryDTO> pickUpHistoryFindAll(int page, int maxSize, OrderBy orderType) {
        final MyPrincipal myPrincipal = getAuthenticatedUser();
        List<RequestHistoryDTO> pickUpHistory = null;
        pickUpHistory = requestInfoToRequestHistory(
                pickupRequestRepositoryImp.findAllHistoryRequests(page, maxSize, orderType, myPrincipal.getHubs()));
        return pickUpHistory;

    }

    private MyPrincipal getAuthenticatedUser() {
        MyPrincipal myPrincipal = null;
        try {

            myPrincipal = objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPrincipal;
    }

    private List<RequestHistoryDTO> requestInfoToRequestHistory(List<PickupRequestInfo> pickupRequestInfos) {
        List<RequestHistoryDTO> dtos = new ArrayList<RequestHistoryDTO>();
        for (PickupRequestInfo info : pickupRequestInfos) {
            dtos.add(new RequestHistoryDTO(info.getPickupRequestId(), info.getRequestType(), info.getPackageType(),
                    info.getOrderStatus().getOrderStatus(), info.getCancellationReason(),info.getBuildingName()));
        }
        return dtos;
    }

    public int countAllPickUpHistory() {
        final MyPrincipal myPrincipal = getAuthenticatedUser();
        return pickupRequestRepositoryImp.countAllHistoryRequest(myPrincipal.getHubs());
    }

    public List<RequestHistoryTimeLine> getAllRequestHistoryDetails(Long requestId) {
        List<RequestHistoryTimeLine> data = new ArrayList<RequestHistoryTimeLine>();
        // getting All request status and Date
        List<RequestHistory> allInfo = requestHistoryRepositoryImp.getAllRequestHistoryDetail(requestId);

        for (RequestHistory requestHistory : allInfo) {
            RequestHistoryTimeLine requestHistoryTimeLine = new RequestHistoryTimeLine();
            requestHistoryTimeLine.setStatus(requestHistory.getOrderStatus().toString());
            requestHistoryTimeLine.setCreationDate(requestHistory.getCreated());
            data.add(requestHistoryTimeLine);
        }
        return data;

    }

}
