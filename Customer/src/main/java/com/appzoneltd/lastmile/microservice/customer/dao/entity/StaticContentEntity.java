package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "contentname", nullable = false, length = 250)
    private String contentname;


    public StaticContentEntity() {
        super();
    }

    public Long getContentId() {
        return this.contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentname() {
        return this.contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

}
