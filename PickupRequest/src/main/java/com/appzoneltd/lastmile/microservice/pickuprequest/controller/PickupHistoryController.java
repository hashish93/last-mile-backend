package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryDTO;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RequestHistoryTimeLine;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PickupHistoryController {
    private final RequestHistoryService historyService;

    @Autowired
    public PickupHistoryController(RequestHistoryService historyService) {
        super();
        this.historyService = historyService;
    }

    @PreAuthorize("hasRole('ROLE_viewarchivedpickuprequests')")
    @RequestMapping(value = "/historyrequests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestHistoryDTO>> pickupHistoryFindAll(
            @RequestBody EndPointParameter requestParameter) {

        List<RequestHistoryDTO> PickupHistoryData = historyService.pickUpHistoryFindAll(requestParameter.getPage(),
                requestParameter.getMaxResult(), requestParameter.getOrderBy());

        return new ResponseEntity<>(PickupHistoryData, HttpStatus.OK);

    }

    @RequestMapping(value = "/historyrequestscount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> countAllPickupHistory() {
        return new ResponseEntity<Object>(historyService.countAllPickUpHistory(), (HttpStatus.OK));
    }


//    @PreAuthorize("hasRole('ROLE_historyrequest')")
    @RequestMapping(value = "/timeline", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestHistoryTimeLine>> timeLineFindAll(
            @RequestBody EndPointParameter requestParameter) {
        List<RequestHistoryTimeLine> timeLineData = historyService.getAllRequestHistoryDetails(requestParameter.getId());
        return new ResponseEntity<>(timeLineData, HttpStatus.OK);

    }


}
