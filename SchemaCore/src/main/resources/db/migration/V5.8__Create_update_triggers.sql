CREATE OR REPLACE FUNCTION lastmile_core.update_package_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_package_table_trigger BEFORE UPDATE
    ON lastmile_core.package FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_core.update_package_table();





CREATE OR REPLACE FUNCTION lastmile_core.update_customer_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';



CREATE TRIGGER update_customer_table_trigger BEFORE UPDATE
    ON lastmile_core.customer FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_core.update_customer_table();




CREATE OR REPLACE FUNCTION lastmile_request.update_pickup_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';



CREATE TRIGGER update_pickup_table_trigger BEFORE UPDATE
    ON lastmile_request.pickup_request FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_request.update_pickup_table();    



CREATE OR REPLACE FUNCTION lastmile_request.update_delivery_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';



CREATE TRIGGER update_delivery_table_trigger BEFORE UPDATE
    ON lastmile_request.delivery_request FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_request.update_delivery_table();        



CREATE OR REPLACE FUNCTION lastmile_request.update_return_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';



CREATE TRIGGER update_return_table_trigger BEFORE UPDATE
    ON lastmile_request.return_request FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_request.update_return_table();            





CREATE OR REPLACE FUNCTION lastmile_request.update_nearby_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql' ;




CREATE TRIGGER update_nearby_table_trigger BEFORE UPDATE
    ON lastmile_request.nearby_vehicle FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_request.update_nearby_table();            








CREATE OR REPLACE FUNCTION lastmile_request.update_freelancer_driver_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql'  ;

CREATE TRIGGER update_freelancer_driver_table_trigger BEFORE UPDATE
    ON lastmile_core.freelancer_driver FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_request.update_freelancer_driver_table();





CREATE OR REPLACE FUNCTION lastmile_request.update_corporate_driver_table()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql'  ;

CREATE TRIGGER update_corporate_driver_table_trigger BEFORE UPDATE
    ON lastmile_core.driver FOR EACH ROW EXECUTE PROCEDURE 
    lastmile_request.update_corporate_driver_table();
