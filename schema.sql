CREATE TABLE TEST_TB(
    id             VARCHAR(10),
    name           VARCHAR(10),
    rate           DECIMAL(10, 5),
    amount_b       DECIMAL(15, 2),
    amount_s       DECIMAL(15, 2),
    created_date   DATE,
    created_time   TIME
);


-- TEST
-- CREATE TABLE TEST(C1 int, C2 int GENERATED ALWAYS AS IDENTITY);