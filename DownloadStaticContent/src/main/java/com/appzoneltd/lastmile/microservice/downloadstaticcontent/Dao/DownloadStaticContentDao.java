package com.appzoneltd.lastmile.microservice.downloadstaticcontent.Dao;

interface DownloadStaticContentDao {
	
	public static final String GET_FILE_INFO_META_DATA="SELECT contentname, contentpath, httpcontenttype, extension, server_id FROM lastmile_static_content_server.static_content WHERE content_id=?";

}
