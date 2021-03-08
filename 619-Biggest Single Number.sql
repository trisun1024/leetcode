# Write your MySQL query statement below
SELECT
    max(num) as num
FROM
    (
        SELECT
            num
        FROM
            my_numbers
        GROUP BY
            num
        HAVING
            COUNT(*) = 1
    ) t