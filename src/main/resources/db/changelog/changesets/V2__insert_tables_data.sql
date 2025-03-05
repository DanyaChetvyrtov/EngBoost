-- liquibase formatted sql

-- changeset your_name:3
INSERT INTO users (username, email)
VALUES ('user1', 'user1@example.com'),
       ('user2', 'user2@example.com');

-- changeset your_name:4
INSERT INTO orders (user_id, product_name, quantity)
VALUES (1, 'Laptop', 1),
       (2, 'Smartphone', 2);