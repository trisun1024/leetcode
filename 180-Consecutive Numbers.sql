# Write your MySQL query statement below
SELECT
    DISTINCT tb1.Num AS ConsecutiveNums
FROM
    Logs AS tb1,
    Logs AS tb2,
    Logs AS tb3
WHERE
    tb1.Id + 1 = tb2.Id
    AND tb2.Id + 1 = tb3.Id
    AND tb1.Num = tb2.Num
    and tb2.Num = tb3.Num;