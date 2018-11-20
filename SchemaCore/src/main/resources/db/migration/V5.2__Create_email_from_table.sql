CREATE TABLE lastmile_core.email_from
(
  id serial NOT NULL,
  email character varying(1000),
  created timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT email_from_pkey PRIMARY KEY (id)
);



INSERT INTO lastmile_core.email_from(id, email)VALUES (1, 'lastmilesupport@appzone');