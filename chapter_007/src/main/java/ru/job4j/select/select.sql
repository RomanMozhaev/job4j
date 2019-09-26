TASK:
***************************
CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);
есть две таблицы )
нужно
// 1) Retrieve in a single query:
// - names of all persons that are NOT in the company with id = 5
// - company name for each person
// 2) Select the name of the company with the maximum number of persons + number of persons in this company
**************************
TASK SOLUTION
1.1)
SELECT name FROM person
WHERE company_id != 5;
1.2)
SELECT p.name as "Person name", c.name as "Company name" FROM person AS p
  INNER JOIN company AS c
    ON p.company_id = c.id;
2)
SELECT c.name, count(p.id) as q FROM company AS c
  INNER JOIN person AS p
    ON c.id = p.company_id
GROUP BY c.id
ORDER BY q DESC
LIMIT 1;