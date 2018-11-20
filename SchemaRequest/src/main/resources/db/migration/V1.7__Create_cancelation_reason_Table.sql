CREATE TABLE lastmile_request.cancelation_reason
(
  id bigint NOT NULL,
  reason character varying(200) NOT NULL,
  created timestamp with time zone DEFAULT now(),
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT cancelation_reason_pk PRIMARY KEY (id)
)