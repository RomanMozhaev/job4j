--Вывести список всех машин и все привязанные к ним детали.
SELECT c.id, c.name, b.name, e.name, t.name FROM cars AS c
INNER JOIN bodies AS b
ON c.body_id = b.id
INNER JOIN engines AS e
ON c.engine_id = e.id
INNER JOIN transmitions AS t
ON c.transmition_id = t.id;

-- Вывод неиспользуемого кузова
SELECT b.name FROM cars AS c RIGHT OUTER JOIN bodies AS b ON c.body_id = b.id WHERE c.id IS NULL;

-- Вывод неиспользуемого двигателя
SELECT  e.name FROM cars AS c RIGHT OUTER JOIN engines AS e ON c.engine_id = e.id WHERE c.id IS NULL;

-- Вывод неиспользуемой транасмиссии
SELECT  t.name FROM cars AS c RIGHT OUTER JOIN transmitions AS t ON c.transmition_id = t.id WHERE c.id IS NULL;
