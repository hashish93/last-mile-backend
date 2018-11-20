CREATE TABLE lastmile_static_content_server.static_content
(
  content_id bigint NOT NULL,
  contentname character varying(100) NOT NULL,
  contentpath character varying(100) NOT NULL,
  httpcontenttype character varying(100) NOT NULL,
  extension character varying(50) NOT NULL,
  server_id character varying(50) NOT NULL,
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT static_content_pkey PRIMARY KEY (content_id)
);