DROP TABLE IF EXISTS bitcoin_wallet_details;
DROP TABLE IF EXISTS bitcoin_wallet_transactions;
DROP TABLE IF EXISTS bitcoin_wallet_backers;
DROP TABLE IF EXISTS help_wallet_details;
DROP TABLE IF EXISTS help_wallet_transactions;
DROP TABLE IF EXISTS help_wallet_backers;
DROP TABLE IF EXISTS promise_wallet_details;
DROP TABLE IF EXISTS promise_wallet_transactions;
DROP TABLE IF EXISTS promise_wallet_backers;
DROP TABLE IF EXISTS wallets;

/*
** wallet type: BITCOIN
** - used in campaigns -
 */
CREATE TABLE bitcoin_wallet_details (
  bitcoin_id          VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id           VARCHAR(100) NOT NULL,

  public_key          TEXT,
  private_key         TEXT,
  balance             NUMERIC
  );

/*
** Bitcoin Wallet donations
 */
CREATE TABLE bitcoin_wallet_transactions (
  transaction_id                VARCHAR(100) PRIMARY KEY NOT NULL,
  bitcoin_id                    VARCHAR(100),

  transaction_date              TIMESTAMP WITH TIME ZONE,
  transaction_type              VARCHAR(100) NOT NULL,
  blockchain_transaction_id     TEXT NOT NULL,
  amount                        NUMERIC  NOT NULL,
  sender_bitcoin_id             VARCHAR(100) NOT NULL,
  receiver_bitcoin_id           VARCHAR(100) NOT NULL,
  sender_name                   TEXT,
  sender_address                TEXT
  );

/*
** Bitcoin Wallet backers
 */
CREATE TABLE bitcoin_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  bitcoin_id                    VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL
  );


/*
** wallet type: HELP
** - used in campaigns -
 */
CREATE TABLE help_wallet_details (
  help_id             VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id           VARCHAR(100) NOT NULL,

  public_key          TEXT,
  private_key         TEXT,
  balance             NUMERIC
  );

/*
** Help Wallet donations
 */
CREATE TABLE help_wallet_transactions (
  transaction_id                VARCHAR(100) PRIMARY KEY NOT NULL,
  help_id                       VARCHAR(100),

  transaction_date              TIMESTAMP WITH TIME ZONE,
  transaction_type              VARCHAR(100) NOT NULL,
  blockchain_transaction_id     TEXT NOT NULL,
  amount                        NUMERIC  NOT NULL,
  sender_help_id                VARCHAR(100) NOT NULL,
  receiver_help_id              VARCHAR(100) NOT NULL,
  sender_name                   TEXT,
  sender_address                TEXT
  );

/*
** Help Wallet backers
 */
CREATE TABLE help_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  help_id                       VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL
  );

/*
** wallet type: PROMISE
** - used in live events -
 */
CREATE TABLE promise_wallet_details (
  promise_id          VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id           VARCHAR(100) NOT NULL,

  balance             NUMERIC
  );

/*
** Live Event Promise donations
*/
CREATE TABLE promise_wallet_transactions (
  transaction_id                VARCHAR(100) PRIMARY KEY NOT NULL,
  promise_id                    VARCHAR(100),

  transaction_date              TIMESTAMP WITH TIME ZONE,
  transaction_type              VARCHAR(100) NOT NULL,
  amount                        NUMERIC  NOT NULL,
  sender_promise_id             VARCHAR(100) NOT NULL,
  receiver_promise_id           VARCHAR(100) NOT NULL,
  sender_name                   TEXT
  );

/*
** Live Event Promise backers
*/
CREATE TABLE promise_wallet_backers (
  backer_id                     VARCHAR(100) PRIMARY KEY NOT NULL,
  promise_id                    VARCHAR(100),

  fundraiser_id                 VARCHAR(100) NOT NULL,
  total_amount                  NUMERIC  NOT NULL
  );

/*
** Wallets
*/
CREATE TABLE wallets (
  wallet_id            VARCHAR(100) PRIMARY KEY NOT NULL,
  entity_id            VARCHAR(100) NOT NULL,
  type                 VARCHAR(100) NOT NULL,

  bitcoin_id           VARCHAR(100),
  help_id              VARCHAR(100),
  promise_id           VARCHAR(100)
  );
