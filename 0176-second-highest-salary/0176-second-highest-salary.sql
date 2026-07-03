# Write your MySQL query statement below
SELECT MAX(salary) AS SecondHighestsalary
FROM Employee
WHERE salary < (Select max(salary) from Employee);