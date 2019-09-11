CREATE TABLE bodies (id serial primary key, name varchar(100) NOT NULL);
CREATE TABLE engines (id serial primary key, name varchar(100) NOT NULL);
CREATE TABLE transmitions (id serial primary key, name varchar(100) NOT NULL);
INSERT INTO bodies(name) VALUES ('Жигули 6'), ('Жигули 7'), ('Лада 8'), ('Лада 9');
INSERT INTO engines(name) VALUES ('Мотор 1,2'), ('Мотор 1,6'), ('Турбо 1,4'), ('Турбодизель 2,0');
INSERT INTO transmitions(name) VALUES ('4 передачи'), ('5 передач'), ('6 передач'), ('Автомат 6 передач');

CREATE TABLE cars (
	id serial primary key, 
	name varchar(100) NOT NULL, 
	body_id integer references bodies(id) NOT NULL,
	engine_id integer references engines(id) NOT NULL,
	transmition_id integer references transmitions(id) NOT NULL
);

INSERT INTO cars VALUES 
	(1, 'Семерочка ТД', 2, 4, 4), 
	(2, 'Семерка бюджет', 2, 1, 1), 
	(3, 'Лада стандарт', 3, 2, 2), 
	(4, 'Лада комфорт', 4, 2, 2), 
	(5, 'Лада турбо', 4, 4, 4); 