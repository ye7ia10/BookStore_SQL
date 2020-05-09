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