CREATE TABLE pickup_time (
    pickup_time_id bigint NOT NULL,
    time_from character varying(50) NOT NULL,
    time_to character varying(50) NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    version bigint DEFAULT 0 NOT NULL
);