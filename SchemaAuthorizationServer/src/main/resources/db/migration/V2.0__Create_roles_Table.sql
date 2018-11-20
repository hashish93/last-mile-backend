CREATE TABLE roles (
    role_id bigint NOT NULL,
    rolename character varying(100) NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    iseditable boolean DEFAULT false NOT NULL,
    description character varying(500),
    version bigint DEFAULT 0 NOT NULL,
    enabled boolean
);