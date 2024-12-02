CREATE TABLE user
(
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE,
    password VARCHAR(15)
);

INSERT INTO user (username, password)
values ('com', '1234');

select * from user;

select *
from user u
join word w
on u.user_id = w.user_id
where u.user_id = 1;

select * from word;
select * from user;