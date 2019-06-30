CREATE TABLE person
(
    id         INT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(250),
    birth_date LONG(250)
);
CREATE TABLE users
(
    id         INT NOT NULL AUTO_INCREMENT,
    login       VARCHAR(250),
    password    VARCHAR(250)
);
INSERT INTO users (login,password) VALUES('root','pass');
INSERT INTO users (login,password) VALUES('user','user');
