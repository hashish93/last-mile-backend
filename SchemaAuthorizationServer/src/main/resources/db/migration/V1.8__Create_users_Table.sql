CREATE TABLE users (
    user_id bigint NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    phone character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    personal_photo_id bigint,
    status character varying(50) DEFAULT 'ACTIVE'::character varying NOT NULL,
    description character varying(500),
    version bigint DEFAULT 0 NOT NULL,
    user_type character varying(100) NOT NULL
);

