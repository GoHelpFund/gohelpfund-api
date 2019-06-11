DROP TABLE IF EXISTS fundraiser_social;
CREATE TABLE fundraiser_social (
  social_id           VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id       VARCHAR(100) NOT NULL,

  facebook            TEXT,
  twitter             TEXT,
  linkedin            TEXT,
  website             TEXT,
  other               TEXT
  );

DROP TABLE IF EXISTS fundraiser_professional;
CREATE TABLE fundraiser_professional (
  professional_id     VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id       VARCHAR(100) NOT NULL,

  job_title           TEXT,
  job_description     TEXT,
  company_name        TEXT,
  company_url         TEXT
  );

DROP TABLE IF EXISTS fundraiser_statuses;
CREATE TABLE fundraiser_statuses (
  status_id                          VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id                      VARCHAR(100) NOT NULL,

  type                               VARCHAR(100) NOT NULL,
  type_description                   TEXT         NOT NULL,
  sub_type                           VARCHAR(100) NOT NULL,
  sub_type_description               TEXT         NOT NULL

  );

DROP TABLE IF EXISTS fundraisers;
CREATE TABLE fundraisers (
  fundraiser_id         VARCHAR(100) PRIMARY KEY NOT NULL,
  social_id             VARCHAR(100) NOT NULL,
  professional_id       VARCHAR(100) NOT NULL,
  wallet_id             VARCHAR(100) NOT NULL,
  status_id             VARCHAR(100) NOT NULL,

  name                  TEXT,
  age                   INT,
  profile_image_url     TEXT
  );
