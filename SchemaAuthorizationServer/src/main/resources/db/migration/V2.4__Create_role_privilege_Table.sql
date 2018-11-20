CREATE TABLE role_privilege (
    role_id bigint NOT NULL,
    module_privilege_id bigint NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    version bigint DEFAULT 0 NOT NULL,
    id bigint NOT NULL
);