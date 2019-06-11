DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  category_id   VARCHAR(100)   PRIMARY KEY NOT NULL,

  name          TEXT NOT NULL,
  description   TEXT NOT NULL,
  image_url     TEXT NOT NULL,

  status        VARCHAR(100)
  );


DROP TABLE IF EXISTS campaign_media_resources;
CREATE TABLE campaign_media_resources (
  resource_id     VARCHAR(100) PRIMARY KEY NOT NULL,
  campaign_id     VARCHAR(100) NOT NULL,

  name            TEXT NOT NULL,
  url             TEXT NOT NULL,
  type            TEXT NOT NULL,
  format          TEXT NOT NULL,

  status          VARCHAR(100)
  );


DROP TABLE IF EXISTS campaign_statuses;
CREATE TABLE campaign_statuses (
  status_id                          VARCHAR(100) PRIMARY KEY NOT NULL,
  campaign_id                        VARCHAR(100) NOT NULL,

  type                               VARCHAR(100) NOT NULL,
  type_description                   TEXT         NOT NULL,
  sub_type                           VARCHAR(100) NOT NULL,
  sub_type_description               TEXT         NOT NULL

  );

DROP TABLE IF EXISTS campaigns;
CREATE TABLE campaigns (
  campaign_id                VARCHAR(100) NOT NULL  PRIMARY KEY,
  category_id                VARCHAR(100) NOT NULL,
  fundraiser_id              VARCHAR(100) NOT NULL,
  wallet_id                  VARCHAR(100) NOT NULL,
  status_id                  VARCHAR(100) NOT NULL,

  campaign_title             TEXT NOT NULL,
  campaign_description       TEXT NOT NULL,

  amount_goal                INT NOT NULL,
  expenses_description       TEXT NOT NULL,

  location                   TEXT NOT NULL,

  start_date                 TIMESTAMP WITH TIME ZONE NOT NULL,
  end_date                   TIMESTAMP WITH TIME ZONE NOT NULL

  );

DROP TABLE IF EXISTS events;
CREATE TABLE events (
  event_id                VARCHAR(100) NOT NULL  PRIMARY KEY,
  fundraiser_id           VARCHAR(100) NOT NULL,
  wallet_id               VARCHAR(100) NOT NULL,

  event_title             TEXT NOT NULL,
  event_description       TEXT NOT NULL,

  location                TEXT NOT NULL,

  start_date              TIMESTAMP WITH TIME ZONE NOT NULL
  );

DROP TABLE IF EXISTS event_attendance;
CREATE TABLE event_attendance (
  attendance_id           VARCHAR(100) NOT NULL  PRIMARY KEY,
  event_id                VARCHAR(100) NOT NULL,
  fundraiser_id           VARCHAR(100) NOT NULL,
  fundraiser_name         VARCHAR(100) NOT NULL,
  fundraiser_type         VARCHAR(100) NOT NULL,
  table_id                VARCHAR(100) NOT NULL
  );