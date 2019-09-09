--Create database
CREATE DATABASE users_and_rules;

-- Create tables
CREATE TABLE roles(
	role_id serial primary key,
	role_name character varying (200) NOT NULL
);
CREATE TABLE rules (
	rule_id serial primary key,
	rule_name character varying (200) NOT NULL
);
CREATE TABLE roles_rules (
	role_id integer references roles(role_id) NOT NULL,
	rule_id integer references rules(rule_id) NOT NULL
);
CREATE TABLE states (
	state_id serial primary key,
	state_name character varying (200) NOT NULL
);
CREATE TABLE users (
	user_id serial primary key,
	user_name character varying (200) NOT NULL, 
	role_id integer references roles(role_id) NOT NULL
);
CREATE TABLE categories (
	category_id serial primary key,
	category character varying (200) NOT NULL
);
CREATE TABLE items (
	item_id serial primary key,
	item_name character varying (200),
	description character varying (1000),
	category_id integer references categories(category_id) NOT NULL,
	state_id integer references states(state_id) NOT NULL,
	user_id integer references users(user_id) NOT NULL
);
CREATE TABLE attachs (
	attach_id serial primary key,
	path character varying (1000) NOT NULL,
	attach_name character varying (200) NOT NULL,
	item_id integer references items(item_id) NOT NULL
);
CREATE TABLE item_comments (
	comments_id serial primary key,
	head character varying (200) NOT NULL,
	item_comment character varying (1000),
	item_id integer references items(item_id) NOT NULL
);

-- insert primary data
INSERT INTO roles (role_name) VALUES ('admin'), ('manager'), ('user'), ('guest');
INSERT INTO rules (rule_name) VALUES ('guest_folder'), ('users_folder'), ('managers_folder'), ('admin_folder');
INSERT INTO roles_rules VALUES (1, 1), (1, 2), (1, 3), (1, 4),
								(2, 1), (2, 2), (2, 3),
								(3, 1), (3, 2),
								(4, 1);
INSERT INTO users (user_name, role_id) VALUES ('admin', 1);				 
INSERT INTO categories (category) VALUES ('current'), ('urgent'), ('low preority');	
INSERT INTO states (state_name) VALUES ('done'), ('in process'), ('not started');	

