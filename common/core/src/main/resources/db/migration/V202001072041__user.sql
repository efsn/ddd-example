create schema users;

CREATE TABLE if NOT EXISTS users.user
(
    id bigserial PRIMARY KEY,
    username varchar(32) NOT NULL,
    password varchar(32) NOT NULL,
    create_datetime timestamptz NOT NULL,
    update_datetime timestamptz NOT NULL
);
