/*
 * Create Schema lastmile_static_content_server Tables
 */



CREATE TABLE lastmile_static_content_server.static_content
(
  content_id      BIGINT                 NOT NULL,
  contentname     CHARACTER VARYING(250) NOT NULL,
  contentpath     CHARACTER VARYING(250) NOT NULL,
  httpcontenttype CHARACTER VARYING(100) NOT NULL,
  extension       CHARACTER VARYING(50)  NOT NULL,
  server_id       CHARACTER VARYING(50)  NOT NULL,
  version         BIGINT                 NOT NULL DEFAULT 0,
  CONSTRAINT static_content_pkey PRIMARY KEY (content_id)
);


/*
 * Create Schema lastmile_authorization_server Tables
 */

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.building_type
(
  building_type_id BIGINT                   NOT NULL,
  type             CHARACTER VARYING(50)    NOT NULL,
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version          BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_building_type PRIMARY KEY (building_type_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.building
(
  building_id      BIGINT                   NOT NULL,
  buildingname     CHARACTER VARYING(100)   NOT NULL,
  buildingnumber   CHARACTER VARYING(50),
  country_code_id  BIGINT,
  phone_number     CHARACTER VARYING(50),
  country_id       BIGINT                   NOT NULL,
  city_id          BIGINT                   NOT NULL,
  area             CHARACTER VARYING(100),
  street           CHARACTER VARYING(100),
  waselcode        CHARACTER VARYING(100),
  longitude        CHARACTER VARYING,
  latitude         CHARACTER VARYING,
  building_type_id BIGINT                   NOT NULL,
  description      CHARACTER VARYING(500),
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  status           CHARACTER VARYING(50)    NOT NULL DEFAULT 'ACTIVE' :: CHARACTER VARYING,
  version          BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT building_pk PRIMARY KEY (building_id),
  CONSTRAINT building_city_fk FOREIGN KEY (city_id)
  REFERENCES lastmile_core.city (city_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT building_country_fk FOREIGN KEY (country_id)
  REFERENCES lastmile_core.country (country_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT building_country_code_fk FOREIGN KEY (country_code_id)
  REFERENCES lastmile_core.country_codes (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT building_type_fk FOREIGN KEY (building_type_id)
  REFERENCES lastmile_core.building_type (building_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

--------------------------------------------

CREATE TABLE lastmile_authorization_server.menu_item
(
  id        BIGINT NOT NULL,
  name      CHARACTER VARYING,
  key       CHARACTER VARYING,
  url       CHARACTER VARYING,
  active    BOOLEAN,
  parent    BIGINT,
  base      BOOLEAN,
  the_order BIGINT,
  CONSTRAINT menu_item_pkey PRIMARY KEY (id),
  CONSTRAINT menu_item_parent_fkey FOREIGN KEY (parent)
  REFERENCES lastmile_authorization_server.menu_item (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.module
(
  id      BIGINT                   NOT NULL,
  name    CHARACTER VARYING        NOT NULL,
  created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT module_pk PRIMARY KEY (id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.privilege
(
  id          BIGINT                   NOT NULL,
  name        CHARACTER VARYING        NOT NULL,
  displayname CHARACTER VARYING        NOT NULL,
  created     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version     BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT privilege_pk PRIMARY KEY (id)
);

---------------------------------------------------------------------------------------


CREATE TABLE lastmile_authorization_server.module_privilege
(
  id              BIGINT                 NOT NULL,
  privilege_id    BIGINT                 NOT NULL,
  module_id       BIGINT                 NOT NULL,
  identifier_name CHARACTER VARYING(100) NOT NULL DEFAULT now(),
  parent          BIGINT,
  default_value   BOOLEAN                         DEFAULT FALSE,
  created         TIMESTAMP WITH TIME ZONE,
  version         BIGINT                 NOT NULL DEFAULT 0,
  menu_id         BIGINT,
  CONSTRAINT module_privilege_pk PRIMARY KEY (id),
  CONSTRAINT module_privilege_module_id_fkey FOREIGN KEY (module_id)
  REFERENCES lastmile_authorization_server.module (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT module_privilege_parent_fkey FOREIGN KEY (parent)
  REFERENCES lastmile_authorization_server.module_privilege (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT module_privilege_privilege_id_fkey FOREIGN KEY (privilege_id)
  REFERENCES lastmile_authorization_server.privilege (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.user_type
(
  id bigint NOT NULL,
  name character varying,
  normal_user boolean DEFAULT true,
  admin_user boolean DEFAULT false,
  CONSTRAINT user_type_pkey PRIMARY KEY (id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.users
(
  user_id bigint NOT NULL,
  username character varying(100),
  password character varying(100) NOT NULL,
  firstname character varying(100) NOT NULL,
  lastname character varying(100) NOT NULL,
  country_code_id bigint NOT NULL,
  phone character varying(50) NOT NULL,
  email character varying(50) NOT NULL,
  enabled boolean NOT NULL DEFAULT true,
  created timestamp with time zone NOT NULL DEFAULT now(),
  personal_photo_id bigint,
  status character varying(50) NOT NULL DEFAULT 'ACTIVE'::character varying,
  description character varying(500),
  version bigint NOT NULL DEFAULT 0,
  user_type_id bigint,
  national_id character varying(100),
  CONSTRAINT users_pk PRIMARY KEY (user_id),
  CONSTRAINT users_country_code_id_fkey FOREIGN KEY (country_code_id)
      REFERENCES lastmile_core.country_codes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT users_user_type_id_fkey FOREIGN KEY (user_type_id)
      REFERENCES lastmile_authorization_server.user_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.roles
(
  role_id bigint NOT NULL,
  rolename character varying(100) NOT NULL,
  created timestamp with time zone NOT NULL DEFAULT now(),
  editable boolean NOT NULL DEFAULT false,
  description character varying(500),
  version bigint NOT NULL DEFAULT 0,
  enabled boolean,
  hub_id bigint,
  system_role boolean DEFAULT false,
  CONSTRAINT roles_pk PRIMARY KEY (role_id),
  CONSTRAINT roles_hub_id_fkey FOREIGN KEY (hub_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.users_roles
(
  role_id bigint NOT NULL,
  user_id bigint NOT NULL,
  created timestamp with time zone NOT NULL DEFAULT now(),
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT roles_fk FOREIGN KEY (role_id)
      REFERENCES lastmile_authorization_server.roles (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_fk FOREIGN KEY (user_id)
      REFERENCES lastmile_authorization_server.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.user_hub
(
  id bigint NOT NULL,
  user_id bigint,
  hub_id bigint,
  CONSTRAINT user_hub_pkey PRIMARY KEY (id),
  CONSTRAINT user_hub_hub_id_fkey FOREIGN KEY (hub_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_hub_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES lastmile_authorization_server.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.system_role
(
  id bigint NOT NULL,
  name character varying,
  CONSTRAINT system_role_pkey PRIMARY KEY (id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.system_role_privilege
(
  id bigint NOT NULL,
  system_role_id bigint,
  module_privilege_id bigint,
  CONSTRAINT system_role_privilege_pkey PRIMARY KEY (id),
  CONSTRAINT system_role_privilege_module_privilege_id_fkey FOREIGN KEY (module_privilege_id)
      REFERENCES lastmile_authorization_server.module_privilege (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT system_role_privilege_system_role_id_fkey FOREIGN KEY (system_role_id)
      REFERENCES lastmile_authorization_server.system_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


---------------------------------------------------------------------------------------

CREATE TABLE lastmile_authorization_server.role_privilege
(
  role_id             BIGINT  NOT NULL,
  module_privilege_id BIGINT  NOT NULL,
  enabled             BOOLEAN NOT NULL DEFAULT TRUE,
  version             BIGINT  NOT NULL DEFAULT 0,
  id                  BIGINT  NOT NULL,
  CONSTRAINT role_privilege_pkey PRIMARY KEY (id),
  CONSTRAINT role_privilege_module_privilege_id_fkey FOREIGN KEY (module_privilege_id)
  REFERENCES lastmile_authorization_server.module_privilege (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT role_privilege_role_id_fkey FOREIGN KEY (role_id)
  REFERENCES lastmile_authorization_server.roles (role_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);


/*
 * Create Schema lastmile_core Tables
 */




CREATE TABLE lastmile_core.work_shift (

  id bigint NOT NULL,
  date_from timestamp with time zone NOT NULL,
  date_to timestamp with time zone NOT NULL,
  created timestamp with time zone NOT NULL DEFAULT now(),
  version bigint NOT NULL DEFAULT 0,
  hub_id bigint,
  CONSTRAINT work_shift_pk PRIMARY KEY (id),
  CONSTRAINT work_shift_hub_id_fkey FOREIGN KEY (hub_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.calendar
(
  id      BIGINT                   NOT NULL,
  dayname CHARACTER VARYING(50)    NOT NULL,
  created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version BIGINT                   NOT NULL DEFAULT 0,
  status  CHARACTER VARYING(50),
  CONSTRAINT calendar_pk PRIMARY KEY (id)
);

---------------------------------------------------------------------------------------
CREATE TABLE lastmile_core.hub_calendar
(
  id bigserial NOT NULL,
  hub_id bigint,
  calendar_id bigint,
  status character varying DEFAULT 'working'::character varying,
  created date DEFAULT now(),
  version bigint DEFAULT 0,
  CONSTRAINT hub_calendar_pkey PRIMARY KEY (id),
  CONSTRAINT hub_calendar_calendar_id_fkey FOREIGN KEY (calendar_id)
      REFERENCES lastmile_core.calendar (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT hub_calendar_hub_id_fkey FOREIGN KEY (hub_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.device_brand
(
  id        BIGINT                 NOT NULL,
  brandname CHARACTER VARYING(500) NOT NULL,
  created   TIME WITH TIME ZONE    NOT NULL DEFAULT now(),
  CONSTRAINT device_brand_pkey PRIMARY KEY (id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.devices
(
  device_id bigint NOT NULL,
  brand_id bigint NOT NULL,
  model character varying(500) NOT NULL,
  imei_info character varying(500) NOT NULL,
  phonenumber character varying(500) NOT NULL,
  status character varying DEFAULT 'ACTIVE'::character varying,
  version bigint NOT NULL DEFAULT 0,
  created time with time zone DEFAULT now(),
  hub_id bigint,
  CONSTRAINT device_pk PRIMARY KEY (device_id),
  CONSTRAINT device_brand_id_fk FOREIGN KEY (brand_id)
      REFERENCES lastmile_core.device_brand (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT devices_hub_id_fkey FOREIGN KEY (hub_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.driving_license_type
(
  driving_license_type_id BIGINT                   NOT NULL,
  license_type            CHARACTER VARYING(50)    NOT NULL,
  created                 TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version                 BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT driving_license_type_id PRIMARY KEY (driving_license_type_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.driver
(
  id                       BIGINT NOT NULL,
  national_id              CHARACTER VARYING(50),
  driving_license_type_id  BIGINT,
  driving_license_exp_date DATE,
  driving_license_id       CHARACTER VARYING(50),
  rating                   NUMERIC DEFAULT 0,
  created timestamp with time zone DEFAULT now(),
  updated timestamp with time zone DEFAULT now(),
  CONSTRAINT driver_pk PRIMARY KEY (id),
  CONSTRAINT driver_license_type_fk FOREIGN KEY (driving_license_type_id)
  REFERENCES lastmile_core.driving_license_type (driving_license_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.package_type
(
  package_type_id  BIGINT                   NOT NULL,
  packagetype      CHARACTER VARYING(50)    NOT NULL,
  packagedimension CHARACTER VARYING(50)    NOT NULL,
  expectedweight   BIGINT                   NOT NULL,
  description      CHARACTER VARYING(500),
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version          BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_package_type PRIMARY KEY (package_type_id)
);

---------------------------------------------------------------------------------------


CREATE TABLE lastmile_core.shipment_service
(
  shipment_service_id BIGINT                   NOT NULL,
  service             CHARACTER VARYING(50)    NOT NULL,
  description         CHARACTER VARYING(500),
  created             TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version             BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_shipment_service PRIMARY KEY (shipment_service_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.shipment_service_type
(
  shipment_service_type_id BIGINT                   NOT NULL,
  type                     CHARACTER VARYING(50)    NOT NULL,
  shipment_service_id      BIGINT                   NOT NULL,
  description              CHARACTER VARYING(500),
  created                  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version                  BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_shipment_service_types PRIMARY KEY (shipment_service_type_id),
  CONSTRAINT shipment_service_fk FOREIGN KEY (shipment_service_id)
  REFERENCES lastmile_core.shipment_service (shipment_service_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core."package"
(
  package_id               BIGINT                   NOT NULL,
  nickname                 CHARACTER VARYING(50),
  package_type_id          BIGINT                   NOT NULL,
  package_value            CHARACTER VARYING        NOT NULL,
  actualweight             NUMERIC(19, 2)           NOT NULL,
  shipment_service_type_id BIGINT,
  status                   CHARACTER VARYING(50),
  description              CHARACTER VARYING(500),
  created                  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version                  BIGINT                   NOT NULL DEFAULT 0,
  updated timestamp with time zone DEFAULT now(),
  CONSTRAINT pk_package PRIMARY KEY (package_id),
  CONSTRAINT package_service_fk FOREIGN KEY (shipment_service_type_id)
  REFERENCES lastmile_core.shipment_service_type (shipment_service_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT package_type_fk FOREIGN KEY (package_type_id)
  REFERENCES lastmile_core.package_type (package_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.package_label
(
  package_label_id BIGINT                   NOT NULL,
  label            CHARACTER VARYING(50)    NOT NULL,
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version          BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_package_label PRIMARY KEY (package_label_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.package_labeling
(
  package_id bigint NOT NULL,
  package_label_id bigint NOT NULL,
  version bigint NOT NULL DEFAULT 0,
  id bigserial NOT NULL,
  CONSTRAINT package_labeling_pkey PRIMARY KEY (id),
  CONSTRAINT labeling_label_fk FOREIGN KEY (package_label_id)
      REFERENCES lastmile_core.package_label (package_label_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT package_label_fk FOREIGN KEY (package_id)
      REFERENCES lastmile_core."package" (package_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.package_images
(
  package_id bigint NOT NULL,
  image_id bigint NOT NULL,
  version bigint NOT NULL DEFAULT 0,
  id bigserial NOT NULL,
  CONSTRAINT package_images_pkey PRIMARY KEY (id),
  CONSTRAINT images_content_fk FOREIGN KEY (image_id)
      REFERENCES lastmile_static_content_server.static_content (content_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT images_package_fk FOREIGN KEY (package_id)
      REFERENCES lastmile_core."package" (package_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.sizes_config
(
  size_id        BIGINT NOT NULL,
  sizename       CHARACTER VARYING(50),
  width          NUMERIC,
  height         NUMERIC,
  length         NUMERIC,
  correspondence NUMERIC,
  status         CHARACTER VARYING        DEFAULT 'ACTIVE' :: CHARACTER VARYING,
  created        TIMESTAMP WITH TIME ZONE DEFAULT now(),
  version        BIGINT NOT NULL          DEFAULT 0,
  CONSTRAINT size_config_pkey PRIMARY KEY (size_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.system_config
(
  config_id   BIGINT                   NOT NULL,
  value       NUMERIC,
  text_value  CHARACTER VARYING(100),
  displayname CHARACTER VARYING(50),
  description CHARACTER VARYING(250),
  status      CHARACTER VARYING(50)    NOT NULL DEFAULT 'ACTIVE' :: CHARACTER VARYING,
  created     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version     BIGINT                   NOT NULL DEFAULT 0,
  unit        CHARACTER VARYING,
  super_config boolean DEFAULT false,
  config_type character varying(100),
  CONSTRAINT system_config_pkey PRIMARY KEY (config_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.hub_configuration
(
  id bigserial NOT NULL,
  system_config_id bigint,
  hub_id bigint,
  value numeric,
  text_value character varying(100),
  config_type character varying(100),
  created date DEFAULT now(),
  CONSTRAINT hub_configuration_pkey PRIMARY KEY (id),
  CONSTRAINT hub_configuration_hub_id_fkey FOREIGN KEY (hub_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT hub_configuration_system_config_id_fkey FOREIGN KEY (system_config_id)
      REFERENCES lastmile_core.system_config (config_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.customer
(
  customer_id     BIGINT NOT NULL,
  country_id      BIGINT,
  city_id         BIGINT,
  gender          CHARACTER VARYING(50),
  birthdate       DATE,
  country_code_id BIGINT NOT NULL,
  updated timestamp with time zone DEFAULT now(),
  CONSTRAINT customer_pk PRIMARY KEY (customer_id),
  CONSTRAINT customer_city_fk FOREIGN KEY (city_id)
  REFERENCES lastmile_core.city (city_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT customer_country_fk FOREIGN KEY (country_id)
  REFERENCES lastmile_core.country (country_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT customer_country_code_fk FOREIGN KEY (country_code_id)
  REFERENCES lastmile_core.country_codes (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------


CREATE TABLE lastmile_core.building_serving_area
(
  building_serving_id BIGINT NOT NULL,
  building_id         BIGINT,
  latitude            CHARACTER VARYING,
  longitude           CHARACTER VARYING,
  created             TIME WITH TIME ZONE DEFAULT now(),
  CONSTRAINT building_serving_area_pkey PRIMARY KEY (building_serving_id),
  CONSTRAINT building_serving_area_building_id_fkey FOREIGN KEY (building_id)
  REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.vehicle_type
(
  vehicle_type_id BIGINT                   NOT NULL,
  type            CHARACTER VARYING(50)    NOT NULL,
  type_ar          CHARACTER VARYING(50)  ,
  created         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version         BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_vehicle_type PRIMARY KEY (vehicle_type_id)
);

---------------------------------------------------------------------------------------


CREATE TABLE lastmile_core.vehicle
(
  vehicle_id bigint NOT NULL,
  vehicle_type_id bigint NOT NULL,
  brand character varying(50) NOT NULL,
  model character varying(50) NOT NULL,
  color character varying(50),
  plate character varying(50) NOT NULL,
  chassis character varying(100) NOT NULL,
  weight numeric(19,2) NOT NULL,
  purpose character varying(50),
  motor character varying(50),
  building_id bigint NOT NULL,
  status character varying(50) NOT NULL DEFAULT 'ACTIVE'::character varying,
  description character varying(500),
  created timestamp with time zone NOT NULL DEFAULT now(),
  version bigint NOT NULL DEFAULT 0,
  CONSTRAINT pk_vehicle PRIMARY KEY (vehicle_id),
  CONSTRAINT vehicle_building_fk FOREIGN KEY (building_id)
      REFERENCES lastmile_core.building (building_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT vehicle_type_fk FOREIGN KEY (vehicle_type_id)
      REFERENCES lastmile_core.vehicle_type (vehicle_type_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_core.active_vehicle
(
  id            BIGINT  NOT NULL,
  vehicle_id    BIGINT  NOT NULL,
  driver_id     BIGINT  NOT NULL,
  device_id     BIGINT  NOT NULL,
  work_shift_id BIGINT  NOT NULL,
  active        BOOLEAN NOT NULL         DEFAULT TRUE,
  version       BIGINT  NOT NULL         DEFAULT 0,
  created       TIMESTAMP WITH TIME ZONE DEFAULT now(),
  CONSTRAINT active_vehicle_pk PRIMARY KEY (id),
  CONSTRAINT active_vehicle_device_fk FOREIGN KEY (device_id)
  REFERENCES lastmile_core.devices (device_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT active_vehicle_driver_fk FOREIGN KEY (driver_id)
  REFERENCES lastmile_core.driver (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT active_vehicle_vehicle_fk FOREIGN KEY (vehicle_id)
  REFERENCES lastmile_core.vehicle (vehicle_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT active_vehicle_work_shift_fk FOREIGN KEY (work_shift_id)
  REFERENCES lastmile_core.work_shift (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------


/*
 * Create Schema lastmile_request Tables
 */


CREATE TABLE lastmile_request.pickup_request_type
(
  pickup_request_type_id BIGINT                NOT NULL,
  type                   CHARACTER VARYING(50) NOT NULL,
  created                TIMESTAMP WITH TIME ZONE       DEFAULT now(),
  version                BIGINT                NOT NULL DEFAULT 0,
  CONSTRAINT pickup_request_type_pkey PRIMARY KEY (pickup_request_type_id)
);

---------------------------------------------------------------------------------------
CREATE TABLE lastmile_request.pickup_service_type
(
  pickup_service_type_id BIGINT                NOT NULL,
  type                   CHARACTER VARYING(50) NOT NULL,
  created                TIMESTAMP WITH TIME ZONE       DEFAULT now(),
  version                BIGINT                NOT NULL DEFAULT 0,
  CONSTRAINT pickup_service_type_pk PRIMARY KEY (pickup_service_type_id)
);

---------------------------------------------------------------------------------------


CREATE TABLE lastmile_request.request_history
(
  request_history_id  BIGINT NOT NULL,
  package_id          BIGINT NOT NULL,
  request_status      CHARACTER VARYING(50),
  created             TIMESTAMP WITH TIME ZONE DEFAULT now(),
  request_id          BIGINT NOT NULL,
  pickup_request_type CHARACTER VARYING(50),
  version             BIGINT NOT NULL          DEFAULT 0,
  CONSTRAINT request_history_pkey PRIMARY KEY (request_history_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_request.pickup_time
(
  pickup_time_id BIGINT                   NOT NULL,
  time_from      CHARACTER VARYING(50)    NOT NULL,
  time_to        CHARACTER VARYING(50)    NOT NULL,
  created        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version        BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT pk_pickup_time PRIMARY KEY (pickup_time_id)
);

---------------------------------------------------------------------------------------

CREATE TABLE lastmile_request.pickup_request
(
  pickup_request_id        BIGINT NOT NULL,
  pickup_request_type_id   BIGINT NOT NULL,
  requesttime              TIMESTAMP WITH TIME ZONE DEFAULT now(),
  requester_id             BIGINT NOT NULL,
  pickuplongitude          CHARACTER VARYING(50),
  pickuplatitude           CHARACTER VARYING(50),
  hub_id                   BIGINT,
  pickupwasellocation      CHARACTER VARYING(200),
  pickupformatedaddress    CHARACTER VARYING(250),
  time_from                CHARACTER VARYING(50),
  time_to                  CHARACTER VARYING(50),
  pickupdate               DATE                     DEFAULT now(),
  recipient_id             BIGINT,
  recipientname            CHARACTER VARYING(50),
  recipientmobile          CHARACTER VARYING(20),
  recipientlongitude       CHARACTER VARYING(50),
  recipientlatitude        CHARACTER VARYING(50),
  recipientwasellocation   CHARACTER VARYING(200),
  recipientformatedaddress CHARACTER VARYING(250),
  recipientadditionalinfo  CHARACTER VARYING(250),
  pickup_service_type_id   BIGINT,
  additionalservices       CHARACTER VARYING(200),
  labelingtext             CHARACTER VARYING(250),
  paymenttype              CHARACTER VARYING(100),
  paymentmethod            CHARACTER VARYING(100),
  description              CHARACTER VARYING(250),
  version                  BIGINT NOT NULL          DEFAULT 0,
  created                  TIMESTAMP WITH TIME ZONE DEFAULT now(),
  request_status           CHARACTER VARYING(50),
  countrycode              CHARACTER VARYING(50),
  cancellation_reason      CHARACTER VARYING(50),
  updated timestamp with time zone DEFAULT now(),
  CONSTRAINT pickup_request_pk PRIMARY KEY (pickup_request_id),
  CONSTRAINT pickup_receipient_fk FOREIGN KEY (recipient_id)
  REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pickup_sender_fk FOREIGN KEY (requester_id)
  REFERENCES lastmile_core.customer (customer_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pickup_type_fk FOREIGN KEY (pickup_request_type_id)
  REFERENCES lastmile_request.pickup_request_type (pickup_request_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------------




