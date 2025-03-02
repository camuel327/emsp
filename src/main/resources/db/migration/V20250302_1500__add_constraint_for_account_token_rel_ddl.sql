ALTER TABLE account_token_rel
    DROP CONSTRAINT IF EXISTS uniq_account_token_rel_account_id_token_id;

CREATE UNIQUE INDEX uniq_account_token_rel_token_id
    ON account_token_rel (token_id);
