SELECT
    Email
FROM
    (
        SELECT
            Email,
            COUNT(*) AS Num
        FROM
            Person
        GROUP BY
            Email
    ) t
WHERE
    t.Num > 1;
    
--- solution 2
SELECT
    Email
FROM
    Person
GROUP BY
    Email
HAVING
    count(Email) > 1;