DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  category_id   VARCHAR(100)   PRIMARY KEY,

  name          TEXT NOT NULL,
  description   TEXT NOT NULL,
  image_url     TEXT NOT NULL,

  status        VARCHAR(100)
  );

INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('0d60a85e-0b90-4482-a14c-108aea2557aa', 'Charity', 'Help a charity or nonprofit right away', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/wallets/charity.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('39240e9f-ae09-4e95-9fd0-a712035c8ad7', 'Education', 'Get immediate help with education costs.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/wallets/education.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('9e4de779-d6a0-44bc-a531-20cdb97178d2', 'Emergency', 'Get immediate help with emergency costs', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/wallets/emergency.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('66a45c1b-19af-4ab5-8747-1b0e2d79339d', 'Medical', 'Get immediate help with medical bills.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/wallets/medical.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('29a45c1b-13af-4ab5-8747-3b0e2d72339f', 'Animals', 'Get immediate help with animal bills.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/wallets/animals.png', 'CREATED');
INSERT INTO categories (category_id,  name, description, image_url, status)
VALUES ('a5a45c1b-19af-zcb5-8747-3e0e2d79330f', 'Volunteer', 'Get immediate help with volunteer bills.', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/wallets/volunteer.png', 'CREATED');
