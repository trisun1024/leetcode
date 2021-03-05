CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT BEGIN
SET
    N = N -1;RETURN (
        # Write your MySQL query statement below.
        SELECT
            DISTINCT Salary
        FROM
            Employee
        ORDER BY
            Salary DESC
        LIMIT
            N, 1
    );END
    
;-------------------------------------------

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT BEGIN RETURN (
      
        WITH t AS (
            SELECT
                Salary,
                DENSE_RANK() over (
                    order by
                        Salary DESC
                ) AS `RANK`
            FROM
                Employee
        )
        SELECT
            Salary
        FROM
            t
        WHERE
            t.`RANK` = N
        LIMIT
            1
    );END