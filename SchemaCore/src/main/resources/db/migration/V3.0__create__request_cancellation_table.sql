CREATE TABLE lastmile_request.request_cancellation
(
  request_id bigint NOT NULL,
  reason_id bigint NOT NULL,
  description character varying(600),
  created timestamp with time zone DEFAULT now(),
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT request_cancellation_pkey PRIMARY KEY (request_id),

  CONSTRAINT request_cancel_cancel_fk FOREIGN KEY (reason_id)
      REFERENCES lastmile_request.cancelation_reason (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

  CONSTRAINT request_cancel_pickup_fk FOREIGN KEY (request_id)
      REFERENCES lastmile_request.pickup_request (pickup_request_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);