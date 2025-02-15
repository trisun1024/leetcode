# Write your MySQL query statement below
SELECT
    MIN(ABS(a.x - b.x)) AS shortest
FROM
    point a
    JOIN point b ON a.x != b.x;