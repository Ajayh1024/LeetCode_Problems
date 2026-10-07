# Write your MySQL query statement below
SELECT person_name
FROM (
    SELECT
        q.person_name,
        q.turn,
        SUM(q2.weight) AS running_weight
    FROM Queue q
    JOIN Queue q2
        ON q2.turn <= q.turn
    GROUP BY q.person_id, q.person_name, q.turn
    HAVING SUM(q2.weight) <= 1000
) t
ORDER BY turn DESC
LIMIT 1;