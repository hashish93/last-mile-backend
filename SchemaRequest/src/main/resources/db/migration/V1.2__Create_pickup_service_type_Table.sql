CREATE TABLE pickup_service_type (
    pickup_service_type_id bigint NOT NULL,
    type character varying(50) NOT NULL,
    created timestamp with time zone DEFAULT now(),
    version bigint DEFAULT 0 NOT NULL
);