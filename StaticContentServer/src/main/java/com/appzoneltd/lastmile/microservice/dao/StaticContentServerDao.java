package com.appzoneltd.lastmile.microservice.dao;

interface StaticContentServerDao {

	public static final String SAVE_FILE_META_DATA = "INSERT INTO lastmile_static_content_server.static_content(content_id,contentname,extension,httpcontenttype,contentpath,server_id) VALUES ( ? , ? , ? , ? , ? , ?)";

	public static final String DELETE_FILE_META_DATA = "UPDATE lastmile_static_content_server.static_content SET status='INACTIVE'  WHERE content_id=?";

}
