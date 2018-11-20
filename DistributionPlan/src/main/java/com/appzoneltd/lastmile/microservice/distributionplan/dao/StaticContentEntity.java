package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

@Entity
@Table(name = "static_content", schema = "lastmile_static_content_server")
@NamedQueries({
        @NamedQuery(name = "StaticContentEntity.countAll", query = "SELECT COUNT(x) FROM StaticContentEntity x")
})
public class StaticContentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "content_id", nullable = false)
    private Long contentId;

/*
    @Column(name = "contentname", nullable = false, length = 250)
    private String contentname;

    @Column(name = "contentpath", nullable = false, length = 250)
    private String contentpath;

    @Column(name = "httpcontenttype", nullable = false, length = 100)
    private String httpcontenttype;

    @Column(name = "extension", nullable = false, length = 50)
    private String extension;

    @Column(name = "server_id", nullable = false, length = 50)
    private String serverId;

    @Column(name = "version", nullable = false)
    private Long version;
*/

    public StaticContentEntity() {
        super();
    }

    public Long getContentId() {
        return this.contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

}
