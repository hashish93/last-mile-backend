DO
$$
BEGIN
        CREATE SEQUENCE lastmile_authorization_server.hibernate_sequence
               INCREMENT 1
               MINVALUE 1
               MAXVALUE 9223372036854775807
               START 298
               CACHE 1;
EXCEPTION WHEN duplicate_table THEN
        -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;


DO
$$
BEGIN
        CREATE SEQUENCE lastmile_request.hibernate_sequence
               INCREMENT 1
               MINVALUE 1
               MAXVALUE 9223372036854775807
               START 298
               CACHE 1;
EXCEPTION WHEN duplicate_table THEN
        -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;

DO
$$
BEGIN
        CREATE SEQUENCE lastmile_core.hibernate_sequence
               INCREMENT 1
               MINVALUE 1
               MAXVALUE 9223372036854775807
               START 298
               CACHE 1;
EXCEPTION WHEN duplicate_table THEN
        -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;

DO
$$
BEGIN
        CREATE SEQUENCE lastmile_static_content_server.hibernate_sequence
               INCREMENT 1
               MINVALUE 1
               MAXVALUE 9223372036854775807
               START 298
               CACHE 1;
EXCEPTION WHEN duplicate_table THEN
        -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;



DO
$$
BEGIN
        CREATE SEQUENCE public.hibernate_sequence
               INCREMENT 1
               MINVALUE 1
               MAXVALUE 9223372036854775807
               START 298
               CACHE 1;
EXCEPTION WHEN duplicate_table THEN
        -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;