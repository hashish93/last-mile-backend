CREATE TABLE reporting.sync_engine
(
  id bigserial NOT NULL,
  module_name character varying,
  time_interval bigint,
  last_sync_time timestamp with time zone,
  deleted boolean DEFAULT false,
  CONSTRAINT sync_engine_pkey PRIMARY KEY (id)
);

INSERT INTO reporting.sync_engine VALUES (1, 'PACKAGE_TYPE', 3600, null, false);
INSERT INTO reporting.sync_engine VALUES (2, 'CUSTOMER_AGE', 3600, null, false);
INSERT INTO reporting.sync_engine VALUES (3, 'REQUEST_TYPE', 3600, null, false);
INSERT INTO reporting.sync_engine VALUES (4, 'GO_EXTRA', 3600, null, false);
INSERT INTO reporting.sync_engine VALUES (5, 'PICKUP_STATISTICS', 3600, null, false);
INSERT INTO reporting.sync_engine VALUES (6, 'DRIVER_RATING', 3600, null, false);


CREATE TABLE reporting.customer_age_details
(
  id bigserial NOT NULL,
  customer_id bigint,
  customer_age bigint,
  created timestamp with time zone DEFAULT now(),
  CONSTRAINT customer_age_details_pkey PRIMARY KEY (id)
);


CREATE TABLE reporting.customer_age_summery
(
  id bigserial NOT NULL,
  -- within_period character varying,
  age_between_21_and_30 bigint DEFAULT 0,
  age_less_than_21 bigint DEFAULT 0,
  age_between_31_and_45 bigint DEFAULT 0,
  age_above_45 bigint DEFAULT 0,
  others bigint DEFAULT 0,
  CONSTRAINT customer_age_summery_pkey PRIMARY KEY (id)
);


CREATE TABLE reporting.driver_rating_details
(
  id bigserial NOT NULL,
  driver_id bigint,
  driver_type character varying,
  driver_rating numeric DEFAULT 0,
  hub_id bigint,
  CONSTRAINT driver_rating_details_pkey PRIMARY KEY (id)
);

CREATE TABLE reporting.driver_rating_summery
(
  id bigserial NOT NULL,
  driver_type character varying,
  one_star bigint DEFAULT 0,
  two_star bigint DEFAULT 0,
  three_star bigint DEFAULT 0,
  four_star bigint DEFAULT 0,
  five_star bigint DEFAULT 0,
  hub_id bigint,
  CONSTRAINT driver_rating_summery_pkey PRIMARY KEY (id)
);

CREATE TABLE reporting.go_extra_details
(
  id bigserial NOT NULL,
  hub_id bigint,
  request_id bigint,
  request_status character varying,
  created timestamp with time zone,
  CONSTRAINT go_extra_details_pkey PRIMARY KEY (id)
);

CREATE TABLE reporting.go_extra_summery
(
  id bigserial NOT NULL,
  hub_id bigint DEFAULT 0,
  reject_count bigint DEFAULT 0,
  acknowledge_count bigint DEFAULT 0,
  canceled_count bigint DEFAULT 0,
  with_period character varying,
  CONSTRAINT go_extra_summery_pkey PRIMARY KEY (id)
);

CREATE TABLE reporting.package_type_details
(
  id bigserial NOT NULL,
  package_id bigint,
  package_value bigint,
  package_type_id bigint,
  hub_id bigint,
  created timestamp with time zone,
  CONSTRAINT package_type_details_pkey PRIMARY KEY (id)
);

CREATE TABLE reporting.package_type_summary
(
  id bigserial NOT NULL,
  hub_id bigint,
  package_type_id bigint,
  package_less_5_kg bigint DEFAULT 0,
  package_less_10_kg bigint DEFAULT 0,
  package_less_25_kg bigint DEFAULT 0,
  within_period character varying,
  CONSTRAINT package_type_summary_pkey PRIMARY KEY (id)
);


CREATE TABLE reporting.pickup_statistics_details
(
  id bigserial NOT NULL,
  hub_id bigint,
  request_id bigint,
  request_type character varying,
  request_status character varying,
  created timestamp with time zone,
  CONSTRAINT pickup_statistics_details_pkey PRIMARY KEY (id)
);


CREATE TABLE reporting.pickup_statistics_summery
(
  id bigserial NOT NULL,
  hub_id bigint,
  request_type character varying,
  pickedup_count bigint DEFAULT 0,
  canceled_count bigint DEFAULT 0,
  no_capacity_count bigint DEFAULT 0,
  no_coverage_count bigint DEFAULT 0,
  within_period character varying,
  CONSTRAINT pickup_statistics_summery_pkey PRIMARY KEY (id)
);


CREATE TABLE reporting.request_type_details
(
  id bigserial NOT NULL,
  hub_id bigint,
  request_id bigint,
  request_type character varying,
  created timestamp with time zone DEFAULT now(),
  CONSTRAINT request_type_details_pkey PRIMARY KEY (id)
);


CREATE TABLE reporting.request_type_summery
(
  id bigserial NOT NULL,
  hub_id bigint,
  on_demand_count bigint DEFAULT 0,
  scheduled_count bigint DEFAULT 0,
  delivery_count bigint DEFAULT 0,
  return_count bigint DEFAULT 0,
  within_period character varying,
  CONSTRAINT request_type_summery_pkey PRIMARY KEY (id)
);