CREATE TABLE lastmile_request.out_of_coverage_request
(
  id bigserial NOT NULL,
  latitude character varying,
  longitude character varying,
  created timestamp with time zone DEFAULT now(),
  type character varying,
  CONSTRAINT out_of_coverage_request_pkey PRIMARY KEY (id)
)