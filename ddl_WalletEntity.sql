CREATE TABLE wallet
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    balance DECIMAL               NOT NULL,
    user_id BIGINT                NOT NULL,
    CONSTRAINT pk_wallet PRIMARY KEY (id)
);

ALTER TABLE wallet
    ADD CONSTRAINT uc_wallet_user UNIQUE (user_id);