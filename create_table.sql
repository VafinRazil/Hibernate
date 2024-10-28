CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(128) UNIQUE,
    role VARCHAR(128),
    firstname VARCHAR(128),
    lastname VARCHAR(128),
    birth_date DATE
);

CREATE TABLE IF NOT EXISTS company(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS profile(
    user_id BIGSERIAL PRIMARY KEY REFERENCES users(id),
    street varchar(128),
    lang CHAR(2)
);

CREATE TABLE IF NOT EXISTS profile(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL UNIQUE REFERENCES users(id),
    street varchar(128),
    lang CHAR(2)
);

DROP table profile;

DROP table users;

create sequence users_id_seq_1 owned by users.id;