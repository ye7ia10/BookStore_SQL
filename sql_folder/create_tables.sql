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


CREATE TABLE user (
first_name varchar(55) NOT NULL,
last_name varchar(55) NOT NULL,
phone_number varchar(55) NOT NULL,
address varchar(55) NOT NULL,
username varchar(55) NOT NULL UNIQUE,
email VARCHAR(320) NOT NULL UNIQUE,
password VARCHAR(320) NOT NULL,
admin int DEFAULT 0,
CONSTRAINT chk_Frequency CHECK (admin IN (0,1)),
primary key (username)
);

create table book_sales(
id int auto_increment,
ISBN int not null,
quantity int not null,
last_checkout_date datetime not null,
constraint book_sales_PK primary key(id),
constraint book_sales_FK foreign key(ISBN) references book(ISBN) ON DELETE restrict  ON UPDATE cascade);

create table customers_top_rated(
id int auto_increment,
username varchar(55) NOT NULL,
quantity int not null,
last_checkout_date datetime not null,
constraint customers_top_rated_PK primary key(id),
constraint customers_top_rated_FK foreign key(username) references user(username) ON DELETE restrict  ON UPDATE cascade);


ALTER TABLE book_authors
ADD CONSTRAINT book_authors_FK
FOREIGN KEY (book_id) REFERENCES book(ISBN) on delete cascade  on update cascade;

ALTER TABLE book_copies
ADD CONSTRAINT book_copies_FK
FOREIGN KEY (id) REFERENCES book(ISBN) on delete cascade  on update cascade;

ALTER TABLE orders
ADD CONSTRAINT orders_FK
FOREIGN KEY (book_id) REFERENCES book(ISBN) on delete cascade  on update cascade;

ALTER TABLE book
ADD CONSTRAINT book_category_FK
FOREIGN KEY (category_id) REFERENCES category(id) on delete restrict  on update cascade;

ALTER TABLE book
ADD CONSTRAINT book_publisher_FK
FOREIGN KEY (publisher_Name) REFERENCES publisher(Publisher_Name) on delete restrict  on update cascade;



drop event IF EXISTS Clean_Older_Than_90_days_logs;
drop event IF EXISTS Clean_Older_Than_90_days_logs_customers;

CREATE EVENT IF NOT EXISTS Clean_Older_Than_90_days_logs
ON SCHEDULE
EVERY 1 HOUR
DO
DELETE FROM book_sales
WHERE last_checkout_date < DATE_SUB(NOW(), INTERVAL 90 DAY);
    
CREATE EVENT IF NOT EXISTS Clean_Older_Than_90_days_logs_customers
ON SCHEDULE
EVERY 1 HOUR
DO
DELETE FROM customers_top_rated
WHERE last_checkout_date < DATE_SUB(NOW(), INTERVAL 90 DAY);
DELIMITER $$

create trigger update_quantity before update on book_copies
for each row begin

if OLD.available + NEW.available < 0 then set NEW.available = OLD.available;
else set NEW.available = OLD.available + NEW.available;
end if;

END$$
DELIMITER ;

DELIMITER $$

create trigger update_quantity_order before delete on orders
for each row begin

update book_copies set book_copies.available = book_copies.available + OLD.quantity where book_copies.id = OLD.book_id;

END$$
DELIMITER ;

DELIMITER $$

create trigger place_order after update on book_copies
for each row begin

if NEW.available < OLD.thersold then

insert into orders(book_id, quantity, order_date) values(OLD.id, OLD.thersold, CURDATE());

end if;

END$$
DELIMITER ;

DELIMITER //
CREATE TRIGGER InsertPreventTrigger BEFORE INSERT ON book_copies
FOR EACH ROW
BEGIN
IF(new.thersold < 0 or new.available < 0) THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'You can not insert record';
END IF;
END //
DELIMITER ;



