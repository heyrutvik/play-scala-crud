# Tasks schema
 
# --- !Ups

CREATE TABLE curd (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `label` VARCHAR(255),
    `desc` TEXT
);
 
# --- !Downs
 
DROP TABLE curd;
