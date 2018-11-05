
DROP TABLE IF EXISTS users;
CREATE  TABLE users (
  user_id             VARCHAR(100) NOT NULL,
  user_name           VARCHAR(100) PRIMARY KEY NOT NULL ,

  password            VARCHAR(100) NOT NULL,
  enabled             BOOLEAN NOT NULL
  );

 INSERT INTO users(user_id, user_name,password,enabled) VALUES ('748250bb-f7eb-4adc-925c-2af315cc4a55', 'daniel.dascalu','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);
 INSERT INTO users(user_id, user_name, password,enabled) VALUES ('848250bb-f7eb-4adc-925c-2af315cc4a55', 'daniel.tirzuman','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);
 INSERT INTO users(user_id, user_name,password,enabled) VALUES ('948250bb-f7eb-4adc-925c-2af315cc4a55', 'vlad.batrinu','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);


DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  user_role_id           VARCHAR(100) PRIMARY KEY NOT NULL,
  user_name              VARCHAR(100) NOT NULL,

  role                   VARCHAR(100) NOT NULL
  );

 INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('138250bb-f7eb-4adc-925c-2af315cc4a55', 'daniel.dascalu', 'ROLE_FUNDRAISER');
 INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('172250bb-f7eb-4adc-925c-2af315cc4a55', 'daniel.dascalu', 'ROLE_BACKER');
 INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('149250bb-f7eb-4adc-925c-2af315cc4a55', 'daniel.tirzuman', 'ROLE_FUNDRAISER');
 INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('148250bb-f7eb-4afc-925c-2af315cc4a56', 'daniel.tirzuman', 'ROLE_BACKER');
 INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('148250bb-f7ez-4adc-925c-2af315cc4a55', 'vlad.batrinu', 'ROLE_FUNDRAISER');
 INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('148250bb-f7eb-4adc-925c-2af315cc4a56', 'vlad.batrinu', 'ROLE_BACKER');



-- Tables for OAuth token store
DROP TABLE IF EXISTS oauth_client_details;
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

-- insert client details
INSERT INTO oauth_client_details
   (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES
   ('gohelpfund', 'ghfsecret', 'web-client,mobile-client', 'refresh_token,password,client_credentials', 'ROLE_FUNDRAISER,ROLE_BACKER', 3600, 2592000);


DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id                VARCHAR(255),
  token                   bytea,
  authentication_id       VARCHAR(255),
  user_name               VARCHAR(255),
  client_id               VARCHAR(255)
);


DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id                VARCHAR(255),
  token                   bytea,
  authentication_id       VARCHAR(255),
  user_name               VARCHAR(255),
  client_id               VARCHAR(255),
  authentication          bytea,
  refresh_token           VARCHAR(255)
);


DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id                VARCHAR(255),
  token                   bytea,
  authentication          bytea
);

DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code                    VARCHAR(255),
  authentication          bytea
);

DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
	userId                  VARCHAR(256),
	clientId                VARCHAR(256),
	scope                   VARCHAR(256),
	status                  VARCHAR(10),
	expiresAt               TIMESTAMP,
	lastModifiedAt          TIMESTAMP
);