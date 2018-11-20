ALTER TABLE lastmile_core.package
  ADD COLUMN is_package_offloaded BOOLEAN;
ALTER TABLE lastmile_core.package
  ADD COLUMN offloading_comment CHARACTER VARYING(150);