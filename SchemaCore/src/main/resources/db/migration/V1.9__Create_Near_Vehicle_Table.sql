CREATE TABLE lastmile_request.nearby_vehicle
(
 id bigint NOT NULL,
 package_id bigint,
 request_id bigint,
 active_vehicle_id bigint,
 response boolean,
 created timestamp with time zone DEFAULT now(),
 updated timestamp with time zone DEFAULT now(),
 CONSTRAINT nearby_vehicle_pkey PRIMARY KEY (id)
);