
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS social_users;

DROP EXTENSION IF EXISTS citext;


CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE IF NOT EXISTS users (
  id       SERIAL PRIMARY KEY,
  email    CITEXT UNIQUE,
  login    CITEXT,
  password CITEXT
);

CREATE TABLE IF NOT EXISTS social_users (
  id         SERIAL PRIMARY KEY,
  user_id    char(256),
  user_token CHAR(128) UNIQUE
)