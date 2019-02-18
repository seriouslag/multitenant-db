create table users
(
	username varchar(255) not null,
	id serial not null,
	password varchar(255) not null,
	first_name varchar(30),
	last_name varchar(30),
	email varchar(255),
	phone_number varchar(12),
	enabled boolean default true not null,
	last_password_reset_date timestamp
);

alter table users owner to postgres;

create unique index users_email_uindex
	on users (email);

create unique index users_id_uindex
	on users (id);

create unique index users_username_uindex
	on users (username);

create table authority
(
	name varchar(255) not null,
	id serial not null
		constraint authority_pk
			primary key
);

alter table authority owner to postgres;

create unique index authority_id_uindex
	on authority (id);

create table user_authority
(
	id serial not null
		constraint user_authority_pk
			primary key,
	user_id integer not null,
	authority_id integer not null
);

alter table user_authority owner to postgres;

create unique index user_authority_id_uindex
	on user_authority (id);