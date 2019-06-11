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

/*
** Help Wallet backers
 */
DROP TABLE IF EXISTS help_wallet_backers;
CREATE TABLE help_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  help_id                       VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL
  );

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

/*
** Live Event Promise backers
*/
DROP TABLE IF EXISTS promise_wallet_backers;
CREATE TABLE promise_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  promise_id                    VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL,
  total_amount                  INT  NOT NULL
  );

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
