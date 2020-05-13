create database Book_Store;
use Book_Store;

create table book
(ISBN int not null unique, 
title varchar(25) not null, 
publisher_Name varchar(25) not null, 
publish_year year not null, 
selling_price decimal not null, 
category_id int not null,
constraint book_PK primary key (ISBN));

create table book_authors(
book_id int not null,
author_name varchar(25) not null,
constraint book_authors_PK primary key(author_name, book_id));

create table category(
id int auto_increment not null,
category_name varchar(10) not null,
constraint category_PK primary key(id));

create table publisher(
Publisher_Name varchar(15) not null,
address varchar(25) not null,
phone int(11) not null,
constraint publisher_PK primary key(Publisher_Name));

create table book_copies(
id int auto_increment not null,
thersold int not null,
available int not null,
constraint book_copies_PK primary key(id));

create table orders(
id int auto_increment not null,
book_id int not null,
quantity int not null,
order_date date not null,
constraint orders_PK primary key(id));
