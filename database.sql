create database spring;
use spring;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    ph varchar(15) NOT NULL
);
select * from user;

CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    writer_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
select * from post;
