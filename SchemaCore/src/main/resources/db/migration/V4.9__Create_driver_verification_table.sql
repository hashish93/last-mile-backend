CREATE TABLE lastmile_core.driver_verification
(
  id bigint NOT NULL,
  is_verified boolean DEFAULT false,
  verification_code character varying(10),
  CONSTRAINT driver_verification_pk PRIMARY KEY (id)
);