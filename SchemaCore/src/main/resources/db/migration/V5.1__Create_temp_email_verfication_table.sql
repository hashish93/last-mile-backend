create table lastmile_core.temp_email_verfication (
	user_id bigint not null  primary key,
	new_email varchar(1000) not null , 
	verification_hash varchar(1000)
);