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

CREATE TABLE IF NOT EXISTS model(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    body_id INT NOT NULL REFERENCES body(id),
    transmission_id INT NOT NULL REFERENCES transmission(id)
);

INSERT INTO model(name) VALUES ('M5', 1, 2);
INSERT INTO model(name) VALUES ('M3', 1, 1);
INSERT INTO model(name) VALUES ('M4 Coupe', 2, 1);
INSERT INTO model(name) VALUES ('A5', 2, 2);
INSERT INTO model(name) VALUES (' R8 GT', 2, 2);

CREATE TABLE IF NOT EXISTS car(
    id SERIAL PRIMARY KEY,
    brand_id INT NOT NULL REFERENCES brand(id),
    model_id INT NOT NULL REFERENCES model(id)
);

INSERT INTO car(name) VALUES (1, 1);
INSERT INTO car(name) VALUES (1, 2);
INSERT INTO car(name) VALUES (1, 3);
INSERT INTO car(name) VALUES (2, 4);
INSERT INTO car(name) VALUES (2, 5);


CREATE TABLE if not exists advertisement (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    photo BYTEA,
    sold BOOLEAN,
    created TIMESTAMP,
    car_id INT NOT NULL REFERENCES car(id),
    users_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS users_advertisement (
   id SERIAL PRIMARY KEY,
   advertisement_id INT NOT NULL REFERENCES advertisement,
   users_id INT NOT NULL REFERENCES users
);