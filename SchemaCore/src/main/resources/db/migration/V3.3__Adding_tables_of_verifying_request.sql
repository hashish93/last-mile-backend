CREATE TABLE lastmile_core.verified_package
(
  package_id      BIGINT                   NOT NULL,
  package_type_id BIGINT                   NOT NULL,
  package_value   CHARACTER VARYING        NOT NULL,
  actualweight    NUMERIC(19, 2)           NOT NULL,
  description     CHARACTER VARYING(500),
  created         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version         BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_verified_package PRIMARY KEY (package_id),
  CONSTRAINT package_fk FOREIGN KEY (package_id)
  REFERENCES lastmile_core."package" (package_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT vpackage_type_fk FOREIGN KEY (package_type_id)
  REFERENCES lastmile_core.package_type (package_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE lastmile_core.verified_package_images
(
  package_id bigint NOT NULL,
  image_id bigint NOT NULL,
  version bigint NOT NULL DEFAULT 0,
  id bigserial NOT NULL,
  CONSTRAINT verified_package_images_pkey PRIMARY KEY (id),
  CONSTRAINT images_content_fk FOREIGN KEY (image_id)
      REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT images_verified_package_fk FOREIGN KEY (package_id)
      REFERENCES lastmile_core.verified_package (package_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE lastmile_request.verified_pickup_request
(
  pickup_request_id  BIGINT NOT NULL,
  additionalservices CHARACTER VARYING(200),
  labelingtext       CHARACTER VARYING(250),
  paymenttype        CHARACTER VARYING(100),
  paymentmethod      CHARACTER VARYING(100),
  version            BIGINT NOT NULL          DEFAULT 0,
  created            TIMESTAMP WITH TIME ZONE DEFAULT now(),
  CONSTRAINT verified_pickup_request_pk PRIMARY KEY (pickup_request_id),
  CONSTRAINT vpickup_pickuprequest_fk FOREIGN KEY (pickup_request_id)
  REFERENCES lastmile_request.pickup_request (pickup_request_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

