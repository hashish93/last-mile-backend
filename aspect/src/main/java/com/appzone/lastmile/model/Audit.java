package com.appzone.lastmile.model;

import com.datastax.driver.core.utils.UUIDs;
import lombok.Data;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Appzone on 14-Feb-17.
 */
@Data


@Table(value = "audit_log")
public class Audit {
    @PrimaryKeyColumn(name = "id",ordinal = 1,type = PrimaryKeyType.PARTITIONED)
    private UUID id = UUIDs.timeBased();

    @Column(value = "screenname")
    private String ScreenName;

    @Column(value = "action")
    private String Action;

    @Column(value = "payload")
    private Object payload;

    @Column(value = "username")
    private String userName;

    @Column(value = "time")
    private Date time;

    @Column(value = "status")
    private String status;

    @Column(value = "response")
    private String response;
}
