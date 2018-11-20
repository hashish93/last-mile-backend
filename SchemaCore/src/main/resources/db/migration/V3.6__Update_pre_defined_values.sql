UPDATE lastmile_request.cancelation_reason SET reason = 'Actual cost is too high' WHERE id = 1;
UPDATE lastmile_request.cancelation_reason SET reason = 'DRIVER_NO_CAPACITY' WHERE id = 2;
UPDATE lastmile_request.cancelation_reason SET reason = 'Nonlegal package content' WHERE id = 3;


UPDATE lastmile_core.package_label SET label = 'Open Here' WHERE package_label_id = 8;