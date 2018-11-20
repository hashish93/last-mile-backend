CREATE TABLE lastmile_request.delivery_request
(
  delivery_request_id bigint NOT NULL,
  requesttime timestamp with time zone DEFAULT now(),
  requester_id bigint NOT NULL,
  pickuplongitude character varying(50),
  pickuplatitude character varying(50),
  hub_id bigint,
  pickupwasellocation character varying(200),
  pickupformatedaddress character varying(250),
  pickupdate date,
  recipient_id bigint,
  recipientname character varying(50),
  recipientmobile character varying(20),
  recipientlongitude character varying(50),
  recipientlatitude character varying(50),
  recipientwasellocation character varying(200),
  recipientformatedaddress character varying(250),
  recipientadditionalinfo character varying(250),
  additionalservices character varying(200),
  time_from character varying(50),
  time_to character varying(50),
  deliverydate date,
  labelingtext character varying(250),
  paymenttype character varying(100),
  paymentmethod character varying(100),
  description character varying(250),
  confirmationcode character varying(50),
  countrycode character varying(50),
  version bigint NOT NULL DEFAULT 0,
  created timestamp with time zone DEFAULT now(),
  updated timestamp with time zone DEFAULT now(),
  request_status character varying(50),
  CONSTRAINT delivery_request_pk PRIMARY KEY (delivery_request_id),
  CONSTRAINT delivery_receipient_fk FOREIGN KEY (recipient_id)
      REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT delivery_sender_fk FOREIGN KEY (requester_id)
      REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
