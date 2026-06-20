# Write your MySQL query statement below
SELECT email AS Email
From Person
group by email
having count(email)>1;