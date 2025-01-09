DROP TABLE IF EXISTS t_orders;
CREATE TABLE t_orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT,
    user_id INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);