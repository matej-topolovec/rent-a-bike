INSERT INTO membershipType(name, discountRate, durationInMonths)
VALUES('No Membership', 0, 0);
INSERT INTO membershipType(name, discountRate, durationInMonths)
VALUES('Monthly', 10, 1);
INSERT INTO membershipType(name, discountRate, durationInMonths)
VALUES('Half-Year', 30, 6);
INSERT INTO membershipType(name, discountRate, durationInMonths)
VALUES('Annually', 50, 12);

INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Matej','Topolovec','12345897859','1990-06-05', 'matej.topolovec@net.hr', 'Ilica 22, 10000 Zagreb', '092/3455-575', 1);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Tin','Topolovec','46546546545','1990-06-05', 'tin.topolovec@net.hr', 'Svetoivanska 55, 10000 Zagreb', '092/3455-785', 1);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Kristina','Juriæ','45523545897','1998-07-05', 'kristina.jurica@net.hr', 'Graberje 45, 10000 Zagreb', '095/3585-575', 3);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Valentina','Periæ','45878956487','1993-02-03', 'valentina.peric@gmail.com', 'Ilica 22, 10000 Zagreb', '092/3455-575', 4);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Robert','Gluhakoviæ','47541254878','1990-06-07', 'rgluhakovic@net.hr', 'Kušlanova 22, 10040 Zagreb-Sesvete', '095/3455-545', 1);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Ivica','Durinkoviæ','45784512589','1990-12-12', 'idurinkovic@t-com.hr', 'Barišiæeva 31, 10000 Zagreb', '091/4578-575', 4);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Gordana','Miškiæ','45784512589','1990-04-05', 'gmiskic@gmail.com', 'Gunduliæeva 55, 10000 Zagreb', '093/4654-575', 3);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Vanja','Iliæ','45785412589','1990-01-05', 'vilic@net.hr', 'Ilica 25, 10000 Zagreb', '092/1235-575', 2);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Tihomir','Ložiæ','45412458796','1991-06-12', 'tlozic@net.hr', 'Ulica Dragojle Jarneviæ 1, 10000 Zagreb', '091/4522-575', 2);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Matko','Miliæ','45125687459','1995-06-05', 'rmilic@gmail.com', 'Sesvetska 52, 10000 Zagreb', '097/3455-575', 1);
INSERT INTO customer(name, surname, OIB, birthdate, email, address, phone, membershipTypeId)
VALUES ('Matej','Kliniæ','14541256898','1995-03-05', 'mklinic@gmail.com', 'Sesvetska 35, 10000 Zagreb', '095/1245-575', 1);

INSERT INTO user(name, surname, username, password, membershipTypeId,enabled)
VALUES ('Adminko','Adminko','admin','$2a$10$62iyTepDsDslP/9tp6fQFOov32op4RqDOsmsOFaDVv8ZtM2fVd16C', 1, true);


INSERT INTO user_role (username, role)
VALUES ('admin', 'ROLE_ADMIN');


INSERT INTO user(name, surname,username,password, membershipTypeId,enabled)
VALUES ('Userko','Userka','user','$2a$10$62iyTepDsDslP/9tp6fQFOov32op4RqDOsmsOFaDVv8ZtM2fVd16C', 1, true);


INSERT INTO user_role (username, role)
VALUES ('user', 'ROLE_USER');


INSERT INTO user(name, surname ,username, password, membershipTypeId, enabled)
VALUES ('Ivo','Ivic','demo','$2a$10$62iyTepDsDslP/9tp6fQFOov32op4RqDOsmsOFaDVv8ZtM2fVd16C', 1, true);


INSERT INTO user_role (username, role)
VALUES ('demo', 'ROLE_DEMO');


INSERT INTO logging(username, action, action_time) values ('TestUsername','Test action', '2018-05-15');


INSERT INTO bike_type(name) values('Cestovni');


INSERT INTO bike(name, dateAdded, quantity, available, typeid)
Values('Trek', '2018-05-10' , 5 , 1 , 1);


INSERT INTO bike(name, dateAdded, quantity, available, typeid)
Values('Scott', '2018-05-17' , 3 , 1 , 1);