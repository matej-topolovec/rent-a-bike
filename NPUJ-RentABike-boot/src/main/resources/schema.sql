drop table if exists user;
drop table if exists user_role;
drop table if exists logging;
drop table if exists type_bike;
drop table if exists bike;

CREATE TABLE user (
id INT(11) IDENTITY PRIMARY KEY,
ime VARCHAR(50) NOT NULL,
prezime VARCHAR(50) NOT NULL,
username VARCHAR(45),
email VARCHAR(45),
address varchar(50),
phone int,
password VARCHAR(100) NOT NULL ,
enabled TINYINT NOT NULL DEFAULT 1
);

ALTER TABLE user ADD CONSTRAINT user_username_unique UNIQUE (username);
ALTER TABLE user ADD CONSTRAINT user_email_unique UNIQUE (email);

CREATE TABLE user_role (
user_role_id int(11) IDENTITY PRIMARY KEY,
username varchar(45) NOT NULL,
role varchar(45) NOT NULL,
FOREIGN KEY (username) REFERENCES user (username)
);

CREATE TABLE logging(
id INT(11) IDENTITY PRIMARY KEY,
username varchar(50),
action varchar(50),
action_time timestamp
);


CREATE TABLE type_bike(
id INT(11) IDENTITY PRIMARY KEY,
name varchar(50)
);

CREATE TABLE bike(
id INT(11) IDENTITY PRIMARY KEY,
name varchar(50),
dateAdded timestamp,
quantity int NOT NULL,
available int NOT NULL,
typeid int,
FOREIGN KEY (typeid) REFERENCES type_bike (id)
);


