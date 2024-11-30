# 단어 테이블 생성
create table word
(
    word_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255),
    description TEXT,
    create_at   DATE,
    user_id     BIGINT,
    view_count  BIGINT DEFAULT 0
);

# 단어 테이블 전체 조회
select *
from word
where name = 'jdbc';

# JDBC 단어 추가
INSERT INTO word(name, description)
values ('JDBC', '데이터베이스에 접근');

INSERT INTO word(name, description)
values ('LOMBOK', '코드를 간편하게해줌');

# 단어 수정
update word
set name        = 'JDBCA',
    description = 'JDBC 템플릿을 활용'
where name = 'JDBC';

# 단어 삭제
delete
from word
where name = 'JDBCA';

# 조회수 업데이트
update word
set view_count = 1
where word_id = 1;

# 단어와 회원이름 조회
SELECT w.name, w.description, u.username, w.create_at, w.view_count
FROM word w
JOIN user u
ON u.user_id = w.user_id
where w.word_id = 2;

# UTILS
# drop table word;

select *
from word;

select *
from user;

show table status;

delete
from word
where word_id = 2;

TRUNCATE TABLE word;

SELECT *
FROM word
where word_id = 2;