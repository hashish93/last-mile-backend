CREATE TABLE lastmile_core.user_email_verification
(
  id                BIGINT                NOT NULL,
  email             CHARACTER VARYING(50) NOT NULL,
  is_verified       BOOLEAN DEFAULT FALSE,
  verification_code CHARACTER VARYING(50),
  CONSTRAINT user_email_verification_pk PRIMARY KEY (id),
  CONSTRAINT user_email_ver_fk FOREIGN KEY (id)
  REFERENCES lastmile_authorization_server.users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);