CREATE TABLE privilege (
    id bigint NOT NULL,
    name character varying NOT NULL,
    displayname character varying NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    version bigint DEFAULT 0 NOT NULL
);