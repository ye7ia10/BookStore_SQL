create table book_sales(
ISBN int not null,
quantity int not null,
last_checkout_date datetime not null,
constraint book_sales_PK primary key(ISBN, last_checkout_date),
constraint book_sales_FK foreign key(ISBN) references book(ISBN) ON DELETE restrict  ON UPDATE cascade);

create table customers_top_rated(
username varchar(55) NOT NULL UNIQUE,
quantity int not null,
last_checkout_date datetime not null,
constraint customers_top_rated_PK primary key(username, last_checkout_date),
constraint customers_top_rated_FK foreign key(username) references user_table(username) ON DELETE restrict  ON UPDATE cascade);

