SELECT * FROM test.test_base;

INSERT INTO test.test_base (pID, tVarChar)
VALUES (2, 'Hello');
-- commit; 

CREATE TABLE test.Cards (
	ID int,
    name varchar(255)
);
-- commit;