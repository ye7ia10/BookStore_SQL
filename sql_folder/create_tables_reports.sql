use project;
create table book_sales(
id int auto_increment,
ISBN int not null,
quantity int not null,
last_checkout_date datetime not null,
constraint book_sales_PK primary key(id),
constraint book_sales_FK foreign key(ISBN) references book(ISBN) ON DELETE restrict  ON UPDATE cascade);

create table customers_top_rated(
id int auto_increment,
username varchar(55) NOT NULL UNIQUE,
quantity int not null,
last_checkout_date datetime not null,
constraint customers_top_rated_PK primary key(id),
constraint customers_top_rated_FK foreign key(username) references user(username) ON DELETE restrict  ON UPDATE cascade);