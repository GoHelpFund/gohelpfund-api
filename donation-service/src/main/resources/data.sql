-- Campaign Help wallets
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('0zz0a75f-9b90-5482-a1de-108aea2567az', 'cd881764-bea1-4249-b86d-f8fb8182eec1', 'XxRUQGKNWzfVCsAE67aVDwcGyzjYnxWcw4', 'X', 18);
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
VALUES ('6zz0a75f-9b90-5482-a1de-108aea2567az', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 'Xq6mADtA9LqCpWKCJC91ifTtVWoT7XLVFc', 'X', 10);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('7zz0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1', 'XoJUYPobcZHwEJrp5fg6e6ZQ36oSPDK4bB', 'X', 10);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('7az0a75f-9b90-5482-a1de-108aea2567az', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 'XkeB44eBFB9eGG1q7Rojo66x4KFQKk7fB9', 'X', 10);
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('8zz0a75f-9b90-5482-a1de-108aea2567az', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'Xi4XuWfCg6qKAYvtw8WBuCRkofoUTpfZ1c', 'X', 10);
-- Event Help wallets
INSERT INTO help_wallet_details (help_id,  entity_id, public_key, private_key, balance)
VALUES ('9zz0a75f-9b90-5482-a1de-108aea2567az', 'bal81764-bea1-4249-b86d-f8fb8182eec1', 'XnnS8BdLC6Zh65kSV17kwyR4H1FQBfT95Z', 'X', 0);


