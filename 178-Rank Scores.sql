# Write your MySQL query statement below
select
    Score,
    DENSE_RANK() over (
        order by
            score desc
    ) `Rank`
from
    Scores
order by
    `Rank`;