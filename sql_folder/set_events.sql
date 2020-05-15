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