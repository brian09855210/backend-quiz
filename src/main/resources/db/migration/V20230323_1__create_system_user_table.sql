CREATE TABLE "system_user"
(
    "id" uuid NOT NULL PRIMARY KEY,
    "account" varchar(128) UNIQUE,
    "password" varchar(128),
    "name" varchar(128)
);

COMMENT ON COLUMN "system_user".id IS 'UUID';
COMMENT ON COLUMN "system_user".account IS '帳號';
COMMENT ON COLUMN "system_user".password IS '密碼';
COMMENT ON COLUMN "system_user".name IS '使用者名稱';
