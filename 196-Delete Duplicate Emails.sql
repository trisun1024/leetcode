# Write your MySQL query statement below
WITH tab1 AS (
    SELECT
        Email,
        MIN(Id) AS min_id,
        COUNT(1) AS count_
    FROM
        Person
    GROUP BY
        Email
)
DELETE FROM
    Person
WHERE
    Id NOT IN (
        SELECT
            min_id
        FROM
            tab1
    )