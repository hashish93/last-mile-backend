CREATE TABLE lastmile_authorization_server.forget_password
(
  id             BIGINT                 NOT NULL,
  email          CHARACTER VARYING(200) NOT NULL,
  code_generated CHARACTER VARYING(100) NOT NULL,
  user_id        BIGINT,
  created        TIMESTAMP WITH TIME ZONE DEFAULT now(),
  CONSTRAINT id PRIMARY KEY (id)
);