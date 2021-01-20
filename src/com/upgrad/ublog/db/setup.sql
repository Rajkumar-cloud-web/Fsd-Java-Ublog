cc

CREATE DATABASE ublog;

USE ublog;

CREATE TABLE user (
    userId INTEGER AUTO_INCREMENT PRIMARY KEY,
    emailId VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE post (
    postId INTEGER AUTO_INCREMENT PRIMARY KEY,
    emailId VARCHAR(100),
    tag VARCHAR(10),
    title VARCHAR(200),
    description VARCHAR(1000),
    timestamp VARCHAR(100)
);
