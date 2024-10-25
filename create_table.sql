CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(128) UNIQUE,
    role VARCHAR(128),
    firstname VARCHAR(128),
    lastname VARCHAR(128),
    birth_date DATE
);

DROP table users;

create sequence users_id_seq_1 owned by users.id;