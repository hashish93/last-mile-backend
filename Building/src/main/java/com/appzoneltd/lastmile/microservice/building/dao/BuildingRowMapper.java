package com.appzoneltd.lastmile.microservice.building.dao;

import com.appzoneltd.lastmile.microservice.building.model.BuildingInfo;
import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creating Building Row Mapper implements RowMapper to get All data row and
 * mapping it to objects
 *
 * @author alaa.nabil
 */
public class BuildingRowMapper implements RowMapper<BuildingInfo> {

    @Override
    public BuildingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        BuildingInfo building = new BuildingInfo();

        building.setBuildingId(rs.getLong("building_id"));
        building.setBuildingname(rs.getString("buildingname"));
        building.setBuildingnumber(rs.getString("buildingnumber"));
        building.setCountryId(rs.getLong("country_id"));
        building.setCountryCodeId(rs.getLong("country_code_id"));
        building.setCountryCode(rs.getString("phonecode"));
        building.setPhoneNumber(rs.getString("phone_number"));
        building.setCityId(rs.getLong("city_id"));
        building.setArea(rs.getString("area"));
        building.setStreet(rs.getString("street"));
        building.setWaselcode(rs.getString("waselcode"));
        building.setLongitude(rs.getString("longitude"));
        building.setLatitude(rs.getString("latitude"));
        building.setBuildingTypeId(rs.getLong("building_type_id"));
        building.setCreated(rs.getTimestamp("created"));
        building.setStatus(EntityStatus.valueOf(rs.getString("status")));
        building.setDescription(rs.getString("description"));
        building.setVersion(rs.getLong("version"));
        building.setCountryNameAr(rs.getString("country_name_ar"));
        building.setCountryNameEn(rs.getString("country_name_en"));
        building.setCityNameAr(rs.getString("city_name_ar"));
        building.setCityNameEn(rs.getString("city_name_en"));
        building.setType(rs.getString("type"));

        return building;
    }

}
