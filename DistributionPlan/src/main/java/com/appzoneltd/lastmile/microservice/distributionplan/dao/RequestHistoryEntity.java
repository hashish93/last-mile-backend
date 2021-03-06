/*
 * Created on 8 Mar 2017 ( Time 15:31:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.distributionplan.dao;


import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "request_history"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="request_history", schema="lastmile_request" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="RequestHistoryEntity.countAll", query="SELECT COUNT(x) FROM RequestHistoryEntity x" )
} )
public class RequestHistoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="request_history_id", nullable=false)
    private Long       requestHistoryId ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="package_id", nullable=false)
    private Long       packageId    ;

    @Column(name="request_status", length=50)
    private String     requestStatus ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    private Date       created      ;

    @Column(name="request_id", nullable=false)
    private Long       requestId    ;

    @Column(name="version", nullable=false)
    private Long       version      ;

    @Column(name="request_type", length=50)
    private String     requestType  ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public RequestHistoryEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setRequestHistoryId( Long requestHistoryId ) {
        this.requestHistoryId = requestHistoryId ;
    }
    public Long getRequestHistoryId() {
        return this.requestHistoryId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : package_id ( int8 ) 
    public void setPackageId( Long packageId ) {
        this.packageId = packageId;
    }
    public Long getPackageId() {
        return this.packageId;
    }

    //--- DATABASE MAPPING : request_status ( varchar ) 
    public void setRequestStatus( String requestStatus ) {
        this.requestStatus = requestStatus;
    }
    public String getRequestStatus() {
        return this.requestStatus;
    }

    //--- DATABASE MAPPING : created ( timestamptz ) 
    public void setCreated( Date created ) {
        this.created = created;
    }
    public Date getCreated() {
        return this.created;
    }

    //--- DATABASE MAPPING : request_id ( int8 ) 
    public void setRequestId( Long requestId ) {
        this.requestId = requestId;
    }
    public Long getRequestId() {
        return this.requestId;
    }

    //--- DATABASE MAPPING : version ( int8 ) 
    public void setVersion( Long version ) {
        this.version = version;
    }
    public Long getVersion() {
        return this.version;
    }

    //--- DATABASE MAPPING : request_type ( varchar ) 
    public void setRequestType( String requestType ) {
        this.requestType = requestType;
    }
    public String getRequestType() {
        return this.requestType;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(requestHistoryId);
        sb.append("]:"); 
        sb.append(packageId);
        sb.append("|");
        sb.append(requestStatus);
        sb.append("|");
        sb.append(created);
        sb.append("|");
        sb.append(requestId);
        sb.append("|");
        sb.append(version);
        sb.append("|");
        sb.append(requestType);
        return sb.toString(); 
    } 

}
