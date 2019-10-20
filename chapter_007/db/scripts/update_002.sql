CREATE TABLE shop
(id serial primary key,
  name varchar(100) not null,
  expiredate bigint not null,
  createdate  bigint not null,
  price  bigint not null,
  discount double precision not null,
  type_id int not null);
CREATE TABLE trash
(id serial primary key,
  name varchar(100) not null,
  expiredate bigint not null,
  createdate  bigint not null,
  price  bigint not null,
  discount double precision not null,
  type_id int not null);
CREATE TABLE warehouse
(id serial primary key,
  name varchar(100) not null,
  expiredate bigint not null,
  createdate  bigint not null,
  price  bigint not null,
  discount double precision not null,
  type_id int not null);

CREATE TABLE types
(type_id serial primary key,
  name varchar(100) not null);

INSERT INTO types (name)
VALUES ('Bread'), ('Butter'), ('SaltCrackers'), ('SweetCookies');