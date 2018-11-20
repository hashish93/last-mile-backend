package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

@Entity
@Table(name = "delivery_request_document", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "DeliveryRequestDocumentEntity.countAll", query = "SELECT COUNT(x) FROM DeliveryRequestDocumentEntity x")
})
public class DeliveryRequestDocumentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "delivery_request_id", nullable = false)
    private Long deliveryRequestId;

    @ManyToOne
    @JoinColumn(name = "creditcard_image_id", referencedColumnName = "content_id")
    private StaticContentEntity creditCardImage;

    @ManyToOne
    @JoinColumn(name = "customer_image_id", referencedColumnName = "content_id")
    private StaticContentEntity customerIdImage;

    @Column(name = "barcode", length = 100)
    private String barcode;

    @OneToOne
    @JoinColumn(name = "delivery_request_id", referencedColumnName = "delivery_request_id", insertable = false, updatable = false)
    private DeliveryRequestEntity deliveryRequest;


    public DeliveryRequestDocumentEntity() {
        super();
    }

    public Long getDeliveryRequestId() {
        return this.deliveryRequestId;
    }

    public void setDeliveryRequestId(Long deliveryRequestId) {
        this.deliveryRequestId = deliveryRequestId;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public DeliveryRequestEntity getDeliveryRequest() {
        return this.deliveryRequest;
    }

    public void setDeliveryRequest(DeliveryRequestEntity deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    public StaticContentEntity getCreditCardImage() {
        return creditCardImage;
    }

    public void setCreditCardImage(StaticContentEntity creditCardImage) {
        this.creditCardImage = creditCardImage;
    }

    public StaticContentEntity getCustomerIdImage() {
        return customerIdImage;
    }

    public void setCustomerIdImage(StaticContentEntity customerIdImage) {
        this.customerIdImage = customerIdImage;
    }
}
