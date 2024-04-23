
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    township VARCHAR(255) NOT NULL,
    housenumber VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT 'CUSTOMER'
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    product_desc TEXT,
    product_image MEDIUMBLOB,
    price INT,
    category_id BIGINT,
    uploaded_date DATE,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255)
);
