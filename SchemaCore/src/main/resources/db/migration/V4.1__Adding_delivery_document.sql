CREATE TABLE lastmile_request.delivery_request_document
(
  delivery_request_id BIGINT NOT NULL,
  customer_image_id   BIGINT NOT NULL,
  creditcard_image_id BIGINT,
  barcode             CHARACTER VARYING(100),
  CONSTRAINT delivery_request_document_pkey PRIMARY KEY (delivery_request_id),
  CONSTRAINT document_creditcard_fk FOREIGN KEY (creditcard_image_id)
  REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT document_id_fk FOREIGN KEY (customer_image_id)
  REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT document_delivery_fk FOREIGN KEY (delivery_request_id)
  REFERENCES lastmile_request.delivery_request (delivery_request_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);