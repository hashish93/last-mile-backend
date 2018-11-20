ALTER TABLE lastmile_request.request_history DROP COLUMN pickup_request_type;
ALTER TABLE lastmile_request.request_history ADD COLUMN request_type character varying(50);