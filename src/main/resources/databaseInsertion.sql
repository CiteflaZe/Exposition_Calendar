INSERT INTO expositions_calendar.roles (role_name) VALUES("Admin"),
("User");

INSERT INTO expositions_calendar.users(name, surname, email, password, role_id) VALUES ("Admin",  "Admin", "admin@gmail.com",  "8c6976e5b541415bde98bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",  1),
("Jack",  "Blevis", "user1@gmail.com",  "a41b9462caa4a31bac3567e0b6e6fd910787db2ab433d96f6d178cabfce90",  2),
("Arthur",  "Hofman", "user2@gmail.com",  "6025d18fe48abd45168528f18a82e265dd98d421a784aa09f61b34170391a3",  2);

INSERT INTO expositions_calendar.halls (name ,city, street, house_number) VALUES ("International Exhibition Centre", "Kyiv", "Brovarskyi Ave", 15),
("Expo Center of Ukraine", "Kyiv", "AAcademica Glushkova Avenue", 1);

INSERT INTO expositions_calendar.expositions(title, theme, start_date, end_date, ticket_price, description, hall_id) VALUES ("IDDM", "Informatics and Medicine", "2019-11-5", "2019-12-23"
, 80.25, "The International Seminar on Computer Science and Medicine, Data-Driven IDDM, focuses on all the technical and practical aspects of the latest research
 and the results of international academics, scientists and practitioners related to the topics of intellectual medical data processing.", 1),
 ("EuroAgro", "Agricultural Industry", "2019-12-4", "2020-01-14", 40.50,
 "EuroAGRO is a professional platform where domestic producers have the opportunity to demonstrate their achievements in the field
 of agriculture and get acquainted with the latest technologies and solutions of the European agricultural market", 2);

 INSERT INTO expositions_calendar.payments(payment_time, price, status, tickets_amount, user_id, exposition_id) VALUES("2019-11-8 13:31:18", 240.75, "Passed", 3, 2, 1),
("2019-11-9 15:24:45", 162, "Passed", 4, 3, 2),
("2019-11-10 21:13:28", 80.25, "Failed", 1, 2, 2),
("2019-11-7 9:31:25", 81, "Failed", 2, 3, 1);

INSERT INTO expositions_calendar.tickets (valid_date, user_id, payment_id, exposition_id) VALUES ("2019-11-23", 2, 1, 1),
("2019-11-23", 2, 1, 1),
("2019-11-23", 2, 1, 1),
("2019-12-28", 3, 2, 2),
("2019-12-28", 3, 2, 2),
("2019-12-28", 3, 2, 2),
("2019-12-28", 3, 2, 2);