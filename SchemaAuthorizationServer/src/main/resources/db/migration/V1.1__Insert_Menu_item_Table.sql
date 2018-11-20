INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (1, 'DASHBOARD', 'dashboard', 'dashboard', true, NULL, true, 1);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (2, 'MAPS', 'maps', NULL, true, NULL, false, 2);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (3, 'PICKUPS', 'pickups', NULL, true, NULL, false, 4);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (4, 'DELIVERABLE', 'deliverable', '.', false, NULL, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (5, 'HUB_MANAGEMENT', 'hub.management', '.', false, NULL, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (6, 'PACKAGES', 'package', 'listpackage', true, NULL, false, 5);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (7, 'CUSTOMERS', 'customers', 'listcustomer', true, NULL, false, 6);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (8, 'REPORTS', 'reports', '.', false, NULL, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (9, 'USER_FEATURES', 'user features', NULL, true, NULL, false, 7);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (10, 'ON_DEMAND_REQUESTS', 'ondemand.requests', 'listondemand', true, 3, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (11, 'SCHEDULE_REQUESTS', 'schedule.requests', 'listonschedule', true, 3, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (12, 'HISTORY_PICKUP', 'history.requests', 'historyrequest', true, 3, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (13, 'USERS', 'users', 'listemployee', true, 9, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (14, 'ROLES', 'roles', 'listroles', true, 9, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (15, 'BUILDINGS', 'buildings', 'listbuilding', true, 9, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (16, 'VEHICLES', 'vehicles', 'listvehicle', true, 9, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (17, 'SYSCONFIG', 'config.system', 'config', true, 9, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (18, 'CITY_VIEW', 'city.map', '.', true, 2, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (19, 'VEHICLE_VIEW', 'vehicle.map', 'vehicleview', true, 2, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (20, 'OPERATION_CENTER', 'operation.map', 'operationcenter', true, 2, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (21, 'DEVICES', 'devices', 'listdevices', true, 9, false, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (22, 'LOADING_ACTIVITIES', 'activities', NULL, true, NULL, true, 3);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (23, 'ACTIVE_VEHICLE', 'active.vehicles', 'assignvehicles', true, 22, true, NULL);
INSERT INTO menu_item (id, name, key, url, active, parent, base, the_order) VALUES (24, 'CALENDAR', 'calendar', 'calendar', true, 9, false, NULL);