package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "confirmationcode")
    private String confirmationCode;

    @Column(name = "countrycode", length = 50)
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "customer_id")
    private CustomerEntity recipient;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "customer_id")
    private CustomerEntity requester;


    public DeliveryRequestEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDeliveryRequestId() {
        return deliveryRequestId;
    }

    public void setDeliveryRequestId(Long deliveryRequestId) {
        this.deliveryRequestId = deliveryRequestId;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public String getPickuplongitude() {
        return pickuplongitude;
    }

    public void setPickuplongitude(String pickuplongitude) {
        this.pickuplongitude = pickuplongitude;
    }

    public String getPickuplatitude() {
        return pickuplatitude;
    }

    public void setPickuplatitude(String pickuplatitude) {
        this.pickuplatitude = pickuplatitude;
    }

    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public String getPickupwasellocation() {
        return pickupwasellocation;
    }

    public void setPickupwasellocation(String pickupwasellocation) {
        this.pickupwasellocation = pickupwasellocation;
    }

    public String getPickupformatedaddress() {
        return pickupformatedaddress;
    }

    public void setPickupformatedaddress(String pickupformatedaddress) {
        this.pickupformatedaddress = pickupformatedaddress;
    }

    public String getRecipientname() {
        return recipientname;
    }

    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname;
    }

    public String getRecipientmobile() {
        return recipientmobile;
    }

    public void setRecipientmobile(String recipientmobile) {
        this.recipientmobile = recipientmobile;
    }

    public String getRecipientlongitude() {
        return recipientlongitude;
    }

    public void setRecipientlongitude(String recipientlongitude) {
        this.recipientlongitude = recipientlongitude;
    }

    public String getRecipientlatitude() {
        return recipientlatitude;
    }

    public void setRecipientlatitude(String recipientlatitude) {
        this.recipientlatitude = recipientlatitude;
    }

    public String getRecipientwasellocation() {
        return recipientwasellocation;
    }

    public void setRecipientwasellocation(String recipientwasellocation) {
        this.recipientwasellocation = recipientwasellocation;
    }

    public String getRecipientformatedaddress() {
        return recipientformatedaddress;
    }

    public void setRecipientformatedaddress(String recipientformatedaddress) {
        this.recipientformatedaddress = recipientformatedaddress;
    }

    public String getRecipientadditionalinfo() {
        return recipientadditionalinfo;
    }

    public void setRecipientadditionalinfo(String recipientadditionalinfo) {
        this.recipientadditionalinfo = recipientadditionalinfo;
    }

    public String getAdditionalservices() {
        return additionalservices;
    }

    public void setAdditionalservices(String additionalservices) {
        this.additionalservices = additionalservices;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getLabelingtext() {
        return labelingtext;
    }

    public void setLabelingtext(String labelingtext) {
        this.labelingtext = labelingtext;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public CustomerEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(CustomerEntity recipient) {
        this.recipient = recipient;
    }

    public CustomerEntity getRequester() {
        return requester;
    }

    public void setRequester(CustomerEntity requester) {
        this.requester = requester;
    }

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
