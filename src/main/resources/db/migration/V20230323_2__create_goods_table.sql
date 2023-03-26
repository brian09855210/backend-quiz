CREATE TABLE "goods"
(
    "id" uuid NOT NULL PRIMARY KEY,
    "name" varchar(128) NOT NULL
);

COMMENT ON COLUMN "goods".id IS 'UUID';
COMMENT ON COLUMN "goods".name IS '商品名稱';
