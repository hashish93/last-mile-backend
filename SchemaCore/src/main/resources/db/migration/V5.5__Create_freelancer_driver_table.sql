CREATE TABLE lastmile_core.freelancer_driver
(
  id                       BIGINT                NOT NULL,
  national_id              CHARACTER VARYING(50),
  driving_license_type_id  BIGINT,
  driving_license_exp_date DATE,
  driving_license_id       CHARACTER VARYING(50),
  rating                   NUMERIC                        DEFAULT 0,
  amount                   BIGINT                ,
  --   country_id               BIGINT                NOT NULL,
  city_id                  BIGINT                ,
  image_id                 BIGINT                ,
  vehicle_type_id          BIGINT,
  brand                    BIGINT ,
  model                    BIGINT ,
  color                    CHARACTER VARYING(50),
  plate                    CHARACTER VARYING(50),
  chassis                  CHARACTER VARYING(100),
  weight                   NUMERIC(19, 2),
  purpose                  CHARACTER VARYING(50),
  motor                    CHARACTER VARYING(50),
  model_year               CHARACTER VARYING(50),
  building_id              BIGINT,
  status                   CHARACTER VARYING(50) NOT NULL DEFAULT 'NEW' :: CHARACTER VARYING,
  is_national_id_exit              BOOLEAN                        DEFAULT FALSE,
  is_vehicle_ownership_id_exist              BOOLEAN                        DEFAULT FALSE,
  is_criminalrecord_exist              BOOLEAN                        DEFAULT FALSE,
  is_birthcertificate_exist              BOOLEAN                        DEFAULT FALSE,
  is_approved              BOOLEAN                        DEFAULT FALSE,
  description              CHARACTER VARYING(500),
  rejectionreasondescription  CHARACTER VARYING(500),
  created                  TIMESTAMP WITH TIME ZONE       DEFAULT now(),
  version                  BIGINT                         DEFAULT 0,
  updated timestamp with time zone DEFAULT now(),
  CONSTRAINT freelancer_driver_pk PRIMARY KEY (id),
  CONSTRAINT freelancer_building_fk FOREIGN KEY (building_id)
  REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT freelancer_city_fk FOREIGN KEY (city_id)
  REFERENCES lastmile_core.drive_in_city (city_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  --   CONSTRAINT freelancer_country_fk FOREIGN KEY (country_id)
  --   REFERENCES lastmile_core.country (country_id) MATCH SIMPLE
  --   ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT freelancer_image_fk FOREIGN KEY (image_id)
  REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT freelancer_license_type_fk FOREIGN KEY (driving_license_type_id)
  REFERENCES lastmile_core.driving_license_type (driving_license_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT freelancer_type_fk FOREIGN KEY (vehicle_type_id)
  REFERENCES lastmile_core.vehicle_type (vehicle_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);