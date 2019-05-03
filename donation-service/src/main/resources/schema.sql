DROP TABLE IF EXISTS help_wallet_details;

CREATE TABLE help_wallet_details (
  help_id             VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id           VARCHAR(100) NOT NULL,

  public_key          TEXT,
  private_key         TEXT,
  balance             INT
  );

INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('0zz0a75f-9b90-5482-a1de-108aea2567az', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'XuetpmLUeAvLUsitpyfZgmuyxfoAGPEjfU', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('1zz0a75f-9b90-5482-a1de-108aea2567az', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'XuetpmLUeAvLUsitpyfZgmuyxfoAGPEjfU', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('2zz0a75f-9b90-5482-a1de-108aea2567az', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'XuetpmLUeAvLUsitpyfZgmuyxfoAGPEjfU', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('3zz0a75f-9b90-5482-a1de-108aea2567az', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'XuetpmLUeAvLUsitpyfZgmuyxfoAGPEjfU', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('4zz0a75f-9b90-5482-a1de-108aea2567az', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'XuetpmLUeAvLUsitpyfZgmuyxfoAGPEjfU', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('5zz0a75f-9b90-5482-a1de-108aea2567az', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'XuetpmLUeAvLUsitpyfZgmuyxfoAGPEjfU', 'X', 0);

INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('6zz0a75f-9b90-5482-a1de-108aea2567az', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'Xn2WXYAZv2k3XTu4emKokXjBG9zT4ZG4hf', 'X', 100);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('7zz0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1', 'XcWjZL4caQeSiRVsJd4Zzqd1FyBjhF2YRZ', 'X', 100);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('8zz0a75f-9b90-5482-a1de-108aea2567az', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'Xk8vW54TEoyyxaxs1XxzCngk32LRpmRFQX', 'X', 100);



DROP TABLE IF EXISTS wallets;

CREATE TABLE wallets (
  wallet_id            VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id            VARCHAR(100) NOT NULL,
  type                 VARCHAR(100) NOT NULL,

  help_id              VARCHAR(100)
  );

INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('0yy0a75f-yb90-5482-a1de-108aea2567ay', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'campaign', '0zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('1yy0a75f-yb90-5482-a1de-108aea2567ay', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'campaign', '1zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('2yy0a75f-yb90-5482-a1de-108aea2567ay', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'campaign', '2zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('3yy0a75f-yb90-5482-a1de-108aea2567ay', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'campaign', '3zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('4yy0a75f-yb90-5482-a1de-108aea2567ay', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'campaign', '4zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('5yy0a75f-yb90-5482-a1de-108aea2567ay', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'campaign', '5zz0a75f-9b90-5482-a1de-108aea2567az');

INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('6yy0a75f-yb90-5482-a1de-108aea2567ay', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'fundraiser', '6zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('7yy0a75f-yb90-5482-a1de-108aea2567ay', '100393bc-8aaa-45a8-9093-80c4792348c1', 'fundraiser', '7zz0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id)
VALUES ('8yy0a75f-yb90-5482-a1de-108aea2567ay', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'fundraiser', '8zz0a75f-9b90-5482-a1de-108aea2567az');
