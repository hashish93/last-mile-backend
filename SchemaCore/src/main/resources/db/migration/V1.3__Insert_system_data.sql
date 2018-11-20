/*
 * Authoriztion And Privilege For LastMile
 */


INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (1, 'DASHBOARD', 'dashboard', 'dashboard', TRUE, NULL, TRUE, 1);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (2, 'PICKUP_REQUESTS', 'pickuprequests', NULL, TRUE, NULL, FALSE, 2);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (3, 'ACTIVE_JOBS', 'activejobs', NULL, TRUE, NULL, FALSE, 7);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (4, 'DELIVERABLE', 'deliverable', '.', FALSE, NULL, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (5, 'HUB_MANAGEMENT', 'hub.management', '.', FALSE, NULL, FALSE, NULL);
--INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
--VALUES (6, 'PACKAGES', 'package', 'listpackage', FALSE, NULL, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (8, 'REPORTS', 'reports', '', TRUE, NULL, FALSE, 8);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (7, 'CUSTOMERS', 'customers', 'listcustomer', TRUE, 8, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (9, 'SYSTEM_FEATURES', 'user features', NULL, TRUE, NULL, FALSE, 9);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (10, 'ON_DEMAND_PICKUPS', 'ondemand.requests', 'listondemand', TRUE, 2, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (11, 'SCHEDULE_PICKUPS', 'schedule.requests', 'listonschedule', TRUE, 2, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (12, 'HISTORY_PICKUP', 'history.requests', 'historyrequest', FALSE, 8, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (13, 'USERS_MANAGEMENT', 'users', 'listemployee', TRUE, 9, FALSE, 1);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (14, 'ROLES_PERMISSIONS', 'roles', 'listroles', TRUE, 9, FALSE, 2);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (15, 'BUILDINGS', 'buildings', 'listbuilding', TRUE, 9, FALSE, 3);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (16, 'VEHICLES', 'vehicles', 'listvehicle', TRUE, 9, FALSE, 4);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (17, 'SYSCONFIG', 'config.system', 'config', FALSE, 9, FALSE, 6);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (18, 'CITY_VIEW', 'city.map', '.', TRUE, NULL, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (19, 'VEHICLE_MAP_VIEW', 'vehicle.map', 'vehicleview', TRUE, 3, FALSE, 2);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (20, 'BUILDINGS_MAP_VIEW', 'operation.map', 'operationcenter', TRUE, 3, FALSE, 3);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (21, 'DEVICES', 'devices', 'listdevices', TRUE, 9, FALSE, 5);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (25, 'DISPATCH_ACTIVITIES', 'dispatch.activities', NULL, TRUE, NULL, FALSE, 6);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (22, 'LOADING_ACTIVITIES', 'loading.activities', NULL, TRUE, 25, FALSE, NULL);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (24, 'WORK_CALENDAR', 'calendar', 'calendar', FALSE, 9, FALSE, 7);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (26, 'JOB_DISTRIBUTION', 'job.distribution', 'distribute', TRUE, 25, FALSE, 2);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (27, 'ACTIVE_ORDERS', 'active.orders', 'listactiveorders', TRUE, 3, FALSE, 1);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (28, 'ACTIVE_VEHICLE', 'active.vehicles', 'assignvehicles', TRUE, 25, FALSE, 1);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (29, 'LOADING_ACTVITIES', 'loading.activities', 'loading', TRUE, 25, FALSE, 3);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (30, 'DELIVERY_REQUESTS', 'deliveryrequests', 'deliveryrequests', TRUE, NULL, FALSE, 3);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (31, 'PACKAGES', 'listpackages', 'listpackages', TRUE, NULL, FALSE, 5);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (32, 'RETURN_REQUESTS', 'listreturns', 'listreturns', TRUE, NULL, FALSE, 4);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (33, 'FAQ', 'faq', 'faq', FALSE, 9, FALSE, 8);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (34, 'FREELANCERDRIVER', 'freelancerdriver', 'listfreelancers', TRUE, NULL, FALSE, 10);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (35, 'OFFLOADING_ACTIVITIES', 'offloading.activities', 'listoffloading', TRUE, 25, FALSE, 4);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (36, 'CONFIGURATION', 'configuration', null, TRUE, null, FALSE, 10);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (37, 'BUILDING_CONFIG', 'building_config', 'buildingconfig', TRUE, 36, FALSE, 1);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (38, 'MOBILE_CONFIG', 'mobile_config', 'mobileconfig', TRUE, 36, FALSE, 3);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (39, 'SYSTEM_CONFIG', 'system_config', 'systemconfig', TRUE, 36, FALSE, 2);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (40, 'ARCHIVED_REQUESTS', 'archived_requests', null, TRUE, null, FALSE, 11);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (41, 'PICKUP_HISTORY', 'pickup_history', 'historyrequest', TRUE, 40, FALSE, 1);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (42, 'DELIVERY_HISTORY', 'delivery_history', 'archiveddelivery', TRUE, 40, FALSE, 2);
INSERT INTO lastmile_authorization_server.menu_item (id, name, key, url, active, parent, base, the_order)
VALUES (43, 'RETURN_HISTORY', 'return_history', 'archivedreturn', TRUE, 40, FALSE, 3);



---------------------------------------------------------------------------------------

--INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (1, 'PACKAGES', 0);

INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (4, 'BUILDINGS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (5, 'BUILDINGS_MAP_VIEW', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (6, 'VEHICLE_MAP_VIEW', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (7, 'DEVICES', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (8, 'CUSTOMERS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (9, 'SYSCONFIG', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (10, 'ROLES_PERMISSIONS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (11, 'ON_DEMAND_PICKUPS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (12, 'SCHEDULE_PICKUPS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (13, 'HISTORY_PICKUP', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (14, 'ACTIVE_VEHICLE', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (15, 'WORK_CALENDAR', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (16, 'JOB_DISTRIBUTION', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (17, 'LOADING_ACTVITIES', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (18, 'DELIVERY_REQUESTS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (19, 'ACTIVE_ORDERS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (20, 'PACKAGES', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (21, 'RETURN_REQUESTS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (22, 'FAQ', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (23, 'VEHICLES', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (24, 'USERS_MANAGEMENT', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (25, 'FREELANCERDRIVER', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (26, 'OFFLOADING_ACTIVITIES', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (27, 'BUILDING_CONFIG', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (28, 'SYSTEM_CONFIG', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (29, 'MOBILE_CONFIG', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (30, 'ARCHIVED_PICKUP_REQUESTS', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (31, 'ARCHIEVED_DELIVERY_REQUEST', 0);
INSERT INTO lastmile_authorization_server.module (id, name, version) VALUES (32, 'ARCHIEVED_RETURN_REQUEST', 0);
---------------------------------------------------------------------------------------

INSERT INTO lastmile_authorization_server.privilege (id, name, displayname, version) VALUES (1, 'List', 'View', 0);
INSERT INTO lastmile_authorization_server.privilege (id, name, displayname, version)
VALUES (2, 'AddEdit', 'Adminstrate', 0);
INSERT INTO lastmile_authorization_server.privilege (id, name, displayname, version)
VALUES (3, 'ActivateDeactivate', 'Activate/Deactivate', 0);

---------------------------------------------------------------------------------------
--INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
--VALUES (1, 1, 1, 'listpackage', NULL, FALSE, 0, NULL);
--INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
--VALUES (2, 2, 1, 'addeditpackage', 1, FALSE, 0, NULL);
--INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
--VALUES (3, 3, 1, 'deletepackage', 1, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (4, 1, 23, 'listvehicle', NULL, FALSE, 0, 16);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (5, 2, 23, 'addeditvehicle', 4, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (6, 3, 23, 'deletevehicle', 4, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (7, 1, 24, 'listemployee', NULL, FALSE, 0, 13);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (8, 2, 24, 'addeditemployee', 7, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (9, 3, 24, 'deleteemployee', 7, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (10, 1, 4, 'listbuilding', NULL, FALSE, 0, 15);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (11, 2, 4, 'addeditbuilding', 10, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (12, 3, 4, 'deletebuilding', 10, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (13, 1, 5, 'operationcenter', NULL, FALSE, 0, 20);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (14, 1, 6, 'vehicleview', NULL, FALSE, 0, 19);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (15, 1, 7, 'listdevices', NULL, FALSE, 0, 21);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (16, 2, 7, 'addeditdevices', 15, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (17, 3, 7, 'deletedevices', 15, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (18, 1, 8, 'listcustomer', NULL, FALSE, 0, 7);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (19, 1, 9, 'config', NULL, FALSE, 0, 17);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (20, 1, 10, 'listroles', NULL, FALSE, 0, 14);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (21, 2, 10, 'addeditroles', 20, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (22, 3, 10, 'deleteroles', 20, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (23, 1, 11, 'listondemand', NULL, FALSE, 0, 10);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (24, 2, 11, 'editondemand', 23, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (25, 1, 12, 'listonschedule', NULL, FALSE, 0, 11);
--INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
--VALUES (26, 1, 13, 'historyrequest', NULL, FALSE, 0, 12);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (27, 2, 12, 'reschedule', 25, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (28, 3, 12, 'deleteschedule', 25, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (29, 2, 9, 'editconfig', 19, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (30, 1, 14, 'listactivevehicles', NULL, FALSE, 0, 28);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (31, 2, 14, 'addeditactivevehicles', 30, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (32, 3, 14, 'deleteactivevehicles', 30, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (33, 1, 15, 'viewcalendar', NULL, FALSE, 0, 24);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (34, 2, 15, 'editcalendar', 33, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (35, 1, 17, 'viewloading', NULL, FALSE, 0, 29);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (36, 1, 16, 'viewjobdistribution', NULL, FALSE, 0, 26);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (37, 2, 16, 'editjobdistribution', 36, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (38, 1, 18, 'listdeliveries', NULL, FALSE, 0, 30);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (39, 1, 19, 'listactiveorders', NULL, FALSE, 0, 27);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (40, 2, 18, 'editdeliveries', 38, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (41, 2, 17, 'editloading', 35, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (42, 1, 20, 'listpackages', NULL, FALSE, 0, 31);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (43, 1, 21, 'listreturns', NULL, FALSE, 0, 32);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (44, 2, 21, 'editreturns', 43, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (45, 3, 21, 'deletereturns', 43, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (46, 1, 22, 'listfaq', NULL, FALSE, 0, 33);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (47, 2, 22, 'editfaq', 46, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (48, 1, 25, 'listfreelancerdrivers', NULL, FALSE, 0, 34);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (49, 1, 26, 'viewoffloading', NULL, FALSE, 0, 35);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (50, 2, 26, 'editoffloading', 49, FALSE, 0, NULL);

INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (51, 1, 27, 'listbuildingconfig', null, FALSE, 0, 37);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (52, 2, 27, 'edittbuildingconfig', 51, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (53, 1, 28, 'listsystemconfig', null, FALSE, 0, 38);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (54, 2, 28, 'editsystemconfig', 53, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (55, 1, 29, 'listmobileconfig', null, FALSE, 0, 39);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (56, 2, 29, 'editmobileconfig', 55, FALSE, 0, NULL);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (57, 1, 30, 'viewarchivedpickuprequests', null, FALSE, 0, 41);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (58, 1, 31, 'viewarchieveddeliveryrequests', null, FALSE, 0, 42);
INSERT INTO lastmile_authorization_server.module_privilege (id, privilege_id, module_id, identifier_name, parent, default_value, version, menu_id)
VALUES (59, 1, 32, 'viewarchievedreturnrequests', null, FALSE, 0, 43);

---------------------------------------------------------------------------------------


INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (1, 'SUPER_ADMIN', FALSE, FALSE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (2, 'FREELANCER_ADMIN', FALSE, TRUE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (3, 'HUB_ADMIN', FALSE, TRUE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (4, 'SUPERVISOR', FALSE, TRUE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (5, 'OPERATION_BACKOFFICE', TRUE, TRUE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (6, 'CORPORATE_DRIVER', TRUE, TRUE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (7, 'FREELANCER_DRIVER', FALSE, FALSE);
INSERT INTO lastmile_authorization_server.user_type (id, name, normal_user, admin_user) VALUES (8, 'CUSTOMER', FALSE, FALSE);

---------------------------------------------------------------------------------------

INSERT INTO lastmile_authorization_server.users (user_id, username, password, firstname, lastname, country_code_id, phone, email, enabled, personal_photo_id, status, description, version, user_type_id)
VALUES
  (1, 'superadmin', 'appzone', 'superadmin', 'superadmin', 63, '01006747065', 'admin@appzone.com', TRUE, NULL, 'ACTIVE',
   NULL, 0, 1);

---------------------------------------------------------------------------------------

INSERT INTO lastmile_authorization_server.roles (role_id, rolename, editable, description, version, enabled, hub_id, system_role)
VALUES (1, 'SUPER_ADMIN', FALSE, 'Super Admin User', 0, TRUE, NULL, TRUE);
INSERT INTO lastmile_authorization_server.roles (role_id, rolename, editable, description, version, enabled, hub_id, system_role)
VALUES (2, 'FREELANCER_ADMIN', FALSE, 'Role Description', 0, TRUE, NULL, TRUE);
INSERT INTO lastmile_authorization_server.roles (role_id, rolename, editable, description, version, enabled, hub_id, system_role)
VALUES (3, 'SUPERVISOR', FALSE, 'super visor', 0, TRUE, NULL, TRUE);
INSERT INTO lastmile_authorization_server.roles (role_id, rolename, editable, description, version, enabled, hub_id, system_role)
VALUES (4, 'HUB_ADMIN', FALSE, 'HUB_ADMIN', 0, TRUE, NULL, TRUE);

---------------------------------------------------------------------------------------

INSERT INTO lastmile_authorization_server.system_role (id, name) VALUES (1, 'HUBADMIN');
INSERT INTO lastmile_authorization_server.system_role (id, name) VALUES (2, 'SUPERVISOR');

---------------------------------------------------------------------------------------

INSERT INTO lastmile_authorization_server.users_roles (role_id, user_id, version) VALUES (1, 1, 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 4, TRUE, 0, 1);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 5, TRUE, 0, 2);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 6, TRUE, 0, 3);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 7, TRUE, 0, 4);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 8, TRUE, 0, 5);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 9, TRUE, 0, 6);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 10, TRUE, 0, 7);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 11, TRUE, 0, 8);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 12, TRUE, 0, 9);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 13, TRUE, 0, 10);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 14, TRUE, 0, 11);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 15, TRUE, 0, 12);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 16, TRUE, 0, 13);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 17, TRUE, 0, 14);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 18, TRUE, 0, 15);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 19, TRUE, 0, 16);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 20, TRUE, 0, 17);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 21, TRUE, 0, 18);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 22, TRUE, 0, 19);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 23, TRUE, 0, 20);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 24, TRUE, 0, 21);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 25, TRUE, 0, 22);
--INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
--VALUES (1, 26, TRUE, 0, 23);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 27, TRUE, 0, 24);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 28, TRUE, 0, 25);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 29, TRUE, 0, 26);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 30, TRUE, 0, 27);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 31, TRUE, 0, 28);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 32, TRUE, 0, 29);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 33, TRUE, 0, 30);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 34, TRUE, 0, 31);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 35, TRUE, 0, 32);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 36, TRUE, 0, 33);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 37, TRUE, 0, 34);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 38, TRUE, 0, 35);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 39, TRUE, 0, 36);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 40, TRUE, 0, 37);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 41, TRUE, 0, 38);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 42, TRUE, 0, 39);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 43, TRUE, 0, 40);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 44, TRUE, 0, 41);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 45, TRUE, 0, 42);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 46, TRUE, 0, 43);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 47, TRUE, 0, 44);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 48, TRUE, 0, 107);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 49, TRUE, 0, 108);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 50, TRUE, 0, 109);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 51, TRUE, 0, 110);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 52, TRUE, 0, 111);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 53, TRUE, 0, 112);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 54, TRUE, 0, 113);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 55, TRUE, 0, 114);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 56, TRUE, 0, 115);
--------------------------------------------

INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 4, TRUE, 0, 45);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 7, TRUE, 0, 46);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 10, TRUE, 0, 47);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 13, TRUE, 0, 48);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 14, TRUE, 0, 49);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 15, TRUE, 0, 50);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 18, TRUE, 0, 51);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 20, TRUE, 0, 52);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 23, TRUE, 0, 53);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 25, TRUE, 0, 54);
--INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
--VALUES (3, 26, TRUE, 0, 55);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 30, TRUE, 0, 56);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 33, TRUE, 0, 57);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 35, TRUE, 0, 58);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 36, TRUE, 0, 59);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 38, TRUE, 0, 60);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 39, TRUE, 0, 61);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 42, TRUE, 0, 62);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 43, TRUE, 0, 63);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 46, TRUE, 0, 64);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 49, TRUE, 0, 116);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 50, TRUE, 0, 117);

INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 51, TRUE, 0, 118);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 53, TRUE, 0, 119);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 55, TRUE, 0, 120);


--------------------------------------------

INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 4, TRUE, 0, 65);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 5, TRUE, 0, 66);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 6, TRUE, 0, 67);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 7, TRUE, 0, 68);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 8, TRUE, 0, 69);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 9, TRUE, 0, 70);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 13, TRUE, 0, 71);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 14, TRUE, 0, 72);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 15, TRUE, 0, 73);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 16, TRUE, 0, 74);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 17, TRUE, 0, 75);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 18, TRUE, 0, 76);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 19, TRUE, 0, 77);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 20, TRUE, 0, 78);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 21, TRUE, 0, 79);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 22, TRUE, 0, 80);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 23, TRUE, 0, 81);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 24, TRUE, 0, 82);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 25, TRUE, 0, 83);
--INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
--VALUES (4, 26, TRUE, 0, 84);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 27, TRUE, 0, 85);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 28, TRUE, 0, 86);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 29, TRUE, 0, 87);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 30, TRUE, 0, 88);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 31, TRUE, 0, 89);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 32, TRUE, 0, 90);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 33, TRUE, 0, 91);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 34, TRUE, 0, 92);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 35, TRUE, 0, 93);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 36, TRUE, 0, 94);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 37, TRUE, 0, 95);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 38, TRUE, 0, 96);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 39, TRUE, 0, 97);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 40, TRUE, 0, 98);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 41, TRUE, 0, 99);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 42, TRUE, 0, 100);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 43, TRUE, 0, 101);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 44, TRUE, 0, 102);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 45, TRUE, 0, 103);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 46, TRUE, 0, 104);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 47, TRUE, 0, 105);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 49, TRUE, 0, 121);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 50, TRUE, 0, 122);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 51, TRUE, 0, 123);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 52, TRUE, 0, 124);

INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (2, 48, TRUE, 0, 125);

INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 57, TRUE, 0, 126);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 58, TRUE, 0, 127);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (1, 59, TRUE, 0, 128);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 57, TRUE, 0, 129);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 58, TRUE, 0, 130);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (3, 59, TRUE, 0, 131);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 57, TRUE, 0, 132);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 58, TRUE, 0, 133);
INSERT INTO lastmile_authorization_server.role_privilege (role_id, module_privilege_id, enabled, version, id)
VALUES (4, 59, TRUE, 0, 134);
---------------------------------------------------------------------------------------




/*
 * LOOK UP FOR SCHEMA CORE
 */

---------------------------------------------------------------------------------------

INSERT INTO lastmile_core.building_type (building_type_id, type, version) VALUES (1, 'Hub', 0);
INSERT INTO lastmile_core.building_type (building_type_id, type, version) VALUES (2, 'Store', 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (1, 'SATURDAY', 0, 'working');
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (2, 'SUNDAY', 0, 'working');
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (3, 'MONDAY', 0, 'working');
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (4, 'TUESDAY', 0, 'working');
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (5, 'WEDNESDAY', 0, 'working');
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (6, 'THURSDAY', 0, 'working');
INSERT INTO lastmile_core.calendar (id, dayname, version, status) VALUES (7, 'FRIDAY', 0, 'non-working');

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.device_brand (id, brandname) VALUES (1, 'HTC');

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.driving_license_type (driving_license_type_id, license_type, version) VALUES (1, 'Type 1', 0);
INSERT INTO lastmile_core.driving_license_type (driving_license_type_id, license_type, version) VALUES (2, 'Type 2', 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (1, 'Cameras & Electronics', '25*25*25', 50, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (2, 'Mobile Phones, Tablets & Accessories', '10*10*10', 15, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (3, 'Clothing, Shoes & Jewelry', '15*15*15', 20, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (4, 'Movies ,Music & Games', '10*10*10', 10, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (5, 'Toys, Kids & Baby', '30*30*30', 30, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (6, 'Grocery, Food & Beverages', '15*15*15', 20, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (8, 'Personal Care products', '5*5*5', 10, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (9, 'Home Services', '5*5*5', 20, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (10, 'Art, Crafts & Collectables', '20*20*20', 30, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (11, 'Home Appliances', '30*30*30', 50, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (12, 'Home Decor & Furniture', '30*30*30', 100, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (13, 'Office Products & Supplies', '30*30*30', 100, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (14, 'Vehicle Parts & Accessories', '30*30*30', 100, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (15, 'Pet Food & Supplies', '20*20*20', 20, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (16, 'Books & Documents ', '15*15*15', 10, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (17, 'Computers, IT & Networking', '20*20*20', 30, NULL, 0);
INSERT INTO lastmile_core.package_type (package_type_id, packagetype, packagedimension, expectedweight, description, version)
VALUES (18, 'Sports & Fitness', '70*70*70', 100, NULL, 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (1, 'Panel truck', 'شاحنة', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (2, 'Minivan', 'شاحنة صغيرة', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (3, 'Tow truck', 'شاحنة الجر', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (4, 'Panel van', 'شاحنة', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (5, 'Sedan delivery', 'سيارة سيدان', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (6, 'Box truck', 'شاحنة مغلقة', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (7, 'Van', 'شاحنة', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (8, 'Medium Duty Truck', 'شاحنة متوسطة الاحمال', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (9, 'Medium Standard Truck', 'شاحنة متوسطة عادة', 0);
INSERT INTO lastmile_core.vehicle_type (vehicle_type_id, type, type_ar, version) VALUES (10, 'Flatbed truck ', 'شاحنة مسطحة', 0);

---------------------------------------------------------------------------------------

INSERT INTO lastmile_core.shipment_service (shipment_service_id, service, description, version)
VALUES (1, 'Internal', NULL, 0);
INSERT INTO lastmile_core.shipment_service (shipment_service_id, service, description, version)
VALUES (2, 'Outbound', NULL, 0);

---------------------------------------------------------------------------------------

INSERT INTO lastmile_core.shipment_service_type (shipment_service_type_id, type, shipment_service_id, description, version)
VALUES (1, 'Next Day', 1, NULL, 0);
INSERT INTO lastmile_core.shipment_service_type (shipment_service_type_id, type, shipment_service_id, description, version)
VALUES (2, '2-3 Days', 1, NULL, 0);
INSERT INTO lastmile_core.shipment_service_type (shipment_service_type_id, type, shipment_service_id, description, version)
VALUES (3, '7 Days', 1, NULL, 0);
INSERT INTO lastmile_core.shipment_service_type (shipment_service_type_id, type, shipment_service_id, description, version)
VALUES (4, 'Next Day', 2, NULL, 0);
INSERT INTO lastmile_core.shipment_service_type (shipment_service_type_id, type, shipment_service_id, description, version)
VALUES (5, '2-3 Days', 2, NULL, 0);
INSERT INTO lastmile_core.shipment_service_type (shipment_service_type_id, type, shipment_service_id, description, version)
VALUES (6, '7 Days', 2, NULL, 0);

---------------------------------------------------------------------------------------

INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (1, 'Handle with Care', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (2, 'Glass', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (3, 'Use No Hooks', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (4, 'This Side Up', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (5, 'Fragile', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (6, 'Keep In Cool Place', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (7, 'Keep Dry', 0);
INSERT INTO lastmile_core.package_label (package_label_id, label, version) VALUES (8, 'Opend Here', 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.sizes_config (size_id, sizename, width, height, length, correspondence, status, version)
VALUES (589057341, 'MEDIUM', 234, 234, 234, 234, 'ACTIVE', 0);
INSERT INTO lastmile_core.sizes_config (size_id, sizename, width, height, length, correspondence, status, version)
VALUES (318238291, 'SMALL', 5, 6, 45, 7, 'ACTIVE', 0);
INSERT INTO lastmile_core.sizes_config (size_id, sizename, width, height, length, correspondence, status, version)
VALUES (318224056, 'LARGE', 2, 3, 1, 4, 'ACTIVE', 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit,config_type) VALUES
  (1, 3, 'DRIVER_RESPONSE_TIME', 'C_DRIVR_RESPNS_TIME_DESC', 'ACTIVE', 0, 'MINS','ON_DEMAND');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit,config_type) VALUES
  (2, 0, 'DISPATCH_MODE', NULL, 'ACTIVE', 0, NULL,'ON_DEMAND');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit,config_type) VALUES
  (3, 60000, 'NEARBY_VEHICLES_RANGE', 'C_NEAR_VEH_RANG_DESC', 'ACTIVE', 0, 'KM','ON_DEMAND');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit) VALUES
  (4, 222222, 'TRIALS_OF_LOOKING_FOR_VEHICLE', 'C_TRAILS_LKN_F_VEH_DESC', 'ACTIVE', 0, 'Trial');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit) VALUES
  (5, 500000, 'TRIALS_INTERVAL_OF_LOOKING_FOR_VEHICLE', 'C_TRLS_INTRV_F_LKN_VEH_DESC', 'ACTIVE', 0, 'SECOND');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit) VALUES
  (6, 503, 'WAITING_TIM_F_STTS_LKN_F_VEH', 'C_WTNG_TIM_F_STTS_LKN_F_VEH_DESC', 'ACTIVE', 0, 'Hrs');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit) VALUES
  (7, 455, 'TRIALS_INTRV_F_STTS_LKN_F_VEH', 'C_TRLS_INTRV_F_STTS_LKN_F_VEH_DESC', 'ACTIVE', 0, 'Mins');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit,config_type) VALUES
  (8, 5, 'EMERGENCY_CAPACITY', 'C_EMRGNCY_CAP_DESC', 'ACTIVE', 0, 'Percent (%)','GENERAL');
INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit,config_type) VALUES
  (14, 0, 'DISTRIBUTION_MODE', NULL, 'ACTIVE', 0, NULL,'DISTRIBUTION');
INSERT INTO lastmile_core.system_config (config_id, text_value, displayname, description, status, version, unit) VALUES
  (15, '10:30 AM', 'AUTOMATIC_PLAN_CREATION_TIME', 'C_ATMTC_PLN_CRTION_TIM_DEC', 'ACTIVE', 0, 'HH:mm AM/PM');

INSERT INTO lastmile_core.system_config (config_id, value, displayname, description, status, version, unit,config_type)
VALUES (16, 5, 'COUNT_DOWN', 'C_COUNT_DOWN_DESC', 'ACTIVE', 0, 'MINS','ON_DEMAND');

---------------------------------------------------------------------------------------
INSERT INTO lastmile_request.pickup_request_type (pickup_request_type_id, type, version) VALUES (1, 'ON-DEMAND', 0);
INSERT INTO lastmile_request.pickup_request_type (pickup_request_type_id, type, version) VALUES (2, 'SCHEDULED', 0);

---------------------------------------------------------------------------------------
INSERT INTO lastmile_request.pickup_time (pickup_time_id, time_from, time_to, version)
VALUES (1, '2:00 PM', '5:00 PM', 0);
INSERT INTO lastmile_request.pickup_time (pickup_time_id, time_from, time_to, version)
VALUES (2, '4:00 PM', '8:00 PM', 0);







