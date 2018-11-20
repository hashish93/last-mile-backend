package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "return_request", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "ReturnRequestEntity.countAll", query = "SELECT COUNT(x) FROM ReturnRequestEntity x")
})
public class ReturnRequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "return_request_id", nullable = false)
    private Long returnRequestId;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "pickupdate")
    private Date pickupdate;

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

    @Column(name = "return_longitude", length = 50)
    private String returnLongitude;
    @Column(name = "return_latitude", length = 50)
    private String returnLatitude;
    @Column(name = "return_formatted_address", length = 250)
    private String returnFormattedAddress;
    @Column(name = "return_description", length = 250)
    private String returnDescription;
    @Column(name = "time_from", length = 50)
    private String timeFrom;
    @Column(name = "time_to", length = 50)
    private String timeTo;
    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;
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

    public ReturnRequestEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReturnFormattedAddress() {
        return returnFormattedAddress;
    }

    public void setReturnFormattedAddress(String returnFormattedAddress) {
        this.returnFormattedAddress = returnFormattedAddress;
    }

    public Long getReturnRequestId() {
        return returnRequestId;
    }

    public ReturnRequestEntity setReturnRequestId(Long returnRequestId) {
        this.returnRequestId = returnRequestId;
        return this;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public ReturnRequestEntity setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
        return this;
    }

    public String getPickuplongitude() {
        return pickuplongitude;
    }

    public ReturnRequestEntity setPickuplongitude(String pickuplongitude) {
        this.pickuplongitude = pickuplongitude;
        return this;
    }

    public String getPickuplatitude() {
        return pickuplatitude;
    }

    public ReturnRequestEntity setPickuplatitude(String pickuplatitude) {
        this.pickuplatitude = pickuplatitude;
        return this;
    }

    public Long getHubId() {
        return hubId;
    }

    public ReturnRequestEntity setHubId(Long hubId) {
        this.hubId = hubId;
        return this;
    }

    public String getPickupwasellocation() {
        return pickupwasellocation;
    }

    public ReturnRequestEntity setPickupwasellocation(String pickupwasellocation) {
        this.pickupwasellocation = pickupwasellocation;
        return this;
    }

    public String getPickupformatedaddress() {
        return pickupformatedaddress;
    }

    public ReturnRequestEntity setPickupformatedaddress(String pickupformatedaddress) {
        this.pickupformatedaddress = pickupformatedaddress;
        return this;
    }

    public Date getPickupdate() {
        return pickupdate;
    }

    public ReturnRequestEntity setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
        return this;
    }

    public String getRecipientname() {
        return recipientname;
    }

    public ReturnRequestEntity setRecipientname(String recipientname) {
        this.recipientname = recipientname;
        return this;
    }

    public String getRecipientmobile() {
        return recipientmobile;
    }

    public ReturnRequestEntity setRecipientmobile(String recipientmobile) {
        this.recipientmobile = recipientmobile;
        return this;
    }

    public String getRecipientlongitude() {
        return recipientlongitude;
    }

    public ReturnRequestEntity setRecipientlongitude(String recipientlongitude) {
        this.recipientlongitude = recipientlongitude;
        return this;
    }

    public String getRecipientlatitude() {
        return recipientlatitude;
    }

    public ReturnRequestEntity setRecipientlatitude(String recipientlatitude) {
        this.recipientlatitude = recipientlatitude;
        return this;
    }

    public String getRecipientwasellocation() {
        return recipientwasellocation;
    }

    public ReturnRequestEntity setRecipientwasellocation(String recipientwasellocation) {
        this.recipientwasellocation = recipientwasellocation;
        return this;
    }

    public String getRecipientformatedaddress() {
        return recipientformatedaddress;
    }

    public ReturnRequestEntity setRecipientformatedaddress(String recipientformatedaddress) {
        this.recipientformatedaddress = recipientformatedaddress;
        return this;
    }

    public String getRecipientadditionalinfo() {
        return recipientadditionalinfo;
    }

    public ReturnRequestEntity setRecipientadditionalinfo(String recipientadditionalinfo) {
        this.recipientadditionalinfo = recipientadditionalinfo;
        return this;
    }

    public String getAdditionalservices() {
        return additionalservices;
    }

    public ReturnRequestEntity setAdditionalservices(String additionalservices) {
        this.additionalservices = additionalservices;
        return this;
    }

    public String getReturnLongitude() {
        return returnLongitude;
    }

    public ReturnRequestEntity setReturnLongitude(String returnLongitude) {
        this.returnLongitude = returnLongitude;
        return this;
    }

    public String getReturnLatitude() {
        return returnLatitude;
    }

    public ReturnRequestEntity setReturnLatitude(String returnLatitude) {
        this.returnLatitude = returnLatitude;
        return this;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public ReturnRequestEntity setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
        return this;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public ReturnRequestEntity setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public ReturnRequestEntity setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public ReturnRequestEntity setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public String getLabelingtext() {
        return labelingtext;
    }

    public ReturnRequestEntity setLabelingtext(String labelingtext) {
        this.labelingtext = labelingtext;
        return this;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public ReturnRequestEntity setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
        return this;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public ReturnRequestEntity setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReturnRequestEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public ReturnRequestEntity setVersion(Long version) {
        this.version = version;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public ReturnRequestEntity setCreated(Date created) {
        this.created = created;
        return this;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public ReturnRequestEntity setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public CustomerEntity getRecipient() {
        return recipient;
    }

    public ReturnRequestEntity setRecipient(CustomerEntity recipient) {
        this.recipient = recipient;
        return this;
    }

    public CustomerEntity getRequester() {
        return requester;
    }

    public ReturnRequestEntity setRequester(CustomerEntity requester) {
        this.requester = requester;
        return this;
    }
}
