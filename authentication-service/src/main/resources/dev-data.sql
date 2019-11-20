INSERT INTO users(user_id, fundraiser_id, fundraiser_type, user_name, email, password, password_changed, enabled) VALUES ('748250bb-f7eb-4adc-925c-2af315cc4a55', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'person', 'daniel.dascalu@gohelpfund.com', 'daniel.dascalu@gohelpfund.com', '$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true, true);
INSERT INTO users(user_id, fundraiser_id, fundraiser_type, user_name, email, password, password_changed, enabled) VALUES ('848250bb-f7eb-4adc-925c-2af315cc4a55', '100393bc-8aaa-45a8-9093-80c4792348c1', 'person', 'daniel.tirzuman@gohelpfund.com', 'daniel.tirzuman@gohelpfund.com', '$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true, true);
INSERT INTO users(user_id, fundraiser_id, fundraiser_type, user_name, email, password, password_changed, enabled) VALUES ('8a8250bb-f7eb-4adc-925c-2af315cc4a55', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 'person', 'daniel.nicolae@gohelpfund.com', 'daniel.nicolae@gohelpfund.com', '$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true, true);
INSERT INTO users(user_id, fundraiser_id, fundraiser_type, user_name, email, password, password_changed, enabled) VALUES ('948250bb-f7eb-4adc-925c-2af315cc4a55', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'person', 'vlad.batrinu@gohelpfund.com', 'vlad.batrinu@gohelpfund.com', '$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true, true);


INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('038250bb-f7eb-4adc-925c-2af315cc4a00', 'daniel.dascalu@gohelpfund.com',  'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('172250bb-f7eb-4adc-925c-2af315cc4a01', 'daniel.dascalu@gohelpfund.com',  'ROLE_BACKER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('048250bb-f7eb-4adc-925c-2af315cc4a00', 'daniel.dascalu@gohelpfund.com',  'ROLE_EVENT_MANAGER');

INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('249250bb-f7eb-4adc-925c-2af315cc4a02', 'daniel.tirzuman@gohelpfund.com', 'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('348250bb-f7eb-4afc-925c-2af315cc4a03', 'daniel.tirzuman@gohelpfund.com', 'ROLE_BACKER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('358250bb-f7eb-4afc-925c-2af315cc4a03', 'daniel.tirzuman@gohelpfund.com', 'ROLE_EVENT_MANAGER');

INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('2a9250bb-f7eb-4adc-925c-2af315cc4a02', 'daniel.nicolae@gohelpfund.com', 'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('3a8250bb-f7eb-4afc-925c-2af315cc4a03', 'daniel.nicolae@gohelpfund.com', 'ROLE_BACKER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('4a8250bb-f7eb-4afc-925c-2af315cc4a03', 'daniel.nicolae@gohelpfund.com', 'ROLE_EVENT_MANAGER');

INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('448250bb-f7ez-4adc-925c-2af315cc4a04', 'vlad.batrinu@gohelpfund.com',    'ROLE_FUNDRAISER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('548250bb-f7eb-4adc-925c-2af315cc4a05', 'vlad.batrinu@gohelpfund.com',    'ROLE_BACKER');
INSERT INTO user_roles (user_role_id, user_name, role) VALUES ('558250bb-f7eb-4adc-925c-2af315cc4a05', 'vlad.batrinu@gohelpfund.com',    'ROLE_EVENT_MANAGER');


-- insert client details
INSERT INTO oauth_client_details
   (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES
   ('gohelpfund', 'ghfsecret', 'web-client,mobile-client', 'refresh_token,password,client_credentials', NULL, 36000000, 76000000);

