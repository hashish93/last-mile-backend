
package com.appzoneltd.lastmile.microservice.hubconfig.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="contact_us_email", schema="lastmile_core" )

public class ContactUsEmailEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name="id", nullable=false)
    private Long       id           ;


    @Column(name="email_title", nullable=false, length=100)
    private String     emailTitle   ;

    @Column(name="email_address", nullable=false, length=100)
    private String     emailAddress ;

    @ManyToOne
    @JoinColumn(name="contactus_id", referencedColumnName="id")
    private ContactUsEntity contactUs   ;


    public ContactUsEmailEntity() {
		super();
    }
    

    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }


    public void setEmailTitle( String emailTitle ) {
        this.emailTitle = emailTitle;
    }
    public String getEmailTitle() {
        return this.emailTitle;
    }

  
    public void setEmailAddress( String emailAddress ) {
        this.emailAddress = emailAddress;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }



    public void setContactUs( ContactUsEntity contactUs ) {
        this.contactUs = contactUs;
    }
    public ContactUsEntity getContactUs() {
        return this.contactUs;
    }


    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(emailTitle);
        sb.append("|");
        sb.append(emailAddress);
        return sb.toString(); 
    } 

}
