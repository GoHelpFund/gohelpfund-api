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

INSERT INTO fundraiser_social (social_id,  fundraiser_id, facebook, twitter, linkedin, website, other)
VALUES ('a28250bb-f7eb-4adc-925c-90f315cc4a27', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'https://www.facebook.com/daniel.dascalu/', 'https://twitter.com/daniel.dascalu', 'https://www.linkedin.com/in/daniel.dascalu/', 'https://www.daniel-dascalu.com', 'https://www.example.com');
INSERT INTO fundraiser_social (social_id,  fundraiser_id, facebook, twitter, linkedin, website, other)
VALUES ('b28250bb-f7eb-4adc-925c-90f315cc4a28', '100393bc-8aaa-45a8-9093-80c4792348c1', 'https://www.facebook.com/daniel.tirzuman/', 'https://twitter.com/daniel.tirzuman', 'https://www.linkedin.com/in/daniel.tirzuman/', 'https://www.daniel-tirzuman.com', 'https://www.example.com');
INSERT INTO fundraiser_social (social_id,  fundraiser_id, facebook, twitter, linkedin, website, other)
VALUES ('ba8250bb-f7eb-4adc-925c-90f315cc4a28', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 'https://www.facebook.com/daniel.tirzuman/', 'https://twitter.com/daniel.tirzuman', 'https://www.linkedin.com/in/daniel.tirzuman/', 'https://www.daniel-tirzuman.com', 'https://www.example.com');
INSERT INTO fundraiser_social (social_id,  fundraiser_id, facebook, twitter, linkedin, website, other)
VALUES ('c29250bb-efec-2adc-925c-aff315cc4a29', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'https://www.facebook.com/vlad.batrinu/', 'https://twitter.com/vlad.batrinu', 'https://www.linkedin.com/in/vlad.batrinu/', 'https://www.vlad-batrinu.com', 'https://www.example.com');


DROP TABLE IF EXISTS fundraiser_professional;

CREATE TABLE fundraiser_professional (
  professional_id     VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id       VARCHAR(100) NOT NULL,

  job_title           TEXT,
  job_description     TEXT,
  company_name        TEXT,
  company_url         TEXT
  );

INSERT INTO fundraiser_professional (professional_id,  fundraiser_id, job_title, job_description, company_name, company_url)
VALUES ('a38250bb-f7eb-4adc-925c-90f315cc4a27', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'Front-End Development', 'Responsible for implementing visual and interactive elements that users engage with through their web browser when using a web application', 'GoHelpFund', 'https://www.gohelpfund.com');
INSERT INTO fundraiser_professional (professional_id,  fundraiser_id, job_title, job_description, company_name, company_url)
VALUES ('b49250bb-efec-2adc-925c-aff315cc4a28', '100393bc-8aaa-45a8-9093-80c4792348c1', 'Back-End, Dev-Ops & Distributed Systems', 'Responsible for server-side application logic and software engineering to optimize and improve the performance and implementation of the development process', 'GoHelpFund', 'https://www.gohelpfund.com');
INSERT INTO fundraiser_professional (professional_id,  fundraiser_id, job_title, job_description, company_name, company_url)
VALUES ('ba9250bb-efec-2adc-925c-aff315cc4a28', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 'Back-End, Dev-Ops & Distributed Systems', 'Responsible for server-side application logic and software engineering to optimize and improve the performance and implementation of the development process', 'GoHelpFund', 'https://www.gohelpfund.com');
INSERT INTO fundraiser_professional (professional_id,  fundraiser_id, job_title, job_description, company_name, company_url)
VALUES ('c49250bb-efec-2adc-925c-aff315cc4a29', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'Mobile Development, UI & UX', 'Responsible for developing applications for mobile devices powered by Apple’s iOS operating system', 'GoHelpFund', 'https://www.gohelpfund.com');

DROP TABLE IF EXISTS fundraiser_statuses;

CREATE TABLE fundraiser_statuses (
  status_id                          VARCHAR(100) PRIMARY KEY NOT NULL,
  fundraiser_id                      VARCHAR(100) NOT NULL,

  type                               VARCHAR(100) NOT NULL,
  type_description                   TEXT         NOT NULL,
  sub_type                           VARCHAR(100) NOT NULL,
  sub_type_description               TEXT         NOT NULL

  );

INSERT INTO fundraiser_statuses (status_id, fundraiser_id,  type, type_description, sub_type, sub_type_description)
VALUES ('408250bb-f7eb-4adc-925c-2af315cc4a54', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'COMPLETED', 'User registration completed', 'ACCOUNT_LIVE', 'User is enabled.');
INSERT INTO fundraiser_statuses (status_id, fundraiser_id,  type, type_description, sub_type, sub_type_description)
VALUES ('518250bb-f7eb-4adc-925c-2af315cc4a55', '100393bc-8aaa-45a8-9093-80c4792348c1', 'COMPLETED', 'User registration completed', 'ACCOUNT_LIVE', 'User is enabled.');
INSERT INTO fundraiser_statuses (status_id, fundraiser_id,  type, type_description, sub_type, sub_type_description)
VALUES ('5a8250bb-f7eb-4adc-925c-2af315cc4a55', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 'COMPLETED', 'User registration completed', 'ACCOUNT_LIVE', 'User is enabled.');
INSERT INTO fundraiser_statuses (status_id, fundraiser_id,  type, type_description, sub_type, sub_type_description)
VALUES ('608250bb-f7eb-4adc-925c-2af315cc4a06', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'COMPLETED', 'User registration completed', 'ACCOUNT_LIVE', 'User is enabled.');



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

INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, wallet_id, status_id,  name, age, profile_image_url)
VALUES ('0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'a28250bb-f7eb-4adc-925c-90f315cc4a27', 'a38250bb-f7eb-4adc-925c-90f315cc4a27', '6yy0a75f-yb90-5482-a1de-108aea2567ay', '408250bb-f7eb-4adc-925c-2af315cc4a54', 'Daniel Dascalu', 26, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/daniel-dascalu.jpeg');
INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, wallet_id, status_id, name, age, profile_image_url)
VALUES ('100393bc-8aaa-45a8-9093-80c4792348c1', 'b28250bb-f7eb-4adc-925c-90f315cc4a28', 'b49250bb-efec-2adc-925c-aff315cc4a28', '7yy0a75f-yb90-5482-a1de-108aea2567ay', '518250bb-f7eb-4adc-925c-2af315cc4a55', 'Daniel Tirzuman', 26, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/daniel-tirzuman.jpg');
INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, wallet_id, status_id, name, age, profile_image_url)
VALUES ('1a0393bc-8aaa-45a8-9093-80c4792348c1', 'ba8250bb-f7eb-4adc-925c-90f315cc4a28', 'ba9250bb-efec-2adc-925c-aff315cc4a28', '7ay0a75f-yb90-5482-a1de-108aea2567ay', '5a8250bb-f7eb-4adc-925c-2af315cc4a55', 'Daniel Nicolae', 26, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/daniel-tirzuman.jpg');
INSERT INTO fundraisers (fundraiser_id, social_id, professional_id, wallet_id, status_id, name, age, profile_image_url)
VALUES ('2d0343bc-9afa-45a8-6043-e0c7792348z2', 'c29250bb-efec-2adc-925c-aff315cc4a29', 'c49250bb-efec-2adc-925c-aff315cc4a29', '8yy0a75f-yb90-5482-a1de-108aea2567ay', '608250bb-f7eb-4adc-925c-2af315cc4a06', 'Vlad Batrinu', 26, 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/vlad-batrinu.jpg');
