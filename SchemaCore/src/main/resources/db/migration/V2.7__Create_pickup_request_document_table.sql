CREATE TABLE lastmile_request.pickup_request_document
(
  pickup_request_id bigint NOT NULL,
  customer_image_id bigint NOT NULL,
  creditcard_image_id bigint,
  barcode character varying(100),
  CONSTRAINT pickup_request_document_pkey PRIMARY KEY (pickup_request_id),


  CONSTRAINT document_pickup_fk FOREIGN KEY (pickup_request_id)
      REFERENCES lastmile_request.pickup_request (pickup_request_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

 CONSTRAINT document_id_fk FOREIGN KEY (customer_image_id)
      REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,



 CONSTRAINT document_creditcard_fk FOREIGN KEY (creditcard_image_id)
      REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);