INSERT INTO role (role_name) VALUES ('USER');
INSERT INTO role (role_name) VALUES ('ADMIN');

INSERT INTO user (user_id, username, password, email) VALUES (1, 'janusz', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','janusz@janusz');
INSERT INTO user (user_id, username, password, email) VALUES (2, 'jan', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy' ,'jan@jan');
INSERT INTO user (user_id, username, password, email) VALUES (3, 'kuba', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy', 'kuba@kuba');

INSERT INTO training (training_id, training_name, owner, start_date,end_date,cost,permission,location)
VALUES (1, 'training', 'Me', '2017-02-09','2018-02-09','15000.0','1','NY');
INSERT INTO training (training_id, training_name, owner, start_date,end_date,cost,permission,location)
VALUES (2, 'java', 'java', '2017-02-09','2018-02-09','15000.0','1','NY');

INSERT INTO employee (employee_id, first_name, last_name, sex, job,hire_date,salary)
VALUES (1, 'mateusz', 'W', 'M','manager','2017-02-09',15000.0);
INSERT INTO employee (employee_id, first_name, last_name, sex, job,hire_date,salary)
VALUES (2, 'kuba', 'k', 'M','manager', '2017-03-09',10000.0);

INSERT INTO address (address_id, address_line,street, postal_code, city, country)
VALUES (1, '4/5', 'narutowicza', '32-620','brz','poland');
INSERT INTO address (address_id, address_line,street, postal_code, city, country)
VALUES (2, '3/5', 'bema', '32-600','osw','poland');

INSERT INTO contractor (contractor_id, contractor_name, tin,address_id)
VALUES (1, 'ibm', '12354-600',1);
INSERT INTO contractor (contractor_id, contractor_name, tin,address_id)
VALUES (2, 'PEC', '123123123',2);

INSERT INTO invoice (invoice_id, description, net_amount,gross_amount,tax,contractor_id)
VALUES (1, 'za kawe', 1000,1230,0.23,1);
INSERT INTO invoice (invoice_id, description, net_amount,gross_amount,tax,contractor_id)
VALUES (2, 'zakup komputera', 2000,2468,0.23,2);
INSERT INTO invoice (invoice_id, description, net_amount,gross_amount,tax,contractor_id)
VALUES (3, 'sprzatanie', 2000,2468,0.23,1);




INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,2);
INSERT INTO user_role(user_id, role_id) VALUES (3,1);
INSERT INTO training_user(user_id, training_id) VALUES (1,1);
INSERT INTO enrolled_user(user_id, training_id) VALUES (3,1);
INSERT INTO training_user(user_id, training_id) VALUES (2,2);
