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


INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ua10c636-936e-4dd6-b9d5-f340329142bd', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 150, 'Brand new sweater');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ua20c636-936e-4dd6-b9d5-f340329142bd', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 250, 'Comfy shoes');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ua30c636-936e-4dd6-b9d5-f340329142bd', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 100, 'A pair of pants');

INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ub10c636-936e-4dd6-b9d5-f340329142bd', '970972dd-dce8-4c65-a85b-63735ada0fc9', 250, 'Math books');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ub20c636-936e-4dd6-b9d5-f340329142bd', '970972dd-dce8-4c65-a85b-63735ada0fc9', 250, 'English books');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ub30c636-936e-4dd6-b9d5-f340329142bd', '970972dd-dce8-4c65-a85b-63735ada0fc9', 250, 'Design books');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ub40c636-936e-4dd6-b9d5-f340329142bd', '970972dd-dce8-4c65-a85b-63735ada0fc9', 250, 'Chemistry books');

INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('uc10c636-936e-4dd6-b9d5-f340329142bd', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 15000, 'Temporary shelters');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('uc20c636-936e-4dd6-b9d5-f340329142bd', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 35000, 'Overall Food operations');

INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ud10c636-936e-4dd6-b9d5-f340329142bd', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 15000, 'Overall surgery costs');

INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ue10c636-936e-4dd6-b9d5-f340329142bd', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 15000, 'Overall food');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('ue20c636-936e-4dd6-b9d5-f340329142bd', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 35000, 'Medical treatment costs');

INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('uf10c636-936e-4dd6-b9d5-f340329142bd', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 1000, 'Materials');
INSERT INTO campaign_expenses (expense_id,  campaign_id, amount, description)
VALUES ('uf20c636-936e-4dd6-b9d5-f340329142bd', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 1000, 'Cleaning items');


INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, avatar, status)
VALUES ('9788c636-936e-4dd6-b9d5-f340329142bd', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'Image 1', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/4959707A-C6C8-4490-92DE-9759195B6322.jpg', 'image', 'jpg', true, 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, avatar, status)
VALUES ('3d8b664f-ef5f-4587-a45c-f2991a1fc029', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'Image 2', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/6959707A-C6C8-4490-92DE-9759195B6322.jpg', 'image', 'jpg', true, 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, avatar, status)
VALUES ('f618f5a8-380e-44fb-9b4e-b3286f29dcc8', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'Image 3', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/9959707A-C6C8-4490-92DE-9759195B6322.jpg', 'image', 'jpg', true, 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, avatar, status)
VALUES ('35cb41dd-3edb-4483-a1fe-fc315243d2f8', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'Photo 1', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/7959707A-C6C8-4490-92DE-9759195B6322.jpg', 'image', 'jpg', true, 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, avatar, status)
VALUES ('74b37cd4-75aa-4871-b17b-a5160428e589', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'Photo 2', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/8959707A-C6C8-4490-92DE-9759195B6322.jpg', 'image', 'jpg', true, 'CREATED');
INSERT INTO campaign_media_resources (resource_id,  campaign_id, name, url, type, format, avatar, status)
VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'Video 1', 'https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/5959707A-C6C8-4490-92DE-9759195B6322.jpg', 'image', 'jpg', true, 'CREATED');


INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('108250bb-f7eb-4adc-925c-2af315cc4a55', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'PENDING', 'Campaign is not public', 'VERIFICATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('118250bb-f7eb-4adc-925c-2af315cc4a55', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'PENDING', 'Campaign is not public', 'VERIFICATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('128250bb-f7eb-4adc-925c-2af315cc4a55', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'PENDING', 'Campaign is not public', 'VERIFICATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('138250bb-f7eb-4adc-925c-2af315cc4a55', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'PENDING', 'Campaign is not public', 'VERIFICATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('148250bb-f7eb-4adc-925c-2af315cc4a55', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'PENDING', 'Campaign is not public', 'VERIFICATION_REQUIRED', 'Campaign created as an guest - user needs to register');
INSERT INTO campaign_statuses (status_id, campaign_id,  type, type_description, sub_type, sub_type_description)
VALUES ('158250bb-f7eb-4adc-925c-2af315cc4a55', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'PENDING', 'Campaign is not public', 'VERIFICATION_REQUIRED', 'Campaign created as an guest - user needs to register');


INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, wallet_id, status_id, campaign_title, campaign_description, amount_goal, location, start_date, end_date)
VALUES ('cd881764-bea1-4249-b86d-f8fb8182eec1', '0d60a85e-0b90-4482-a14c-108aea2557aa', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', '0yy0a75f-yb90-5482-a1de-108aea2567ay', '108250bb-f7eb-4adc-925c-2af315cc4a55', 'Sarah needs new clothes', 'School is starting next month and we need new clothes for Sarah', 500, 'UK, London', '2019-05-20T10:30:00Z', '2019-09-29T12:30:00Z');
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, wallet_id, status_id, campaign_title, campaign_description, amount_goal, location, start_date, end_date)
VALUES ('970972dd-dce8-4c65-a85b-63735ada0fc9', '39240e9f-ae09-4e95-9fd0-a712035c8ad7', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', '1yy0a75f-yb90-5482-a1de-108aea2567ay', '118250bb-f7eb-4adc-925c-2af315cc4a55', 'New books for 4th graders', 'Kids from our local school need new books as they enter in the 4th grade', 1000, 'UK, London', '2019-05-23T10:30:00Z', '2019-09-27T12:30:00Z');
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, wallet_id, status_id, campaign_title, campaign_description, amount_goal, location, start_date, end_date)
VALUES ('1c225a3a-2c70-4d95-b87f-f086cbd20366', '9e4de779-d6a0-44bc-a531-20cdb97178d2', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', '2yy0a75f-yb90-5482-a1de-108aea2567ay', '128250bb-f7eb-4adc-925c-2af315cc4a55', 'Help shelter the people from earthquake disaster', 'A massive earthquake rocked Japan’s northernmost province early last night, leaving people withour a home.', 50000, 'UK, London', '2019-05-11T10:30:00Z', '2019-10-29T12:30:00Z');
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, wallet_id, status_id, campaign_title, campaign_description, amount_goal, location, start_date, end_date)
VALUES ('9f0bb16e-fc25-47f3-b60a-635b6224225a', '66a45c1b-19af-4ab5-8747-1b0e2d79339d', '100393bc-8aaa-45a8-9093-80c4792348c1', '3yy0a75f-yb90-5482-a1de-108aea2567ay', '138250bb-f7eb-4adc-925c-2af315cc4a55', 'Operation Smile for kids with genetic defects', 'The last month campaign was a success and we want to fundraise for another 3 wonderful childs that are in need of a surgery.', 15000, 'UK, London', '2019-05-11T10:30:00Z', '2019-10-11T12:30:00Z');
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, wallet_id, status_id, campaign_title, campaign_description, amount_goal, location, start_date, end_date)
VALUES ('8f0bb16e-3225-zef3-b60a-ab5b6224225a', '29a45c1b-13af-4ab5-8747-3b0e2d72339f', '100393bc-8aaa-45a8-9093-80c4792348c1', '4yy0a75f-yb90-5482-a1de-108aea2567ay', '148250bb-f7eb-4adc-925c-2af315cc4a55', 'Abandoned horse found', 'A mistreated and subnutrited horse arrived at our shelter and we need to take care of him and bring him in a better condition', 50000, 'UK, London', '2019-05-11T10:30:00Z', '2019-09-29T12:30:00Z');
INSERT INTO campaigns (campaign_id, category_id, fundraiser_id, wallet_id, status_id, campaign_title, campaign_description, amount_goal, location, start_date, end_date)
VALUES ('3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'a5a45c1b-19af-zcb5-8747-3e0e2d79330f', '2d0343bc-9afa-45a8-6043-e0c7792348z2', '5yy0a75f-yb90-5482-a1de-108aea2567ay', '158250bb-f7eb-4adc-925c-2af315cc4a55', 'Local community cleanup', 'We have started an initiative to cleanup our parks and benches from bus stations', 2000, 'UK, London', '2019-05-10T10:30:00Z', '2019-09-16T12:30:00Z');


INSERT INTO events (event_id, fundraiser_id, wallet_id, event_title, event_description, location, start_date)
VALUES ('bal81764-bea1-4249-b86d-f8fb8182eec1',  '0c8250bb-f7eb-4adc-925c-2af315cc4a50', '9yy0a75f-yb90-5482-a1de-108aea2567ay', 'Balul de la Castel', 'Balul de la Castel', 'RO, Iasi', '2019-06-13T19:00:00Z');


INSERT INTO event_attendance (attendance_id, event_id, fundraiser_id, fundraiser_name, fundraiser_type, table_id)
VALUES ('ev081764-bea1-4249-b86d-f8fb8182eec0',  'bal81764-bea1-4249-b86d-f8fb8182eec1', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'Daniel Dascalu', 'natural', '0');
INSERT INTO event_attendance (attendance_id, event_id, fundraiser_id, fundraiser_name, fundraiser_type, table_id)
VALUES ('ev181764-bea1-4249-b86d-f8fb8182eec0',  'bal81764-bea1-4249-b86d-f8fb8182eec1', '100393bc-8aaa-45a8-9093-80c4792348c1', 'Daniel Tirzuman', 'natural', '0');
INSERT INTO event_attendance (attendance_id, event_id, fundraiser_id, fundraiser_name, fundraiser_type, table_id)
VALUES ('ev281764-bea1-4249-b86d-f8fb8182eec0',  'bal81764-bea1-4249-b86d-f8fb8182eec1', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'Vlad Batrinu', 'natural', '0');
