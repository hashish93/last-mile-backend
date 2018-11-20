CREATE TABLE lastmile_request.pickup_request_type
(
  pickup_request_type_id bigint NOT NULL,
  type character varying(50) NOT NULL,
  created timestamp with time zone DEFAULT now(),
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT pickup_request_type_pkey PRIMARY KEY (pickup_request_type_id)
);