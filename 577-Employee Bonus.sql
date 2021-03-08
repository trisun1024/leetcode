# Write your MySQL query statement below
SELECT
    name,
    bonus
FROM
    Employee a
    LEFT OUTER JOIN Bonus b ON a.empId = b.empId
WHERE
    b.bonus < 1000
    OR b.bonus IS NULL