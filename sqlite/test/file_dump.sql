PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE test (id integer primary key, value text);
INSERT INTO "test" VALUES(1,'first message');
INSERT INTO "test" VALUES(2,'second message');
INSERT INTO "test" VALUES(3,'only message');
CREATE INDEX test_idx on test (value);
CREATE VIEW schema as select * from sqlite_master;
COMMIT;
