SELECT
    t1.id
FROM
    Weather t1
    JOIN Weather t2 ON DATEDIFF(t1.recordDate, t2.recordDate) = 1
    AND t1.Temperature > t2.Temperature;