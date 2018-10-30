DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_orgs;

CREATE  TABLE users (
  user_name VARCHAR(100) NOT NULL ,
  password VARCHAR(100) NOT NULL ,
  enabled boolean NOT NULL ,
  PRIMARY KEY (user_name));

CREATE TABLE user_roles (
  user_role_id SERIAL,
  user_name varchar(100) NOT NULL,
  role varchar(100) NOT NULL,
  PRIMARY KEY (user_role_id));

CREATE TABLE user_orgs (
  organization_id   VARCHAR(100)  NOT NULL,
  user_name         VARCHAR(100)   NOT NULL,
  PRIMARY KEY (user_name));

INSERT INTO users(user_name,password,enabled) VALUES ('daniel.dascalu','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);
INSERT INTO users(user_name,password,enabled) VALUES ('daniel.tirzuman','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);
INSERT INTO users(user_name,password,enabled) VALUES ('vlad.batrinu','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);

INSERT INTO user_roles (user_name, role) VALUES ('daniel.dascalu', 'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_name, role) VALUES ('daniel.dascalu', 'ROLE_BACKER');
INSERT INTO user_roles (user_name, role) VALUES ('daniel.tirzuman', 'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_name, role) VALUES ('daniel.tirzuman', 'ROLE_BACKER');
INSERT INTO user_roles (user_name, role) VALUES ('vlad.batrinu', 'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_name, role) VALUES ('vlad.batrinu', 'ROLE_BACKER');

INSERT INTO user_orgs (organization_id, user_name) VALUES ('z1859f1f-4bd7-4593-8654-ea6d9a6a626e', 'daniel.dascalu');
INSERT INTO user_orgs (organization_id, user_name) VALUES ('z1859f1f-4bd7-4593-8654-ea6d9a6a626e', 'daniel.tirzuman');
INSERT INTO user_orgs (organization_id, user_name) VALUES ('z1859f1f-4bd7-4593-8654-ea6d9a6a626e', 'vlad.batrinu');
