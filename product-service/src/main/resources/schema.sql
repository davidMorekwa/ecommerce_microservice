DROP TABLE IF EXISTS t_products;
DROP TABLE IF EXISTS t_categories;
CREATE TABLE t_categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);
CREATE TABLE t_products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    in_stock INT DEFAULT 0,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES t_categories(id)
);


