package com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.OrderStatus;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.PackageRequestDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PackageRequestRowMapper implements RowMapper<PackageRequestDto> {

	private PackageRequestDto packageRequestDto;

	@Override
	public PackageRequestDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		packageRequestDto = new PackageRequestDto();
		packageRequestDto.setPackageId(rs.getLong(("packageId")));
		packageRequestDto.setPackageTrackingNumber(rs.getString("packagetrackingnumber"));
		packageRequestDto.setPackageType(rs.getString("packagetype"));
		packageRequestDto.setRecipientName(rs.getString("recipientname"));
		packageRequestDto.setRecipientPhone(rs.getString("recipientphone"));
		packageRequestDto.setRequesterName(rs.getString("requestername"));
		packageRequestDto.setRequestId(rs.getLong("requestid"));
		packageRequestDto.setRequestStatus(rs.getString("requeststatus"));
		packageRequestDto.setRequestType(rs.getString("requesttype"));
		packageRequestDto.setSenderPhone(rs.getString("senderphone"));
		packageRequestDto.setStatus(rs.getString("status"));
		return packageRequestDto;
	}

}
