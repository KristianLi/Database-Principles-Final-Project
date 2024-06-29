create database library_db;


use library_db;

CREATE TABLE login (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    account VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    card_num VARCHAR(30) NOT NULL,
    isAdmin TINYINT(1) NOT null,
    FOREIGN KEY (card_num) REFERENCES reader_info(card_num),
);

CREATE TABLE book_info (
    ISBN VARCHAR(20) PRIMARY KEY,
    book_name VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    total_num INT NOT NULL,
    borrow_num INT NOT NULL,
    is_borrow TINYINT(1) NOT NULL
);

CREATE TABLE reader_info (
    card_num VARCHAR(30) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(4) NOT NULL,
    title VARCHAR(255) NOT NULL,
    borrow_num INT NOT NULL,
    borrowed_num INT NOT NULL,
    department VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE borrow_info (
    borrow_id INT PRIMARY KEY AUTO_INCREMENT,
    card_num VARCHAR(30) NOT NULL,
    ISBN VARCHAR(20) NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    fine DECIMAL(10, 2),
    FOREIGN KEY (card_num) REFERENCES reader_info(card_num),
    FOREIGN KEY (ISBN) REFERENCES book_info(ISBN)
);
