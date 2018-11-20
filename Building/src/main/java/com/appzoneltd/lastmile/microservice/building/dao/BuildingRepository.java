package com.appzoneltd.lastmile.microservice.building.dao;

public interface BuildingRepository {

    /**
     * Query for insert record in building table .
     **/
    public final static String BUILDING_CREATE_QUERY = "INSERT INTO lastmile_core.building(building_id, buildingname, buildingnumber, country_id, city_id, area, street, waselcode, longitude, latitude,building_type_id, description, country_code_id, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Query for Updating existing record in building table .
     **/

    public final static String BUILDING_UPDATE_QUERY = "UPDATE lastmile_core.building SET buildingname=?, buildingnumber=?, country_id=?, city_id=?, area=?, street=?, waselcode=?, longitude=?, latitude=?, building_type_id=?, description=?, phone_number=?, country_code_id=?, version=version+1 WHERE version=? AND building_id=?";

    /**
     * Query for Delete existing record in building table by updating isdeleted
     * column to 1 .
     **/
    public final static String BUILDING_MARK_AS_DELETED_QUERY = "UPDATE lastmile_core.building SET status= 'INACTIVE' WHERE building_id=?";

    /**
     * Query for Delete existing record in building table
     **/
    public final static String BUILDING_DELETED_QUERY = "DELETE FROM lastmile_core.building WHERE building_id=?";

    /**
     * Query for find all Building in building table
     **/
    public final static String BUILDING_FIND_ALL_QUERY = "SELECT building_id, buildingname, buildingnumber, building.country_id, building.city_id, area, street, waselcode, longitude, latitude, building.building_type_id, description, building.created, status, building.version, type, country.name_ar AS country_name_ar, country.name_en AS country_name_en,city.name_ar AS city_name_ar,city.name_en AS city_name_en, country_code_id, phone_number, codes.phonecode"
            + " FROM lastmile_core.building building"
            + " INNER JOIN lastmile_core.building_type type ON type.building_type_id=building.building_type_id"
            + " INNER JOIN lastmile_core.country country ON country.country_id=building.country_id"
            + " INNER JOIN lastmile_core.country_codes codes ON codes.id=building.country_code_id"
            + " INNER JOIN lastmile_core.city city ON city.city_id=building.city_id"
            + " WHERE status='ACTIVE' ORDER BY created ";

    /**
     * Query for finding Building by ID of building in building table
     **/

    public final static String BUILDING_FIND_BY_ID_QUERY = "SELECT building_id, buildingname, buildingnumber, building.country_id, building.city_id, area, street, waselcode, longitude, latitude, building.building_type_id, description, building.created, status, building.version, type, country.name_ar AS country_name_ar, country.name_en AS country_name_en,city.name_ar AS city_name_ar,city.name_en AS city_name_en, country_code_id, phone_number, codes.phonecode"
            + " FROM lastmile_core.building building"
            + " INNER JOIN lastmile_core.building_type type ON type.building_type_id=building.building_type_id"
            + " INNER JOIN lastmile_core.country country ON country.country_id=building.country_id"
            + " INNER JOIN lastmile_core.country_codes codes ON codes.id=building.country_code_id"
            + " INNER JOIN lastmile_core.city city ON city.city_id=building.city_id"
            + " WHERE status='ACTIVE' AND building.building_id=?";

    /**
     * Query for counting all Building building table
     **/

    public final static String BUILDING_COUNT_ALL = "SELECT COUNT(*) FROM lastmile_core.building"
            + " INNER JOIN lastmile_core.building_type type ON type.building_type_id=building.building_type_id"
            + " INNER JOIN lastmile_core.country country ON country.country_id=building.country_id"
            + " INNER JOIN lastmile_core.city city ON city.city_id=building.city_id"
            + " WHERE building.status='ACTIVE'";

    /**
     * Querys for Building Serving Area
     **/

    public final static String BUILDING_SERVING_AREA_CREATE_QUERY = "INSERT INTO lastmile_core.building_serving_area(building_serving_id,building_id, latitude, longitude) VALUES (?,?, ?, ?)";

    public final static String BUILDING_SERVING_AREA_DELETED_QUERY = "DELETE FROM lastmile_core.building_serving_area WHERE building_id=?";

    public final static String BUILDING_SERVING_AREA_FIND_BY_ID_QUERY = "SELECT buildingarea.building_id, buildingarea.latitude, buildingarea.longitude FROM lastmile_core.building_serving_area buildingarea INNER JOIN lastmile_core.building building ON buildingarea.building_id=building.building_id WHERE building.status='ACTIVE' AND building.building_id=?";

    public final static String BUILDING_SERVING_AREA_FIND_ALL = "SELECT buildingarea.building_id, buildingarea.latitude, buildingarea.longitude FROM lastmile_core.building_serving_area buildingarea INNER JOIN lastmile_core.building building ON buildingarea.building_id=building.building_id WHERE building.status='ACTIVE'";

    public final static String BUILDING_SERVING_AREA_FIND_ALL_EXCEPT_DETERMINED_ID = "SELECT buildingarea.building_id, buildingarea.latitude, buildingarea.longitude FROM lastmile_core.building_serving_area buildingarea INNER JOIN lastmile_core.building building ON buildingarea.building_id=building.building_id WHERE building.status='ACTIVE' AND building.building_id!=?";

    public final static String BUILDING_COUNT_RELATED_VEHICLE = "SELECT COUNT (vehicle_id) FROM lastmile_core.vehicle WHERE status='ACTIVE' AND building_id=?";

}
