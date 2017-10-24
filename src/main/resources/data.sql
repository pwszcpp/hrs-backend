INSERT INTO role (role_name) VALUES ('USER');
INSERT INTO role (role_name) VALUES ('ADMIN');

INSERT INTO user (user_id, username, password) VALUES (1, 'janusz', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy');

INSERT INTO user_role(user_id, role_id) VALUES (1,1);