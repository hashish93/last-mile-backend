CREATE TABLE lastmile_core.drive_in_city
(
  city_id BIGINT NOT NULL,
  CONSTRAINT drive_id_city_pk PRIMARY KEY (city_id),
  CONSTRAINT city_drive_in_city_fk FOREIGN KEY (city_id)
  REFERENCES lastmile_core.city (city_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);