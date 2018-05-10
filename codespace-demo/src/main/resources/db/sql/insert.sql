INSERT INTO money_types (id, money_label) VALUES (1, 'TRY');
INSERT INTO money_types (id, money_label) VALUES (2, 'GBP');

INSERT INTO user(username,password,enabled) VALUES ('mkyong','$2b$10$K1CiqcM5nSw9LTK.r8Wz0O3NGQKSUCgXHfuUjggVf0e0zHLJGReC2', true);
INSERT INTO user(username,password,enabled) VALUES ('alex','$2b$10$K1CiqcM5nSw9LTK.r8Wz0O3NGQKSUCgXHfuUjggVf0e0zHLJGReC2', true);
INSERT INTO user(username,password,enabled) VALUES ('admin','$2b$10$K1CiqcM5nSw9LTK.r8Wz0O3NGQKSUCgXHfuUjggVf0e0zHLJGReC2', true);

#$2b$10$K1CiqcM5nSw9LTK.r8Wz0O3NGQKSUCgXHfuUjggVf0e0zHLJGReC2

INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('alex', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('admin', 'ROLE_ADMIN');



