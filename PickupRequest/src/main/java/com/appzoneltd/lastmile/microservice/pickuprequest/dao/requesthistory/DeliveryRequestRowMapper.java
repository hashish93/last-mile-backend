package com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.MyPickupRequestDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alaa.nabil on 5/26/2017.
 */
public class DeliveryRequestRowMapper implements RowMapper<MyPickupRequestDTO> {
    @Override
    public MyPickupRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        MyPickupRequestDTO myPickupRequestDTO = new MyPickupRequestDTO(rs.getLong("pickup_request_id"), rs.getString("nickname"),
                rs.getString("recipientName"), rs.getString("recipientAddress"), rs.getString("status"),
                rs.getString("requesttype"), rs.getString("longitude"), rs.getString("latitude"), null, rs.getLong("package_id"), rs.getString("package_description"));

        myPickupRequestDTO.setConfirmationCode(rs.getString("confirmationcode"));


        return myPickupRequestDTO;
    }
}
