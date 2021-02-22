SELECT
    t.Request_at AS Day,
    ROUND(
        AVG(
            CASE WHEN t.Status LIKE 'cancelled%' THEN 1 ELSE 0 END
        ),
        2
    ) AS 'Cancellation Rate'
FROM
    Trips t
    INNER JOIN Users u ON t.Client_Id = u.Users_Id
    AND u.Banned = 'No'
    INNER JOIN Users d ON t.Driver_Id = d.Users_Id
    AND d.Banned = 'No'
WHERE
    t.Request_at BETWEEN '2013-10-01'
    AND '2013-10-03'
GROUP BY
    t.Request_at