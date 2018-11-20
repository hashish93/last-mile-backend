CREATE TABLE lastmile_request.request_history
(
  request_history_id bigint NOT NULL,
  package_id bigint NOT NULL,
  request_status character varying(50),
  created timestamp with time zone DEFAULT now(),
  request_id bigint NOT NULL,
  pickup_request_type character varying(50),
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT request_history_pkey PRIMARY KEY (request_history_id)
)