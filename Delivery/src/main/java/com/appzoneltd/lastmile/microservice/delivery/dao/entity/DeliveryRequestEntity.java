package com.appzoneltd.lastmile.microservice.delivery.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "delivery_request", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "DeliveryRequestEntity.countAll", query = "SELECT COUNT(x) FROM DeliveryRequestEntity x")
})
public class DeliveryRequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "delivery_request_id", nullable = false)
    private Long deliveryRequestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "requesttime")
    private Date requesttime;

    @Column(name = "pickuplongitude", length = 50)
    private String pickuplongitude;

    @Column(name = "pickuplatitude", length = 50)
    private String pickuplatitude;

    @Column(name = "hub_id")
    private Long hubId;

    @Column(name = "pickupwasellocation", length = 200)
    private String pickupwasellocation;

    @Column(name = "pickupformatedaddress", length = 250)
    private String pickupformatedaddress;

    @Column(name = "recipientname", length = 50)
    private String recipientname;

    @Column(name = "recipientmobile", length = 20)
    private String recipientmobile;

    @Column(name = "recipientlongitude", length = 50)
    private String recipientlongitude;

    @Column(name = "recipientlatitude", length = 50)
    private String recipientlatitude;

    @Column(name = "recipientwasellocation", length = 200)
    private String recipientwasellocation;

    @Column(name = "recipientformatedaddress", length = 250)
    private String recipientformatedaddress;

    @Column(name = "recipientadditionalinfo", length = 250)
    private String recipientadditionalinfo;

    @Column(name = "additionalservices", length = 200)
    private String additionalservices;

    @Temporal(TemporalType.DATE)
    @Column(name = "pickupdate")
    private Date pickupdate;

    @Column(name = "time_from", length = 50)
    private String timeFrom;

    @Column(name = "time_to", length = 50)
    private String timeTo;

    @Temporal(TemporalType.DATE)
    @Column(name = "deliverydate")
    private Date deliverydate;

    @Column(name = "labelingtext", length = 250)
    private String labelingtext;

    @Column(name = "paymenttype", length = 100)
    private String paymenttype;

    @Column(name = "paymentmethod", length = 100)
    private String paymentmethod;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "version", nullable = false)
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "request_status", length = 50)
    private String requestStatus;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "customer_id")
    private CustomerEntity recipient;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "customer_id")
    private CustomerEntity requester;
    
    @Column(name = "confirmationcode", length = 50)
    private String confirmationCode;

}
