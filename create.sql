CREATE DATABASE organisational_news;

\c organisational_news

CREATE TABLE departments (id SERIAL PRIMARY KEY, name varchar, description varchar, noOfEmployees int);

CREATE TABLE users (id SERIAL PRIMARY KEY, name varchar, position varchar, yearsActive int, departmentId int);

CREATE TABLE news (id SERIAL PRIMARY KEY, name varchar, content varchar, departmentId int);

CREATE TABLE departments_news (id SERIAL PRIMARY KEY, departmentId int, newsId int);

CREATE TABLE departments_users (id SERIAL PRIMARY KEY, departmentId int, userId int);