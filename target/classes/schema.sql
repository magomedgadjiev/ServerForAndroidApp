DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS social_users CASCADE ;
DROP TABLE if EXISTS tasks CASCADE;

DROP EXTENSION IF EXISTS citext;


CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE IF NOT EXISTS users (
  id       SERIAL PRIMARY KEY,
  email    CITEXT UNIQUE,
  login    CITEXT UNIQUE,
  password CITEXT
);

CREATE TABLE IF NOT EXISTS social_users (
  id         SERIAL PRIMARY KEY,
  user_id    CHAR(256),
  user_token CHAR(128) UNIQUE
);

CREATE TABLE IF NOT EXISTS tasks (
  id             SERIAL PRIMARY KEY,
  user_id        INT REFERENCES users (id),
  social_user_id INT REFERENCES social_users (id),
  name           TEXT      NOT NULL,
  time           TIMESTAMP NOT NULL,
  repeat         BOOLEAN DEFAULT FALSE,
  count          INT     DEFAULT 0,
  notification   TIMESTAMP NOT NULL
)