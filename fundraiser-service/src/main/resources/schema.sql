DROP TABLE IF EXISTS fundraiser_social;

CREATE TABLE fundraiser_social (
  social_id           VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id       VARCHAR(100) NOT NULL,

  facebook            TEXT NOT NULL,
  twitter             TEXT NOT NULL,
  linkedin            TEXT NOT NULL,
  website             TEXT NOT NULL,
  other               TEXT NOT NULL
  );

INSERT INTO fundraiser_social (social_id,  fundraiser_id, facebook, twitter, linkedin, website, other)
VALUES ('z28250bb-f7eb-4adc-925c-90f315cc4a27', 'bc8250bb-f7eb-4adc-925c-2af315cc4a55', 'https://www.facebook.com/john/', 'https://twitter.com/john', 'https://www.linkedin.com/in/john/', 'https://www.john.com', 'https://www.example.com');
INSERT INTO fundraiser_social (social_id,  fundraiser_id, facebook, twitter, linkedin, website, other)
VALUES ('g29250bb-efec-2adc-925c-aff315cc4a27', '200393bc-8aaa-45a8-9093-80c4792348cd', 'https://www.facebook.com/mike/', 'https://twitter.com/mike', 'https://www.linkedin.com/in/mike/', 'https://www.mike.com', 'https://www.example.com');


DROP TABLE IF EXISTS fundraiser_professional;

CREATE TABLE fundraiser_professional (
  professional_id     VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id       VARCHAR(100) NOT NULL,

  job_title           TEXT NOT NULL,
  job_description     TEXT NOT NULL,
  company_name        TEXT NOT NULL,
  company_url         TEXT NOT NULL
  );

INSERT INTO fundraiser_professional (professional_id,  fundraiser_id, job_title, job_description, company_name, company_url)
VALUES ('a38250bb-f7eb-4adc-925c-90f315cc4a27', 'bc8250bb-f7eb-4adc-925c-2af315cc4a55', 'House Designer', 'Designing houses 24/7', 'iDesign', 'https://www.apple.com');
INSERT INTO fundraiser_professional (professional_id,  fundraiser_id, job_title, job_description, company_name, company_url)
VALUES ('b49250bb-efec-2adc-925c-aff315cc4a27', '200393bc-8aaa-45a8-9093-80c4792348cd', 'Engineer', 'Engineering solutions 24/7', 'iEngineer', 'https://www.apple.com');


DROP TABLE IF EXISTS fundraiser_statuses;

CREATE TABLE fundraiser_statuses (
  status_id                          VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id                      VARCHAR(100) NOT NULL,

  type                               VARCHAR(100),
  type_description                   TEXT,
  sub_type                           VARCHAR(100),
  sub_type_description               TEXT

  );

INSERT INTO fundraiser_statuses (status_id, fundraiser_id,  type, type_description, sub_type, sub_type_description)
VALUES ('208250bb-f7eb-4adc-925c-2af315cc4a55', 'bc8250bb-f7eb-4adc-925c-2af315cc4a55', 'PENDING', 'User is a guest', 'REGISTRATION_REQUIRED', 'User needs to register an account');
INSERT INTO fundraiser_statuses (status_id, fundraiser_id,  type, type_description, sub_type, sub_type_description)
VALUES ('218250bb-f7eb-4adc-925c-2af315cc4a55', '200393bc-8aaa-45a8-9093-80c4792348cd', 'PENDING', 'User is a guest', 'REGISTRATION_REQUIRED', 'User needs to register an account');



DROP TABLE IF EXISTS fundraisers;

CREATE TABLE fundraisers (
  fundraiser_id         VARCHAR(100) PRIMARY KEY NOT NULL,
  social_id             VARCHAR(100) NOT NULL,
  professional_id       VARCHAR(100) NOT NULL,
  status_id             VARCHAR(100) NOT NULL,

  name                  TEXT,
  age                   INT,
  profile_image_url     TEXT
  );

INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, status_id,  name, age, profile_image_url)
VALUES ('bc8250bb-f7eb-4adc-925c-2af315cc4a55', 'z28250bb-f7eb-4adc-925c-90f315cc4a27', 'a38250bb-f7eb-4adc-925c-90f315cc4a27', '208250bb-f7eb-4adc-925c-2af315cc4a55', 'John', 28, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/com.gohelpfund.api.v1.categories/charity.png');
INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, status_id, name, age, profile_image_url)
VALUES ('200393bc-8aaa-45a8-9093-80c4792348cd', 'g29250bb-efec-2adc-925c-aff315cc4a27', 'b49250bb-efec-2adc-925c-aff315cc4a27', '218250bb-f7eb-4adc-925c-2af315cc4a55', 'Mike', 38, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/com.gohelpfund.api.v1.categories/charity.png');
