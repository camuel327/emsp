CREATE TABLE account (
    id serial4 NOT NULL,
    service_id int4 NOT NULL,
    fleet_solution varchar(256) NOT NULL,
    contract_id varchar(128) NOT NULL,
    status int4 NOT NULL,
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_account PRIMARY KEY (id),
    CONSTRAINT uniq_account_service_id_fleet_solution_contract_id UNIQUE (service_id, fleet_solution, contract_id)
);
COMMENT ON COLUMN account.status IS '0: Created, 1: Activated, 2: Deactivated';

CREATE TABLE token (
    id serial4 NOT NULL,
    status int2 NOT NULL,
    type int4 NOT NULL,
    value varchar(128) NOT NULL,
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_token PRIMARY KEY (id)
);
COMMENT ON COLUMN token.status IS '0: Created, 1: Activated, 2: Deactivated';
COMMENT ON COLUMN token.type IS '0: RFID, 1: EMAID, 2: Mac-Address';

CREATE TABLE account_token_rel (
    id serial4 NOT NULL,
    account_id int4 NOT NULL,
    token_id int4 NOT NULL,
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_account_token_rel PRIMARY KEY (id),
    CONSTRAINT uniq_account_token_rel_account_id_token_id UNIQUE (account_id, token_id)
);
