INSERT INTO membershipType(name, discountRate, durationInMonths)
VALUES('Akcija', 10, 2);


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


INSERT INTO type_bike(name) values('Cestovni');


INSERT INTO bike(name, dateAdded, quantity, available, typeid)
Values('Trek', '2018-05-15' , 5 , 1 , 1);


INSERT INTO bike(name, dateAdded, quantity, available, typeid)
Values('Scott', '2018-05-15' , 3 , 1 , 1);