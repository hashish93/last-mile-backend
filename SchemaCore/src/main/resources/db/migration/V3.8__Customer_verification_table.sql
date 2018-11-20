CREATE TABLE lastmile_core.customer_verification
(
  id bigint NOT NULL,
  is_verified boolean DEFAULT false,
  verification_code character varying(10),
  CONSTRAINT customer_verification_pk PRIMARY KEY (id),
  CONSTRAINT customer_ver_fk FOREIGN KEY (id)
  REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);