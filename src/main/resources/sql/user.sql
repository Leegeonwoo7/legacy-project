CREATE TABLE user
(
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE,
    password VARCHAR(15)
);

INSERT INTO user (username, password)
values ('com', '1234');

select * from user;