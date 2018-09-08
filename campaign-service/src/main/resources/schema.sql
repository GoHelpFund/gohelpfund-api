DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  category_id   VARCHAR(100)   PRIMARY KEY,

  name          TEXT NOT NULL,
  description   TEXT NOT NULL,
  image_url     TEXT NOT NULL,

  status        VARCHAR(100)
  );

INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('0d60a85e-0b90-4482-a14c-108aea2557aa', 'Charity', 'Help a charity or nonprofit right away', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('39240e9f-ae09-4e95-9fd0-a712035c8ad7', 'Education', 'Get immediate help with education costs.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/education.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('9e4de779-d6a0-44bc-a531-20cdb97178d2', 'Emergency', 'Get immediate help with emergency costs', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/emergency.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('66a45c1b-19af-4ab5-8747-1b0e2d79339d', 'Medical', 'Get immediate help with medical bills.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/medical.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('29a45c1b-13af-4ab5-8747-3b0e2d72339f', 'Animals', 'Get immediate help with animal bills.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/animals.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('a5a45c1b-19af-zcb5-8747-3e0e2d79330f', 'Volunteer', 'Get immediate help with volunteer bills.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/volunteer.png', 'CREATED');


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

INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, status)
VALUES ('9788c636-936e-4dd6-b9d5-f340329142bd', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'Image 1', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'image', 'png', 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, status)
VALUES ('3d8b664f-ef5f-4587-a45c-f2991a1fc029', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'Image 2', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'image', 'png', 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, status)
VALUES ('f618f5a8-380e-44fb-9b4e-b3286f29dcc8', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'Image 3', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'image', 'png', 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, status)
VALUES ('35cb41dd-3edb-4483-a1fe-fc315243d2f8', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'Photo 1', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'image', 'png', 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, status)
VALUES ('74b37cd4-75aa-4871-b17b-a5160428e589', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'Photo 2', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'image', 'png', 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, status)
VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'Video 1', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png', 'image', 'png', 'CREATED');


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
VALUES ('bc8250bb-f7eb-4adc-925c-2af315cc4a55', 'z28250bb-f7eb-4adc-925c-90f315cc4a27', 'a38250bb-f7eb-4adc-925c-90f315cc4a27', '208250bb-f7eb-4adc-925c-2af315cc4a55', 'John', 28, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png');
INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, status_id, name, age, profile_image_url)
VALUES ('200393bc-8aaa-45a8-9093-80c4792348cd', 'g29250bb-efec-2adc-925c-aff315cc4a27', 'b49250bb-efec-2adc-925c-aff315cc4a27', '218250bb-f7eb-4adc-925c-2af315cc4a55', 'Mike', 38, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/categories/charity.png');


DROP TABLE IF EXISTS campaign_statuses;

CREATE TABLE campaign_statuses (
  status_id                          VARCHAR(100) PRIMARY KEY NOT NULL,
  campaign_id                        VARCHAR(100) NOT NULL,

  type                               VARCHAR(100),
  type_description                   TEXT,
  sub_type                           VARCHAR(100),
  sub_type_description               TEXT

  );

INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('108250bb-f7eb-4adc-925c-2af315cc4a55', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'PENDING', 'Campaign is not public', 'REGISTRATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('118250bb-f7eb-4adc-925c-2af315cc4a55', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'PENDING', 'Campaign is not public', 'REGISTRATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('128250bb-f7eb-4adc-925c-2af315cc4a55', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'PENDING', 'Campaign is not public', 'REGISTRATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('138250bb-f7eb-4adc-925c-2af315cc4a55', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'PENDING', 'Campaign is not public', 'REGISTRATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('148250bb-f7eb-4adc-925c-2af315cc4a55', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'PENDING', 'Campaign is not public', 'REGISTRATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('158250bb-f7eb-4adc-925c-2af315cc4a55', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'PENDING', 'Campaign is not public', 'REGISTRATION_REQUIRED', 'Campaign created as an guest - user needs to register');



DROP TABLE IF EXISTS campaigns;

CREATE TABLE campaigns (
  campaign_id                VARCHAR(100) PRIMARY KEY NOT NULL,
  category_id                VARCHAR(100) NOT NULL,
  fundraiser_id              VARCHAR(100) NOT NULL,
  status_id                  VARCHAR(100) NOT NULL,

  campaign_title             TEXT NOT NULL,
  campaign_description       TEXT NOT NULL,

  amount_goal                INT NOT NULL,
  amount_raised              INT,
  expenses_description       TEXT NOT NULL,

  location                   TEXT NOT NULL,

  start_date                 TIMESTAMP WITH TIME ZONE NOT NULL,
  end_date                   TIMESTAMP WITH TIME ZONE NOT NULL,

  backers                    INT
  );

INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, status_id, campaign_title, campaign_description, amount_goal, amount_raised, expenses_description, location, start_date, end_date, backers)
VALUES ('cd881764-bea1-4249-b86d-f8fb8182eec1', '0d60a85e-0b90-4482-a14c-108aea2557aa', 'bc8250bb-f7eb-4adc-925c-2af315cc4a55', '108250bb-f7eb-4adc-925c-2af315cc4a55', 'Charity help needed', 'Charity help required for the elders', 50000, 0, 'Overall charity operations', 'UK, London', '2019-06-29T10:30:00Z', '2019-06-29T12:30:00Z', 0);
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, status_id, campaign_title, campaign_description, amount_goal, amount_raised, expenses_description, location, start_date, end_date, backers)
VALUES ('970972dd-dce8-4c65-a85b-63735ada0fc9', '39240e9f-ae09-4e95-9fd0-a712035c8ad7', 'bc8250bb-f7eb-4adc-925c-2af315cc4a55', '118250bb-f7eb-4adc-925c-2af315cc4a55', 'Education help needed', 'Kids from our local school need new books', 50000, 0, 'Overall education operations', 'UK, London', '2019-06-29T10:30:00Z', '2019-06-29T12:30:00Z', 0);
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, status_id, campaign_title, campaign_description, amount_goal, amount_raised, expenses_description, location, start_date, end_date, backers)
VALUES ('1c225a3a-2c70-4d95-b87f-f086cbd20366', '9e4de779-d6a0-44bc-a531-20cdb97178d2', 'bc8250bb-f7eb-4adc-925c-2af315cc4a55', '128250bb-f7eb-4adc-925c-2af315cc4a55', 'Emergency help required right away', 'Broke my hip and need money for a surgical intervention right away', 50000, 0, 'Overall emergency operations', 'UK, London', '2019-06-29T10:30:00Z', '2019-06-29T12:30:00Z', 0);
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, status_id, campaign_title, campaign_description, amount_goal, amount_raised, expenses_description, location, start_date, end_date, backers)
VALUES ('9f0bb16e-fc25-47f3-b60a-635b6224225a', '66a45c1b-19af-4ab5-8747-1b0e2d79339d', '200393bc-8aaa-45a8-9093-80c4792348cd', '138250bb-f7eb-4adc-925c-2af315cc4a55', 'Must cover medical bills', 'Teenager needs heart surgery', 50000, 0, 'Overall medical operations', 'UK, London', '2019-06-29T10:30:00Z', '2019-06-29T12:30:00Z', 0);
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, status_id, campaign_title, campaign_description, amount_goal, amount_raised, expenses_description, location, start_date, end_date, backers)
VALUES ('8f0bb16e-3225-zef3-b60a-ab5b6224225a', '29a45c1b-13af-4ab5-8747-3b0e2d72339f', '200393bc-8aaa-45a8-9093-80c4792348cd', '148250bb-f7eb-4adc-925c-2af315cc4a55', 'Animal treatment bills to be covered', 'Our hourse from the ranch requested immediate care', 50000, 0, 'Overall animal operations', 'UK, London', '2019-06-29T10:30:00Z', '2019-06-29T12:30:00Z', 0);
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, status_id, campaign_title, campaign_description, amount_goal, amount_raised, expenses_description, location, start_date, end_date, backers)
VALUES ('3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'a5a45c1b-19af-zcb5-8747-3e0e2d79330f', '200393bc-8aaa-45a8-9093-80c4792348cd', '158250bb-f7eb-4adc-925c-2af315cc4a55', 'Local community cleanup', 'Cleanup in our parks and on sidewalks', 50000, 0, 'Overall volunteering operations', 'UK, London', '2019-06-29T10:30:00Z', '2019-06-29T12:30:00Z', 0);
