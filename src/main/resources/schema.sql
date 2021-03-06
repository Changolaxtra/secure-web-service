CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  ON authorities (username, authority);

CREATE TABLE login_attempt (
  username VARCHAR(50) NOT NULL,
  attempts INT(2) NOT NULL,
  locked BOOLEAN,
  stamp TIMESTAMP,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES users(username)
);