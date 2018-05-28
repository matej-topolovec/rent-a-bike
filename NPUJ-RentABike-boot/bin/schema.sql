drop table if exists user;
drop table if exists user_role;
drop table if exists logging;
drop table if exists customer;
drop table if exists type_bike;
drop table if exists bike;
drop table if exists membershipType;
drop table if exists reservation;



CREATE TABLE membershipType(
id INT(11) IDENTITY PRIMARY KEY auto_increment,
name VARCHAR(50),
discountRate INT,
durationInMonths INT
);




CREATE TABLE user (
id INT(11) IDENTITY PRIMARY KEY auto_increment,
name VARCHAR(50),
surname VARCHAR(50),
username VARCHAR(45),
OIB VARCHAR(11) NULL,
birhtdate date NULL,
email VARCHAR(45),
address varchar(50),
phone varchar(20),
password VARCHAR(100) NOT NULL,
enabled TINYINT NOT NULL DEFAULT 1,
membershipTypeId INT DEFAULT 1,
FOREIGN KEY (membershipTypeId) REFERENCES membershipType (id)
);

CREATE TABLE customer (
id INT(11) IDENTITY PRIMARY KEY auto_increment,
name VARCHAR(50),
surname VARCHAR(50),
OIB VARCHAR(11) NULL,
birthdate date NULL,
email VARCHAR(45),
address varchar(50),
phone varchar(20),
membershipTypeId INT DEFAULT 1,
FOREIGN KEY (membershipTypeId) REFERENCES membershipType (id)
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
action_time date
);



CREATE TABLE bike_type(
id INT(11) IDENTITY PRIMARY KEY,
name VARCHAR(50)
);



CREATE TABLE bike(
id INT(11) IDENTITY PRIMARY KEY,
name VARCHAR(50),
dateAdded date not null,
quantity INT NOT NULL,
available INT NOT NULL,
typeId INT,
FOREIGN KEY (typeId) REFERENCES bike_type (id)
);



CREATE TABLE reservation(
id INT(11) IDENTITY PRIMARY KEY,
startTime date not null,
endTime date not null,
userId INT,
bikeId INT,
FOREIGN KEY (userId) REFERENCES user (id),
FOREIGN KEY (bikeId) REFERENCES bike (id)
);


