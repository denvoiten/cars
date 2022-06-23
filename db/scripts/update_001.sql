CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128),
    email VARCHAR(128),
    password TEXT,
    CONSTRAINT email_unique UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS brand(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO brand(name) VALUES ('BMW');
INSERT INTO brand(name) VALUES ('Audi');

CREATE TABLE IF NOT EXISTS body(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO body(name) VALUES ('Седан');
INSERT INTO body(name) VALUES ('Купе');

CREATE TABLE IF NOT EXISTS transmission(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO transmission(name) VALUES ('Автоматическая');
INSERT INTO transmission(name) VALUES ('Механическая');

CREATE TABLE if not exists advertisement (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    photo BYTEA,
    sold BOOLEAN,
    created TIMESTAMP,
    brand_id INT NOT NULL REFERENCES brand(id),
    body_id INT NOT NULL REFERENCES body(id),
    transmission_id INT NOT NULL REFERENCES transmission(id),
    users_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS users_advertisement (
   id SERIAL PRIMARY KEY,
   advertisement_id INT NOT NULL REFERENCES advertisement,
   users_id INT NOT NULL REFERENCES users
);