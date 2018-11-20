CREATE TABLE lastmile_core.driver_forgot_password
(
  driver_id BIGINT                NOT NULL,
  token     CHARACTER VARYING(50) NOT NULL,
  attempts  INTEGER               NOT NULL,
  created   TIME WITH TIME ZONE   NOT NULL DEFAULT now(),
  CONSTRAINT driver_forgot_password_pk PRIMARY KEY (driver_id),
  CONSTRAINT user_forgot_fk FOREIGN KEY (driver_id)
  REFERENCES lastmile_authorization_server.users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);