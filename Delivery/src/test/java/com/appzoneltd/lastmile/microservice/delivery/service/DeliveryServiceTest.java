package com.appzoneltd.lastmile.microservice.delivery.service;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.repository.*;
import com.appzoneltd.lastmile.microservice.delivery.dto.DTOMapper;
import com.appzoneltd.lastmile.microservice.delivery.dto.DeliveryStatus;
import com.appzoneltd.lastmile.microservice.delivery.dto.RequestHistoryDTO;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alaa.nabil on 10/30/2017.
 */
@RunWith(SpringRunner.class)
public class DeliveryServiceTest {

    @InjectMocks
    private DeliveryServiceImpl deliveryService;
    @MockBean
    private DeliveryRequestRepository deliveryRequestRepository;
    @MockBean
    private RequestHistoryRepository requestHistoryRepository;
    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private KafkaSender kafkaSender;
    @MockBean
    private DTOMapper dtoMapper;
    @MockBean
    private ObjectMapper mapper;
    @MockBean
    private PrincipalService principalService;
    @MockBean
    private BuildingRepository buildingRepository;
    @MockBean
    private ExtendedDeliveryRequestRepository adminDeliveryRequestRepository;


    @Mock
    private Principal principal;
    private DeliveryRequestEntity deliveryRequestEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        deliveryRequestEntity = Mockito.mock(DeliveryRequestEntity.class);

        Mockito.when(principalService.getHubs(principal))
                .thenReturn(Arrays.asList(0L, 1L));

    }

    @Test
    public void fetchArchivedDeliveries_WhenPageGt0() {
        Mockito.when(deliveryRequestRepository.findAllByHubIdInAndRequestStatusIn(Arrays.asList(0L, 1L), Arrays.asList(DeliveryStatus.CANCELED_DELIVERY.name(), DeliveryStatus.DELIVERED.name())
                , new PageRequest(0, 2, new Sort(Sort.Direction.fromString(OrderBy.DESC.getOrderBy()), "created"))))
                .thenReturn(Arrays.asList(deliveryRequestEntity, deliveryRequestEntity));

        EndPointParameter endPointParameter = new EndPointParameter(null, 1, 2, OrderBy.DESC);
        final List<RequestHistoryDTO> requestHistoryDTOS = deliveryService.fetchArchivedDeliveries(endPointParameter, principal);
        Assert.assertNotNull(requestHistoryDTOS);
        Assert.assertEquals(2, requestHistoryDTOS.size());
    }

    @Test
    public void fetchArchivedDeliveries_WhenPage0() {
        Mockito.when(deliveryRequestRepository.findAllByHubIdInAndRequestStatusIn(Arrays.asList(0L, 1L), Arrays.asList(DeliveryStatus.CANCELED_DELIVERY.name(), DeliveryStatus.DELIVERED.name())
                , new Sort(Sort.Direction.fromString(OrderBy.DESC.getOrderBy()), "created")))
                .thenReturn(Arrays.asList(deliveryRequestEntity, deliveryRequestEntity, deliveryRequestEntity, deliveryRequestEntity));

        EndPointParameter endPointParameter = new EndPointParameter(null, 0, 2, OrderBy.DESC);
        final List<RequestHistoryDTO> requestHistoryDTOS = deliveryService.fetchArchivedDeliveries(endPointParameter, principal);
        Assert.assertNotNull(requestHistoryDTOS);
        Assert.assertEquals(4, requestHistoryDTOS.size());
    }

    @Test
    public void countArchivedDeliveries() {
        Mockito.when(deliveryRequestRepository.countAllByHubIdInAndRequestStatusIn(Arrays.asList(0L, 1L), Arrays.asList(DeliveryStatus.CANCELED_DELIVERY.name(), DeliveryStatus.DELIVERED.name())))
                .thenReturn(10L);

        EndPointParameter endPointParameter = new EndPointParameter(null, 1, 2, OrderBy.DESC);
        final long count = deliveryService.countArchivedDeliveries(principal);
        Assert.assertNotNull(count);
        Assert.assertEquals(10, count);
    }


}
