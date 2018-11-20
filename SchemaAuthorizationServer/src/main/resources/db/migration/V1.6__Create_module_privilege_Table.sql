CREATE TABLE module_privilege (
    id bigint NOT NULL,
    privilege_id bigint NOT NULL,
    module_id bigint NOT NULL,
    identifier_name character varying(100) DEFAULT now() NOT NULL,
    parent bigint,
    default_value boolean DEFAULT false,
    created timestamp with time zone DEFAULT now(),
    version bigint DEFAULT 0 NOT NULL,
    menu_id bigint
);