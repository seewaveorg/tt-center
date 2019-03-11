CREATE  TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (user_id));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (user_role_id));

CREATE TABLE roles (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(45) NOT NULL ,
  PRIMARY KEY (role_id));


INSERT INTO users(username,password,enabled)
VALUES ('mkyong','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO users(username,password,enabled)
VALUES ('alex','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO roles (role_name)
VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name)
VALUES ('ROLE_USER');

INSERT INTO user_roles (user_id, role_id)
VALUES (1,1);
INSERT INTO user_roles (user_id, role_id)
VALUES (1,2);
INSERT INTO user_roles (user_id, role_id)
VALUES (2,2);