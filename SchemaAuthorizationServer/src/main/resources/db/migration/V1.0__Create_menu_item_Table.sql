CREATE TABLE menu_item (
    id bigint NOT NULL,
    name character varying,
    key character varying,
    url character varying,
    active boolean,
    parent bigint,
    base boolean,
    the_order bigint
);