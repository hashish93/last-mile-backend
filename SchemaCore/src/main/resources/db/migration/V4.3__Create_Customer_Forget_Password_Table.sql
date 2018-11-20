CREATE TABLE lastmile_core.customer_forgot_password
(
  customer_id BIGINT                NOT NULL,
  token       CHARACTER VARYING(50) NOT NULL,
  attempts    INTEGER               NOT NULL,
  created     TIME WITH TIME ZONE   NOT NULL DEFAULT now(),
  CONSTRAINT customer_forgot_password_pk PRIMARY KEY (customer_id),
  CONSTRAINT user_forgot_fk FOREIGN KEY (customer_id)
  REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);