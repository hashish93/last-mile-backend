package com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.component;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.Invoice;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alaa.nabil on 7/31/2017.
 */
@Component
@Scope("singleton")
public class DeliveryInvoiceGenerator {

    private final PackageRepository packageRepository;
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final PickupRequestJpaRepository pickupRequestJpaRepository;

    @Autowired
    public DeliveryInvoiceGenerator(PackageRepository packageRepository, DeliveryRequestRepository deliveryRequestRepository, PickupRequestJpaRepository pickupRequestJpaRepository) {
        this.packageRepository = packageRepository;
        this.deliveryRequestRepository = deliveryRequestRepository;
        this.pickupRequestJpaRepository = pickupRequestJpaRepository;
    }

    public Invoice buildInvoiceModel(Long packageId, Long deliveryId) {

        final PackageEntity packageEntity = packageRepository.findOne(packageId);
        final DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        final PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(deliveryId);

        final int priceOne = 1000;
        final int priceTwo = 2000;
        DecimalFormat f = new DecimalFormat("000.00");
        int total = priceOne + priceTwo;
        Date date = new Date();

        List<InvoiceService> pickupServices = new ArrayList<>();

        InvoiceService p = new InvoiceService();
        p.setType(pickupRequestEntity.getPickupRequestType().getType());
        p.setLocation(deliveryRequestEntity.getPickupformatedaddress());
        p.setQuantity(" 1 " + packageEntity.getVerifiedPackage().getPackageType().getPackagetype() + " ("
                + packageEntity.getVerifiedPackage().getActualweight().toString() + ") ");
        p.setPrice(String.valueOf(priceOne));
        pickupServices.add(p);

        p = new InvoiceService();
        p.setType(packageEntity.getShipmentServiceType().getShipmentService().getService() + " - "
                + packageEntity.getShipmentServiceType().getType());
        p.setLocation(deliveryRequestEntity.getRecipientformatedaddress());
        p.setQuantity(" 1 " + packageEntity.getVerifiedPackage().getPackageType().getPackagetype() + " ("
                + packageEntity.getVerifiedPackage().getActualweight().toString() + ") ");
        p.setPrice(String.valueOf(priceTwo));
        pickupServices.add(p);

        Invoice invoice = new Invoice();
        invoice.setCode(String.valueOf((int) (99999 * 10000000000L)));
        invoice.setDate(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
        invoice.setPaymentMethod(deliveryRequestEntity.getPaymentmethod());
        invoice.setServices(pickupServices);
        invoice.setTaxes(f.format(total * 0.1));
        invoice.setTime(DateFormat.getTimeInstance(DateFormat.SHORT).format(date));
        invoice.setTotalWithoutTaxes(f.format(total * 0.9));
        invoice.setTotalWithTaxes(String.valueOf(total));
        return invoice;
    }
}
