create schema users;

CREATE TABLE if NOT EXISTS users.user
(
    id           serial PRIMARY KEY,
    username     varchar(32) NOT NULL,
    password     varchar(32) NOT NULL,
    created_date timestamp   NOT NULL DEFAULT now()
);