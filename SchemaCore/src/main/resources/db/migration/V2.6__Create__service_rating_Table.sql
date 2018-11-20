CREATE TABLE lastmile_request.service_rating
(
  service_rating_id bigint NOT NULL,
  request_id bigint,
  request_type character varying(100),
  rating numeric,
  created timestamp with time zone DEFAULT now(),
  CONSTRAINT service_rating_pkey PRIMARY KEY (service_rating_id)
);



CREATE TABLE lastmile_request.driver_rating
(
  driver_rating_id bigint NOT NULL,
  driver_id bigint,
  request_id bigint,
  request_type character varying(100),
  rating numeric DEFAULT 0,
  created timestamp with time zone DEFAULT now(),
  CONSTRAINT driver_rating_pkey PRIMARY KEY (driver_rating_id)
);