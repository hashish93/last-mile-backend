--CREATE TABLE lastmile_core.covered_cities
--(
--    id serial NOT NULL,
--  city_id bigint NOT NULL,
--  CONSTRAINT covered_cities_pkey PRIMARY KEY (id),
--  CONSTRAINT covered_cities_city_id_fkey FOREIGN KEY (city_id)
--      REFERENCES lastmile_core.city (city_id) MATCH SIMPLE
--      ON UPDATE NO ACTION ON DELETE NO ACTION
--);


--create view lastmile_core.covered_cities_view as
--select cities.* from lastmile_core.city cities inner join lastmile_core.drive_in_city drive_in_city on cities.city_id = drive_in_city.city_id ;