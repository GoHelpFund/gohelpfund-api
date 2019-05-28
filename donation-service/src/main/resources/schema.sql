/*
** wallet type: HELP
** - used in campaigns -
 */
DROP TABLE IF EXISTS help_wallet_details;
CREATE TABLE help_wallet_details (
  help_id             VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id           VARCHAR(100) NOT NULL,

  public_key          TEXT,
  private_key         TEXT,
  balance             INT
  );
-- Campaign Help wallets
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('0zz0a75f-9b90-5482-a1de-108aea2567az', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'XxRUQGKNWzfVCsAE67aVDwcGyzjYnxWcw4', 'X', 15);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('1zz0a75f-9b90-5482-a1de-108aea2567az', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'XtnUkPrjrr8R9MuxpwoJ34MMVJHrR2MUXR', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('2zz0a75f-9b90-5482-a1de-108aea2567az', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'XnJP6YtgmYA2N27aR156N3T6rPm8hY8dSG', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('3zz0a75f-9b90-5482-a1de-108aea2567az', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'XxxCr1B7VqPxmQa94qhHxbQnxzKhnEARp6', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('4zz0a75f-9b90-5482-a1de-108aea2567az', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'XuJMWq3swyQg9wsmAiG2q38ryu8dYM3uHK', 'X', 0);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('5zz0a75f-9b90-5482-a1de-108aea2567az', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'Xr3sSqQ6AUQN7SLA42n3kpXNeKmMJccN4r', 'X', 0);
-- Fundraiser Help wallets
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('6zz0a75f-9b90-5482-a1de-108aea2567az', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'XioSH8UFdXYwf2S5tM24RLcqVjxZNKvvqp', 'X', 100);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('7zz0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1', 'Xtnagy4Fm8nQck9Qbx5RtDTHap4Y3okTGr', 'X', 85);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('8zz0a75f-9b90-5482-a1de-108aea2567az', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'Xyrc5v6kU3xs9yjrVwQDEHW7mb6UfcYTLH', 'X', 100);
-- Event Help wallets
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('9zz0a75f-9b90-5482-a1de-108aea2567az', 'bal81764-bea1-4249-b86d-f8fb8182eec1', 'XnnS8BdLC6Zh65kSV17kwyR4H1FQBfT95Z', 'X', 0);

/*
** Help Wallet donations
 */
DROP TABLE IF EXISTS help_wallet_transactions;
CREATE TABLE help_wallet_transactions (
  transaction_id                VARCHAR(100) PRIMARY KEY NOT NULL,
  help_id                       VARCHAR(100),

  transaction_date              TIMESTAMP WITH TIME ZONE,
  transaction_type              VARCHAR(100) NOT NULL,
  blockchain_transaction_id     TEXT NOT NULL,
  amount                        INT  NOT NULL,
  sender_help_id                VARCHAR(100) NOT NULL,
  receiver_help_id              VARCHAR(100) NOT NULL,
  sender_name                   TEXT,
  sender_address                TEXT
  );
-- Campaign Help donations
INSERT INTO help_wallet_transactions (transaction_id, help_id, transaction_date, transaction_type, blockchain_transaction_id, amount, sender_help_id, receiver_help_id, sender_name, sender_address)
VALUES ('0aa0a76f-0b90-0482-a0dd-008aea2567ff', '7zz0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'sent', 'ee70a38fb436cece5916b9e45ae7553fa4edeee0a0eabc77d208b0f002911e6f', 15, '7zz0a75f-9b90-5482-a1de-108aea2567az', '0zz0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman', 'Xtnagy4Fm8nQck9Qbx5RtDTHap4Y3okTGr');
INSERT INTO help_wallet_transactions (transaction_id, help_id, transaction_date, transaction_type, blockchain_transaction_id, amount, sender_help_id, receiver_help_id, sender_name, sender_address)
VALUES ('1aa0a76f-0b90-0482-a0dd-008aea2567ff', '0zz0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'received', 'ee70a38fb436cece5916b9e45ae7553fa4edeee0a0eabc77d208b0f002911e6f', 15, '7zz0a75f-9b90-5482-a1de-108aea2567az', '0zz0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman', 'Xtnagy4Fm8nQck9Qbx5RtDTHap4Y3okTGr');

/*
** Help Wallet backers
 */
DROP TABLE IF EXISTS help_wallet_backers;
CREATE TABLE help_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  help_id                       VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL
  );
-- Campaign Help backer
INSERT INTO help_wallet_backers (backer_id,  help_id, fundraiser_id)
VALUES ('0xx4a76f-0240-5782-70dd-008aea2567fz', '0zz0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1');

/*
** wallet type: PROMISE
** - used in live events -
 */
DROP TABLE IF EXISTS promise_wallet_details;
CREATE TABLE promise_wallet_details (
  promise_id          VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id           VARCHAR(100) NOT NULL,

  balance             INT
  );
-- Fundraiser Promise wallets
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('6ff0a75f-9b90-5482-a1de-108aea2567az', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 1000000);
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('7ff0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1', 995000);
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('8ff0a75f-9b90-5482-a1de-108aea2567az', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 1000000);
-- Event Promise wallets
INSERT INTO promise_wallet_details (promise_id,  entity_id,  balance)
VALUES ('9ff0a75f-9b90-5482-a1de-108aea2567az', 'bal81764-bea1-4249-b86d-f8fb8182eec1', 5000);

/*
** Live Event Promise donations
*/
DROP TABLE IF EXISTS promise_wallet_transactions;
CREATE TABLE promise_wallet_transactions (
  transaction_id                VARCHAR(100) PRIMARY KEY NOT NULL,
  promise_id                    VARCHAR(100),

  transaction_date              TIMESTAMP WITH TIME ZONE,
  transaction_type              VARCHAR(100) NOT NULL,
  amount                        INT  NOT NULL,
  sender_promise_id             VARCHAR(100) NOT NULL,
  receiver_promise_id           VARCHAR(100) NOT NULL,
  sender_name                   TEXT
  );
INSERT INTO promise_wallet_transactions (transaction_id, promise_id, transaction_date, transaction_type, amount, sender_promise_id, receiver_promise_id, sender_name)
VALUES ('0ee0a76f-0b90-0482-a0dd-008aea2567ff', '7ff0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'sent', 5000, '7ff0a75f-9b90-5482-a1de-108aea2567az', '9ff0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman');
INSERT INTO promise_wallet_transactions (transaction_id, promise_id, transaction_date, transaction_type, amount, sender_promise_id, receiver_promise_id, sender_name)
VALUES ('1ee0a76f-0b90-0482-a0dd-008aea2567ff', '9ff0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'received', 5000, '7ff0a75f-9b90-5482-a1de-108aea2567az', '9ff0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman');

/*
** Live Event Promise backers
*/
DROP TABLE IF EXISTS promise_wallet_backers;
CREATE TABLE promise_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  promise_id                    VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL
  );
-- Live Event Promise backer
INSERT INTO promise_wallet_backers (backer_id,  promise_id, fundraiser_id)
VALUES ('0vv4a76f-0240-5782-70dd-008aea2567fz', '9ff0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1');

/*
** Wallets
*/
DROP TABLE IF EXISTS wallets;
CREATE TABLE wallets (
  wallet_id            VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id            VARCHAR(100) NOT NULL,
  type                 VARCHAR(100) NOT NULL,

  help_id              VARCHAR(100),
  promise_id           VARCHAR(100)
  );
-- Campaign wallets
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('0yy0a75f-yb90-5482-a1de-108aea2567ay', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'campaign', '0zz0a75f-9b90-5482-a1de-108aea2567az', '0ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('1yy0a75f-yb90-5482-a1de-108aea2567ay', '970972dd-dce8-4c65-a85b-63735ada0fc9', 'campaign', '1zz0a75f-9b90-5482-a1de-108aea2567az', '1ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('2yy0a75f-yb90-5482-a1de-108aea2567ay', '1c225a3a-2c70-4d95-b87f-f086cbd20366', 'campaign', '2zz0a75f-9b90-5482-a1de-108aea2567az', '2ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id )
VALUES ('3yy0a75f-yb90-5482-a1de-108aea2567ay', '9f0bb16e-fc25-47f3-b60a-635b6224225a', 'campaign', '3zz0a75f-9b90-5482-a1de-108aea2567az', '3ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('4yy0a75f-yb90-5482-a1de-108aea2567ay', '8f0bb16e-3225-zef3-b60a-ab5b6224225a', 'campaign', '4zz0a75f-9b90-5482-a1de-108aea2567az', '4ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('5yy0a75f-yb90-5482-a1de-108aea2567ay', '3f0bb16e-4a25-cdf3-b60a-275b6224225a', 'campaign', '5zz0a75f-9b90-5482-a1de-108aea2567az', '5ff0a75f-9b90-5482-a1de-108aea2567az');
-- Fundraiser wallets
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('6yy0a75f-yb90-5482-a1de-108aea2567ay', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'fundraiser', '6zz0a75f-9b90-5482-a1de-108aea2567az', '6ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('7yy0a75f-yb90-5482-a1de-108aea2567ay', '100393bc-8aaa-45a8-9093-80c4792348c1', 'fundraiser', '7zz0a75f-9b90-5482-a1de-108aea2567az', '7ff0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('8yy0a75f-yb90-5482-a1de-108aea2567ay', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'fundraiser', '8zz0a75f-9b90-5482-a1de-108aea2567az', '8ff0a75f-9b90-5482-a1de-108aea2567az');
-- Live Event wallets
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('9yy0a75f-yb90-5482-a1de-108aea2567ay', 'bal81764-bea1-4249-b86d-f8fb8182eec1', 'event', '9zz0a75f-9b90-5482-a1de-108aea2567az', '9ff0a75f-9b90-5482-a1de-108aea2567az');