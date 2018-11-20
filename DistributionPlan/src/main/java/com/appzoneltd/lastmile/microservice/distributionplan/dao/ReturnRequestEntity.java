package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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

    @Column(name = "return_formatted_address", length = 250)
    private String returnFormatedAddress ;

    

    public ReturnRequestEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getReturnRequestId() {
        return this.returnRequestId;
    }

    public void setReturnRequestId(Long returnRequestId) {
        this.returnRequestId = returnRequestId;
    }

    public Date getRequesttime() {
        return this.requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public String getPickuplongitude() {
        return this.pickuplongitude;
    }

    public void setPickuplongitude(String pickuplongitude) {
        this.pickuplongitude = pickuplongitude;
    }

    public String getPickuplatitude() {
        return this.pickuplatitude;
    }

    public void setPickuplatitude(String pickuplatitude) {
        this.pickuplatitude = pickuplatitude;
    }

    public Long getHubId() {
        return this.hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public String getPickupwasellocation() {
        return this.pickupwasellocation;
    }

    public void setPickupwasellocation(String pickupwasellocation) {
        this.pickupwasellocation = pickupwasellocation;
    }

    public String getPickupformatedaddress() {
        return this.pickupformatedaddress;
    }

    public void setPickupformatedaddress(String pickupformatedaddress) {
        this.pickupformatedaddress = pickupformatedaddress;
    }

    public Date getPickupdate() {
        return this.pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }

    public String getRecipientname() {
        return this.recipientname;
    }

    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname;
    }

    public String getRecipientmobile() {
        return this.recipientmobile;
    }

    public void setRecipientmobile(String recipientmobile) {
        this.recipientmobile = recipientmobile;
    }

    public String getRecipientlongitude() {
        return this.recipientlongitude;
    }

    public void setRecipientlongitude(String recipientlongitude) {
        this.recipientlongitude = recipientlongitude;
    }

    public String getRecipientlatitude() {
        return this.recipientlatitude;
    }

    public void setRecipientlatitude(String recipientlatitude) {
        this.recipientlatitude = recipientlatitude;
    }

    public String getRecipientwasellocation() {
        return this.recipientwasellocation;
    }

    public void setRecipientwasellocation(String recipientwasellocation) {
        this.recipientwasellocation = recipientwasellocation;
    }

    public String getRecipientformatedaddress() {
        return this.recipientformatedaddress;
    }

    public void setRecipientformatedaddress(String recipientformatedaddress) {
        this.recipientformatedaddress = recipientformatedaddress;
    }

    public String getRecipientadditionalinfo() {
        return this.recipientadditionalinfo;
    }

    public void setRecipientadditionalinfo(String recipientadditionalinfo) {
        this.recipientadditionalinfo = recipientadditionalinfo;
    }

    public String getAdditionalservices() {
        return this.additionalservices;
    }

    public void setAdditionalservices(String additionalservices) {
        this.additionalservices = additionalservices;
    }

    public String getTimeFrom() {
        return this.timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return this.timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getLabelingtext() {
        return this.labelingtext;
    }

    public void setLabelingtext(String labelingtext) {
        this.labelingtext = labelingtext;
    }

    public String getPaymenttype() {
        return this.paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPaymentmethod() {
        return this.paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }



    public String getReturnLongitude() {
        return returnLongitude;
    }

    public void setReturnLongitude(String returnLongitude) {
        this.returnLongitude = returnLongitude;
    }

    public String getReturnLatitude() {
        return returnLatitude;
    }

    public void setReturnLatitude(String returnLatitude) {
        this.returnLatitude = returnLatitude;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

	public String getReturnFormatedAddress() {
		return returnFormatedAddress;
	}

	public void setReturnFormatedAddress(String returnFormatedAddress) {
		this.returnFormatedAddress = returnFormatedAddress;
	}
}
