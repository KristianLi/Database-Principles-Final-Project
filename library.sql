-- create database library_db;


use library_db;

#   drop table book_info, borrow_info, login, reader_info;
-- 先建reader_info表

-- insert值时候，只需要card_num和name就够了
-- 用户注册了和这个card_num关联的account后，可自行修改其他信息
CREATE TABLE reader_info (
                             card_num VARCHAR(30) PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             gender VARCHAR(4),
                             title VARCHAR(255),
                             borrow_num INT default(10),
                             borrowed_num INT default(0),
                             department VARCHAR(255),
                             phone VARCHAR(20)
);


-- account用户自行自定，注册时候要绑定一个card_num。
-- 这个card_num需要事先存在
CREATE TABLE login (
                       account VARCHAR(255) PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       card_num VARCHAR(30) NOT NULL,
                       isAdmin TINYINT NOT null, -- 1: admin, 0: user
                       FOREIGN KEY (card_num) REFERENCES reader_info(card_num)
);


CREATE TABLE book_info (
                           ISBN VARCHAR(20) PRIMARY KEY,
                           book_name VARCHAR(255) NOT NULL,
                           publisher VARCHAR(255) NOT NULL,
                           author VARCHAR(255) NOT NULL,
                           avai_num INT NOT NULL,
                           borrow_num INT NOT NULL,
                           can_borrow TINYINT NOT NULL -- 1: 可借，0: 不可借
);



-- fine罚款默认为0，insert不需要指定（也可以指定。）
CREATE TABLE borrow_info (
                             borrow_id INT PRIMARY KEY AUTO_INCREMENT,
                             card_num VARCHAR(30) NOT NULL,
                             ISBN VARCHAR(20) NOT NULL,
                             borrow_date DATE NOT NULL,
                             due_date DATE NOT NULL,
                             return_date DATE,
                             fine DECIMAL(10, 2) default 0,
                             FOREIGN KEY (card_num) REFERENCES reader_info(card_num),
                             FOREIGN KEY (ISBN) REFERENCES book_info(ISBN)
);


-- 测试数据
-- reader_info
INSERT INTO reader_info
VALUES
    ('229001', '张伟', '男', '教师', 10, 0, '计算机学院', '12345678901');

INSERT INTO reader_info
VALUES
    ('229002', '李娜', '女', '教师', 10, 0, '人工智能学院', '12345678902');

INSERT INTO reader_info
VALUES
    ('229003', '张敏', '女', '学生', 10, 0, '计算机学院', '12345678903');

INSERT INTO reader_info
VALUES
    ('229004', '李宇轩', '男', '学生', 10, 0, '计算机学院', '12345678904');

INSERT INTO reader_info
VALUES
    ('229005', '王奕辰', '男', '学生', 10, 0, '计算机学院', '12345678905');

-- login
-- 创建了两个用户，一个admin，一个user
INSERT INTO login
VALUES
    ('admin1', 'admin', '229001', 1);

INSERT INTO login
VALUES
    ('user1', 'user', '229003', 0);

-- book_info
INSERT INTO book_info
VALUES
    ('9787121302954', '计算机网络', '清华大学出版社', '谢希仁', 16, 0, 1);

INSERT INTO book_info
VALUES
    ('9787040545180', '计算机组成原理', '高等教育出版社', '唐朔飞', 20, 0, 1);

INSERT INTO book_info
VALUES
    ('9787040492453', '操作系统原理、实现与实践', '高等教育出版社', '李治军', 11, 0, 1);

INSERT INTO book_info
VALUES
    ('9787020152780', '朝花夕拾', '人民文学出版社', '鲁迅', 21, 0, 1);

INSERT INTO book_info
VALUES
    ('9787506365437', '活着', '人民文学出版社', '余华', 13, 0, 1);