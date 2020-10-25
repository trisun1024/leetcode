# Write your MySQL query statement below
select
    avg(number) as median
from
    (
        select
            l.number
        from
            numbers l
            join numbers r
        group by
            1
        having
            abs(sum(sign(l.number - r.number) * r.frequency)) <= max(l.frequency)
    ) t;