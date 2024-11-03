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

CREATE TABLE IF NOT EXISTS chat(
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_chat(
    user_id BIGINT REFERENCES users(id),
    chat_id BIGINT REFERENCES chat(id),
    PRIMARY KEY(user_id, chat_id)
);

CREATE TABLE IF NOT EXISTS users_chat(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    chat_id BIGINT REFERENCES chat(id),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS students(
    id BIGSERIAL PRIMARY KEY,
    name varchar(128) NOT NULL,
    course_id BIGINT REFERENCES course(id)
);

CREATE TABLE IF NOT EXISTS course(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS student_profile(
    id BIGSERIAL PRIMARY KEY,
    avg_rating DECIMAL NOT NULL,
    student_id BIGINT UNIQUE NOT NULL REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS trainer(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS trainer_courses(
    id BIGSERIAL PRIMARY KEY,
    id_trainer  BIGINT REFERENCES trainer,
    id_course BIGINT REFERENCES course
);

CREATE TABLE IF NOT EXISTS test.users(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL,
    profile_id BIGINT REFERENCES test.profile
);

CREATE TABLE IF NOT EXISTS test.profile(
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(128),
    country_id BIGINT REFERENCES test.country
);

CREATE TABLE IF NOT EXISTS test.country(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);

DROP table IF EXISTS trainer;

DROP table students;

DROP table users_chat;

DROP table profile;

DROP table users;

create sequence users_id_seq_1 owned by users.id;