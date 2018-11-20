CREATE TABLE lastmile_core.contact_us
(
  id integer NOT NULL,
  language character varying(10) NOT NULL,
  hotlinenumber character varying(100) NOT NULL,
  hotlinetitle character varying(100),
  daily_working_hours_from character varying(20),
  daily_working_hours_to character varying(20),
  vacation_working_hours_from character varying(20),
  vacation_working_hours_to character varying(20),
  CONSTRAINT contact_us_pkey PRIMARY KEY (id)
);





CREATE TABLE lastmile_core.contact_us_email
(
  id bigint NOT NULL,
  contactus_id bigint NOT NULL,
  email_title character varying(100) NOT NULL,
  email_address character varying(100) NOT NULL,
  CONSTRAINT contact_us_email_pkey PRIMARY KEY (id),
  CONSTRAINT contact_us_email_id_fkey FOREIGN KEY (contactus_id)
      REFERENCES lastmile_core.contact_us (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


