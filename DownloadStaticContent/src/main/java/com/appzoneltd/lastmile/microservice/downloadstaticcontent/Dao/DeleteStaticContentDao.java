package com.appzoneltd.lastmile.microservice.downloadstaticcontent.Dao;

interface DeleteStaticContentDao {

	public static final String DELETE_FILE_META_DATA = "UPDATE  lastmile_static_content_server.static_content SET status='INACTIVE' WHERE content_id=?";

}
