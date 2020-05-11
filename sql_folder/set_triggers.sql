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

insert into orders(book_id, quantity, order_date) values(OLD.id, OLD.constant_quantity, CURDATE());

end if;

END$$
DELIMITER ;






