CREATE DATABASE IF NOT EXISTS backtest_transactions;
USE backtest_transactions;

CREATE TABLE IF NOT EXISTS accounts(
    account_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    document_number VARCHAR(14) NOT NULL,
    credit_limit DECIMAL(18, 2) NOT NULL,
    PRIMARY KEY (account_id)
);

CREATE TABLE IF NOT EXISTS operations_types(
    operation_type_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    description VARCHAR(14) NOT NULL,
    multiplier INT(11) NOT NULL,
    PRIMARY KEY (operation_type_id)
);

INSERT IGNORE INTO operations_types(operation_type_id, description, multiplier) 
VALUES (1, "COMPRA A VISTA", -1),
       (2, "COMPRA PARCELADA", -1),
       (3, "SAQUE", -1),
       (4, "PAGAMENTO", 1);

CREATE TABLE IF NOT EXISTS transactions(
    transaction_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    account_id BIGINT(20) NOT NULL,
    operation_type_id BIGINT(20) NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    event_date DATETIME NOT NULL,
    PRIMARY KEY (transaction_id),
    CONSTRAINT FK_transaction_account_id FOREIGN KEY (account_id) REFERENCES accounts (account_id),
    CONSTRAINT FK_transaction_operation_type_id FOREIGN KEY (operation_type_id) REFERENCES operations_types (operation_type_id)
);