DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS oauth_client_details;
DROP TABLE IF EXISTS oauth_client_token;
DROP TABLE IF EXISTS oauth_access_token;
DROP TABLE IF EXISTS oauth_refresh_token;
DROP TABLE IF EXISTS oauth_code;
DROP TABLE IF EXISTS oauth_approvals;

CREATE  TABLE users (
  user_id             VARCHAR(100) NOT NULL,
  fundraiser_id       VARCHAR(100) NOT NULL,
  fundraiser_type     VARCHAR(100) NOT NULL,
  user_name           VARCHAR(100) PRIMARY KEY NOT NULL ,

  email               VARCHAR(100) NOT NULL ,
  password            VARCHAR(100) NOT NULL,
  password_changed    BOOLEAN NOT NULL,
  enabled             BOOLEAN NOT NULL
  );

CREATE TABLE user_roles (
  user_role_id           VARCHAR(100) PRIMARY KEY NOT NULL,
  user_name              VARCHAR(100) NOT NULL,

  role                   VARCHAR(100) NOT NULL
  );

-- Tables for OAuth token store
CREATE TABLE oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  resource_ids            VARCHAR(255),
  client_secret           VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
);

CREATE TABLE oauth_client_token (
  token_id                VARCHAR(255),
  token                   bytea,
  authentication_id       VARCHAR(255) PRIMARY KEY,
  user_name               VARCHAR(255),
  client_id               VARCHAR(255)
);

CREATE TABLE oauth_access_token (
  token_id                VARCHAR(255),
  token                   bytea,
  authentication_id       VARCHAR(255) PRIMARY KEY,
  user_name               VARCHAR(255),
  client_id               VARCHAR(255),
  authentication          bytea,
  refresh_token           VARCHAR(255)
);

CREATE TABLE oauth_refresh_token (
  token_id                VARCHAR(255),
  token                   bytea,
  authentication          bytea
);

CREATE TABLE oauth_code (
  code                    VARCHAR(255),
  authentication          bytea
);

CREATE TABLE oauth_approvals (
	userId                  VARCHAR(256),
	clientId                VARCHAR(256),
	scope                   VARCHAR(256),
	status                  VARCHAR(10),
	expiresAt               TIMESTAMP,
	lastModifiedAt          TIMESTAMP
);