--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM products AS p
INNER JOIN product_types AS pt
ON pt.type_name = 'СЫР' AND pt.type_id = p.type_id;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT  * FROM products AS p
WHERE p.product_name LIKE '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM products AS p
WHERE p.expired_date <= current_date + interval '1 month'
AND p.expired_date >= current_date;

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM products AS p
WHERE p.price = (SELECT MAX(p.price) FROM products AS p)

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT count(product_id) FROM products AS p
INNER JOIN product_types AS pt
ON pt.type_name = 'СЫР' AND pt.type_id = p.type_id;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM products AS p
INNER JOIN product_types AS pt
ON pt.type_name IN ('СЫР', 'МОЛОКО') AND pt.type_id = p.type_id;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT pt.type_name, count(p.product_id) AS "quantity"
FROM products AS p
INNER JOIN product_types AS pt
ON pt.type_id = p.type_id
GROUP BY pt.type_id
HAVING count(p.product_id) < 10;

--8. Вывести все продукты и их тип.
SELECT * FROM products AS p
INNER JOIN product_types AS pt
ON pt.type_id = p.type_id;
