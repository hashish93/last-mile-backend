package com.appzoneltd.lastmile.microservice.forgetpassword.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users", schema = "lastmile_authorization_server")
@NamedQueries({
        @NamedQuery(name = "UsersEntity.countAll", query = "SELECT COUNT(x) FROM UsersEntity x")
})
@Data
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password ;
    
    @ManyToOne
	@JoinColumn(name = "user_type_id", referencedColumnName = "id", insertable = true, updatable = true)
	private UserTypeEntity userType;
    
    @Column(name = "status", nullable = false, length = 50)
    private String status;
}
