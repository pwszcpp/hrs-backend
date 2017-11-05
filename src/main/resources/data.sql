INSERT INTO role (role_name) VALUES ('USER');
INSERT INTO role (role_name) VALUES ('ADMIN');

INSERT INTO user (user_id, username, password) VALUES (1, 'janusz', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy');
INSERT INTO user (user_id, username, password) VALUES (2, 'jan', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy');
INSERT INTO user (user_id, username, password) VALUES (3, 'kuba', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy');
INSERT INTO training (training_id, training_name, owner, start_date,end_date,cost,permission,location) VALUES (1, 'training', 'Me', '2017-02-09','2018-02-09','15000.0','1','NY');
INSERT INTO training (training_id, training_name, owner, start_date,end_date,cost,permission,location) VALUES (2, 'java', 'java', '2017-02-09','2018-02-09','15000.0','1','NY');

INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,2);
INSERT INTO training_user(user_id, training_id) VALUES (1,1);
INSERT INTO training_user(user_id, training_id) VALUES (2,2);
INSERT INTO training_user(user_id, training_id) VALUES (3,2);