-- Campaign Help donations
INSERT INTO help_wallet_transactions (transaction_id, help_id, transaction_date, transaction_type, blockchain_transaction_id, amount, sender_help_id, receiver_help_id, sender_name, sender_address)
VALUES ('0aa0a76f-0b90-0482-a0dd-008aea2567ff', '7zz0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'sent', 'ee70a38fb436cece5916b9e45ae7553fa4edeee0a0eabc77d208b0f002911e6f', 15, '7zz0a75f-9b90-5482-a1de-108aea2567az', '0zz0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman', 'Xtnagy4Fm8nQck9Qbx5RtDTHap4Y3okTGr');
INSERT INTO help_wallet_transactions (transaction_id, help_id, transaction_date, transaction_type, blockchain_transaction_id, amount, sender_help_id, receiver_help_id, sender_name, sender_address)
VALUES ('1aa0a76f-0b90-0482-a0dd-008aea2567ff', '0zz0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'received', 'ee70a38fb436cece5916b9e45ae7553fa4edeee0a0eabc77d208b0f002911e6f', 15, '7zz0a75f-9b90-5482-a1de-108aea2567az', '0zz0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman', 'Xtnagy4Fm8nQck9Qbx5RtDTHap4Y3okTGr');

INSERT INTO help_wallet_transactions (transaction_id, help_id, transaction_date, transaction_type, blockchain_transaction_id, amount, sender_help_id, receiver_help_id, sender_name, sender_address)
VALUES ('2aa0a76f-0b90-0482-a0dd-008aea2567ff', '7zz0a75f-9b90-5482-a1de-108aea2567az', '2019-05-23T10:34:03Z', 'sent', '2c80903f3fdef1d3ec85a307690939c3c7990f2bb20c432d2efd3a7a9dff2a73', 3, '7az0a75f-9b90-5482-a1de-108aea2567az', '0zz0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Nicolae', 'XnHTBkoaah6htCzTbK6Ew2LgJZUh22PhCh');
INSERT INTO help_wallet_transactions (transaction_id, help_id, transaction_date, transaction_type, blockchain_transaction_id, amount, sender_help_id, receiver_help_id, sender_name, sender_address)
VALUES ('3aa0a76f-0b90-0482-a0dd-008aea2567ff', '0zz0a75f-9b90-5482-a1de-108aea2567az', '2019-05-23T10:34:03Z', 'received', '2c80903f3fdef1d3ec85a307690939c3c7990f2bb20c432d2efd3a7a9dff2a73', 3, '7az0a75f-9b90-5482-a1de-108aea2567az', '0zz0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Nicolae', 'XnHTBkoaah6htCzTbK6Ew2LgJZUh22PhCh');


-- Campaign Help backer
INSERT INTO help_wallet_backers (backer_id,  help_id, fundraiser_id)
VALUES ('0xx4a76f-0240-5782-70dd-008aea2567fz', '0zz0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1');
INSERT INTO help_wallet_backers (backer_id,  help_id, fundraiser_id)
VALUES ('1xx4a76f-0240-5782-70dd-008aea2567fz', '0zz0a75f-9b90-5482-a1de-108aea2567az', '1a0393bc-8aaa-45a8-9093-80c4792348c1');


-- Fundraiser Promise wallets
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('6ff0a75f-9b90-5482-a1de-108aea2567az', '0c8250bb-f7eb-4adc-925c-2af315cc4a50', 1000000);
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('7ff0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1', 810000);
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('7af0a75f-9b90-5482-a1de-108aea2567az', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 1000000);
INSERT INTO promise_wallet_details (promise_id, entity_id, balance)
VALUES ('8ff0a75f-9b90-5482-a1de-108aea2567az', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 1000000);
-- Event Promise wallets
INSERT INTO promise_wallet_details (promise_id,  entity_id,  balance)
VALUES ('9ff0a75f-9b90-5482-a1de-108aea2567az', 'bal81764-bea1-4249-b86d-f8fb8182eec1', 190000);


INSERT INTO promise_wallet_transactions (transaction_id, promise_id, transaction_date, transaction_type, amount, sender_promise_id, receiver_promise_id, sender_name)
VALUES ('0ee0a76f-0b90-0482-a0dd-008aea2567ff', '7ff0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'sent', 190000, '7ff0a75f-9b90-5482-a1de-108aea2567az', '9ff0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman');
INSERT INTO promise_wallet_transactions (transaction_id, promise_id, transaction_date, transaction_type, amount, sender_promise_id, receiver_promise_id, sender_name)
VALUES ('1ee0a76f-0b90-0482-a0dd-008aea2567ff', '9ff0a75f-9b90-5482-a1de-108aea2567az', '2019-05-22T18:58:57Z', 'received', 190000, '7ff0a75f-9b90-5482-a1de-108aea2567az', '9ff0a75f-9b90-5482-a1de-108aea2567az', 'Daniel Tirzuman');


-- Live Event Promise backer
INSERT INTO promise_wallet_backers (backer_id,  promise_id, fundraiser_id, total_amount)
VALUES ('0vv4a76f-0240-5782-70dd-008aea2567fz', '9ff0a75f-9b90-5482-a1de-108aea2567az', '100393bc-8aaa-45a8-9093-80c4792348c1', 190000);


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
VALUES ('7ay0a75f-yb90-5482-a1de-108aea2567ay', '1a0393bc-8aaa-45a8-9093-80c4792348c1', 'fundraiser', '7az0a75f-9b90-5482-a1de-108aea2567az', '7af0a75f-9b90-5482-a1de-108aea2567az');
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('8yy0a75f-yb90-5482-a1de-108aea2567ay', '2d0343bc-9afa-45a8-6043-e0c7792348z2', 'fundraiser', '8zz0a75f-9b90-5482-a1de-108aea2567az', '8ff0a75f-9b90-5482-a1de-108aea2567az');
-- Live Event wallets
INSERT INTO wallets (wallet_id,  entity_id, type, help_id, promise_id)
VALUES ('9yy0a75f-yb90-5482-a1de-108aea2567ay', 'bal81764-bea1-4249-b86d-f8fb8182eec1', 'event', '9zz0a75f-9b90-5482-a1de-108aea2567az', '9ff0a75f-9b90-5482-a1de-108aea2567az');
