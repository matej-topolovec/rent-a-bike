drop table if exists user;
drop table if exists user_role;
CREATE TABLE user (
id INT(11) IDENTITY PRIMARY KEY,
ime VARCHAR(50) NOT NULL,
prezime VARCHAR(50) NOT NULL,
username VARCHAR(45),
password VARCHAR(100) NOT NULL ,
enabled TINYINT NOT NULL DEFAULT 1
);
CREATE TABLE user_role (
user_role_id int(11) IDENTITY PRIMARY KEY,
username varchar(45) NOT NULL,
role varchar(45) NOT NULL,
FOREIGN KEY (username) REFERENCES user (username)
);