CREATE TABLE users_roles (
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    version bigint DEFAULT 0 NOT NULL
);