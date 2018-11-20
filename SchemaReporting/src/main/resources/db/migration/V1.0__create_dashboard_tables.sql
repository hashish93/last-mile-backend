CREATE TABLE reporting.request_creation_in_duration
(
  id             BIGINT NOT NULL,
  request_id     BIGINT,
  request_type   CHARACTER VARYING(100),
  pickup_type_id BIGINT,
  hub_id         BIGINT,
  request_date   TIMESTAMP WITH TIME ZONE,
  CONSTRAINT wrequest_pk PRIMARY KEY (id)
);

CREATE TABLE reporting.pickup_request_status_in_duration
(
  id                  BIGSERIAL NOT NULL,
  request_id          BIGINT,
  pickup_type_id      BIGINT,
  hub_id              BIGINT,
  request_date        TIMESTAMP WITH TIME ZONE,
  request_status      CHARACTER VARYING(100),
  cancellation_reason CHARACTER VARYING(50),
  CONSTRAINT id PRIMARY KEY (id)
);

CREATE TABLE reporting.pickup_request_state_change_in_duration
(
  id                    BIGINT NOT NULL,
  pickup_request_id     BIGINT,
  pickup_type_id        BIGINT,
  hub_id                BIGINT,
  pickup_request_status CHARACTER VARYING(80),
  request_date          TIMESTAMP WITH TIME ZONE,
  CONSTRAINT prequest_pk PRIMARY KEY (id)
);


CREATE TABLE reporting.reject_ondemad_request
(
  id             BIGINT NOT NULL,
  package_id     BIGINT,
  request_id     BIGINT,
  request_status CHARACTER VARYING,
  reason         CHARACTER VARYING,
  created        TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  hub_id         BIGINT,
  CONSTRAINT "reject_ondemad_request_PK" PRIMARY KEY (id)
);

CREATE TABLE reporting.driver_response_ondemad_request
(
  id             BIGINT NOT NULL,
  package_id     BIGINT,
  request_id     BIGINT,
  request_status CHARACTER VARYING,
  created        TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  hub_id         BIGINT,
  CONSTRAINT "driver_Response_Pickup_Request_pkey" PRIMARY KEY (id)
);


CREATE INDEX pickup_date_range_index
  ON reporting.pickup_request_state_change_in_duration (request_date);

CREATE INDEX pickup_hub_id_index
  ON reporting.pickup_request_state_change_in_duration (hub_id);


CREATE TABLE reporting.pickup_request_per_customer_age_in_duration
(
  id                 BIGINT NOT NULL,
  pickup_request_id  BIGINT,
  pickup_type_id     BIGINT,
  hub_id             BIGINT,
  customer_id        BIGINT,
  customer_birthdate TIMESTAMP WITH TIME ZONE,
  customer_age       BIGINT,
  request_date       TIMESTAMP WITH TIME ZONE,
  CONSTRAINT pcrequest_pk PRIMARY KEY (id)
);

CREATE INDEX pickup_customer_date_range_index
  ON reporting.pickup_request_per_customer_age_in_duration (request_date);

CREATE INDEX pickup_customer_hub_id_index
  ON reporting.pickup_request_per_customer_age_in_duration (hub_id);

CREATE INDEX pickup_customer_birthdate_index
  ON reporting.pickup_request_per_customer_age_in_duration (customer_birthdate);

CREATE INDEX pickup_customer_age_index
  ON reporting.pickup_request_per_customer_age_in_duration (customer_age);

