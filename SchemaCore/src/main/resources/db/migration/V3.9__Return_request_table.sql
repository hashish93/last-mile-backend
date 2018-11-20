CREATE TABLE lastmile_request.return_request
(
  return_request_id        BIGINT NOT NULL,
  requesttime              TIMESTAMP WITH TIME ZONE DEFAULT now(),
  requester_id             BIGINT NOT NULL,
  pickuplongitude          CHARACTER VARYING(50),
  pickuplatitude           CHARACTER VARYING(50),
  hub_id                   BIGINT,
  pickupwasellocation      CHARACTER VARYING(200),
  pickupformatedaddress    CHARACTER VARYING(250),
  pickupdate               DATE,
  recipient_id             BIGINT,
  recipientname            CHARACTER VARYING(50),
  recipientmobile          CHARACTER VARYING(20),
  recipientlongitude       CHARACTER VARYING(50),
  recipientlatitude        CHARACTER VARYING(50),
  recipientwasellocation   CHARACTER VARYING(200),
  recipientformatedaddress CHARACTER VARYING(250),
  recipientadditionalinfo  CHARACTER VARYING(250),
  additionalservices       CHARACTER VARYING(200),
  return_longitude         CHARACTER VARYING(50),
  return_latitude          CHARACTER VARYING(50),
  return_description       CHARACTER VARYING(250),
  time_from                CHARACTER VARYING(50),
  time_to                  CHARACTER VARYING(50),
  return_date              DATE,
  labelingtext             CHARACTER VARYING(250),
  paymenttype              CHARACTER VARYING(100),
  paymentmethod            CHARACTER VARYING(100),
  description              CHARACTER VARYING(250),
  version                  BIGINT NOT NULL          DEFAULT 0,
  created                  TIMESTAMP WITH TIME ZONE DEFAULT now(),
  request_status           CHARACTER VARYING(50),
  updated timestamp with time zone DEFAULT now(),
  CONSTRAINT return_request_pk PRIMARY KEY (return_request_id),
  CONSTRAINT return_receipient_fk FOREIGN KEY (recipient_id)
  REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT return_sender_fk FOREIGN KEY (requester_id)
  REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);