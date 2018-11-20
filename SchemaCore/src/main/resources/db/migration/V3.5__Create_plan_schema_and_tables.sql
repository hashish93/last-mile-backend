CREATE TABLE lastmile_plan.plan
(
  id      BIGINT NOT NULL,
  building_id        BIGINT                 NOT NULL,
  created TIMESTAMP WITH TIME ZONE DEFAULT now(),
  CONSTRAINT plan_pk PRIMARY KEY (id),
  CONSTRAINT plan_created_key UNIQUE (created)
);

CREATE TABLE lastmile_plan.rejection_reason
(
  id     BIGINT                 NOT NULL,
  reason CHARACTER VARYING(200) NOT NULL,
  CONSTRAINT rejection_reason_pk PRIMARY KEY (id)
);

CREATE TABLE lastmile_plan.plan_order
(
  id                  BIGINT                 NOT NULL,
  plan_id             BIGINT                 NOT NULL,
  active_vehicle_id   BIGINT                 NOT NULL,
  package_id          BIGINT                 NOT NULL,
  order_id            BIGINT                 NOT NULL,
  order_type          CHARACTER VARYING(100) NOT NULL,
  priority            BIGINT                 NOT NULL,
  eta                 CHARACTER VARYING(100) NOT NULL,
  departure_time      TIME WITH TIME ZONE    NOT NULL,
  arrival_time        TIME WITH TIME ZONE    NOT NULL,
  driver_response     CHARACTER VARYING(100),
  rejection_reason_id BIGINT,
  rejection_reason    CHARACTER VARYING(100),
  created             TIMESTAMP WITH TIME ZONE DEFAULT now(),
  status              CHARACTER VARYING,
  excluded 			  boolean DEFAULT false,
  CONSTRAINT plan_orders_pk PRIMARY KEY (id),
  CONSTRAINT orders_plan_fk FOREIGN KEY (plan_id)
  REFERENCES lastmile_plan.plan (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT package_plan_fk FOREIGN KEY (package_id)
  REFERENCES lastmile_core."package" (package_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT reject_reason_order_fk FOREIGN KEY (rejection_reason_id)
  REFERENCES lastmile_plan.rejection_reason (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE lastmile_plan.plan_tmp
(
  id      BIGINT NOT NULL,
  created TIMESTAMP WITH TIME ZONE DEFAULT now(),
  CONSTRAINT plan_tmp_pk PRIMARY KEY (id),
  CONSTRAINT plan_created_tmp_key UNIQUE (created)
);

CREATE TABLE lastmile_plan.plan_order_tmp
(
  id                  BIGINT                 NOT NULL,
  plan_id             BIGINT                 NOT NULL,
  active_vehicle_id   BIGINT                 NOT NULL,
  package_id          BIGINT                 NOT NULL,
  order_id            BIGINT                 NOT NULL,
  order_type          CHARACTER VARYING(100) NOT NULL,
  priority            BIGINT                 NOT NULL,
  eta                 CHARACTER VARYING(100) NOT NULL,
  departure_time      TIME WITH TIME ZONE    NOT NULL,
  arrival_time        TIME WITH TIME ZONE    NOT NULL,
  driver_response     CHARACTER VARYING(100),
  rejection_reason_id BIGINT,
  created             TIMESTAMP WITH TIME ZONE DEFAULT now(),
  status              CHARACTER VARYING,
  CONSTRAINT plan_orders_tmp_pk PRIMARY KEY (id),
  CONSTRAINT orders_plan_tmp_fk FOREIGN KEY (plan_id)
  REFERENCES lastmile_plan.plan_tmp (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT package_plan_tmp_fk FOREIGN KEY (package_id)
  REFERENCES lastmile_core."package" (package_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT reject_reason_order_tmp_fk FOREIGN KEY (rejection_reason_id)
  REFERENCES lastmile_plan.rejection_reason (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
