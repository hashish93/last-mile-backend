CREATE TABLE lastmile_core.faq_language
(
  id serial NOT NULL,
  code text NOT NULL,
  name text,
  CONSTRAINT faq_language_pkey PRIMARY KEY (id)
);


CREATE TABLE lastmile_core.faq
(
  id serial NOT NULL,
  question text NOT NULL,
  answer text NOT NULL,
  language_id bigint,
  language character varying(100),
  CONSTRAINT pk PRIMARY KEY (id)
);



INSERT INTO lastmile_core.faq_language(code, name)
    VALUES ('ar', 'Arabic');
    INSERT INTO lastmile_core.faq_language(code, name)
    VALUES ('en', 'English');
    INSERT INTO lastmile_core.faq_language(code, name)
    VALUES ('fr', 'French');



