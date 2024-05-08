create database kiosk;

use kiosk;



CREATE TABLE `members` (
   `member_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `login_id`   varchar(50)   NOT NULL,
   `password`   varchar(50)   NOT NULL,
   `nickname`   varchar(50)   NOT NULL,
   `point`   bigint   NOT NULL DEFAULT 0,
   `role`   varchar(50)   NULL,
   `delete_yn`  TINYINT(1) DEFAULT 0,
   `created_at`   timestamp   NULL DEFAULT now(),
   `updated_at`   TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE `orders` (
   `order_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `code`   VARCHAR(255)   NOT NULL,
   `temp_id`   bigint   NOT NULL,
   `status`   VARCHAR(255)   NOT NULL,   
   `created_at`   TIMESTAMP   NOT NULL DEFAULT now(),
   `updated_at`   TIMESTAMP   NULL DEFAULT now(),
   `member_id`   bigint   NOT NULL
);

CREATE TABLE `products` (
   `product_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `name`   varchar(50)   NULL,
   `image_url`   varchar(255)   NULL,
   `price`   bigint   NULL,
   `delete_yn`  TINYINT(1) DEFAULT 0,
   `product_option`   varchar(50)   NOT NULL,
   `product_type`      varchar(50) NOT NULL,
   `created_at`   TIMESTAMP   NULL DEFAULT now(),
   `updated_at`   TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE `orders_products_mapping` (
   `order_product_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `order_id`   bigint   NOT NULL,
   `product_id`   bigint   NULL,
   `order_product_cnt` int NOT NULL,
   `order_product_price` bigint NOT NULL
);

CREATE TABLE `temp_id_manager` (
    `id` BIGINT NOT NULL,
    `current_temp_id` INT DEFAULT 1,
    PRIMARY KEY (id)
);

ALTER TABLE `orders_products_mapping` ADD CONSTRAINT `FK_orders_TO_orders_products_mapping_1` FOREIGN KEY (
   `order_id`
)
REFERENCES `orders` (
   `order_id`
);

ALTER TABLE `orders_products_mapping` ADD CONSTRAINT `FK_products_TO_orders_products_mapping_1` FOREIGN KEY (
   `product_id`
)
REFERENCES `products` (
   `product_id`
);

ALTER TABLE `orders` ADD CONSTRAINT `FK_members_TO_orders` FOREIGN KEY (
   `member_id`
)
REFERENCES `members` (
   `member_id`
);

select * from orders;
select * from products;

select a.*, sum(b.order_product_price) as sum_price, sum(b.order_product_cnt) as sum_cnt
from orders a
join orders_products_mapping b on a.order_id = b.order_id 
join products c on b.product_id = c.product_id
GROUP BY a.order_id;

select a.order_id as orderId, a.code as code, a.temp_id as tempId, a.status as status ,
            sum(b.order_product_price) as sumPrice, sum(b.order_product_cnt) as sumCnt
            from orders a
            join orders_products_mapping b on a.order_id = b.order_id 
            join products c on b.product_id = c.product_id
            GROUP BY a.order_id;

INSERT INTO kiosk.members (login_id,password,nickname,`point`,`role`,created_at,updated_at) VALUES
    ('test','test','test',0,'ADMIN','2024-04-29 04:33:12','2024-04-29 04:33:12'); 

INSERT INTO kiosk.orders (code,temp_id,status,created_at,updated_at,member_id) VALUES
    ('code1',1,'READY','2024-04-29 04:33:31','2024-04-29 04:33:31',1),
    ('code2',2,'READY','2024-04-29 04:33:31','2024-04-29 04:33:31',1);
    
INSERT INTO kiosk.products (name,image_url,price,product_option,product_type,created_at,updated_at) VALUES
    ('nurget',NULL,1000,'SINGLE','DESSERT','2024-04-29 04:34:17','2024-04-29 04:34:17'),
    ('pizza',NULL,2000,'SINGLE','DESSERT','2024-04-29 04:34:17','2024-04-29 04:34:17'),
    ('plain burger','',1000,'SINGLE','BURGER','2024-04-30 10:59:27','2024-04-30 10:59:27'),
    ('cheese burger','',1500,'SINGLE','BURGER','2024-04-30 11:07:50','2024-04-30 11:07:50');

INSERT INTO kiosk.orders_products_mapping (order_id,product_id,order_product_cnt,order_product_price) VALUES
    (1,1,2,2000),
    (1,2,1,2000),
    (2,2,4,8000);
select distinct a.* from products a
join orders_products_mapping b on a.product_id = b.product_id;

INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'bacon tomato deluxe', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/bacon+tomato+deluxe.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'big mac blt', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/big+mac+blt.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'big mac', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/big+mac.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'bulgogi burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/bulgogi+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'cheeseburger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/cheeseburger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double big mac', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/double+big+mac.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double bulgogi burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/double+bulgogi+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double cheeseburger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/double+cheeseburger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double quater pounder with cheese', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/double+quater+pounder+with+cheese.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'egg bulgogi burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/egg+bulgogi+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'hamburger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/hamburger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mcchicken mozzarella', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/mcchicken+mozzarella.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mcchicken', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/mcchicken.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mccrispy classic burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/mccrispy+classic+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mccrispy deluxe burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/mccrispy+deluxe+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mcspicy shanghai burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/mcspicy+shanghai+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'quater pounder with cheese', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/quater+pounder+with+cheese.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'shrimp beef burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/shrimp+beef+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'supreme shrimp burger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/supreme+shrimp+burger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'triple cheeseburger', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/single/triple+cheeseburger.png', FLOOR(RAND() * (10001 - 5000)) + 5000, 'SINGLE', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, '1955 burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/1955+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'bacon tomato deluxe meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/bacon+tomato+deluxe+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'big mac blt meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/big+mac+blt+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'big mac meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/big+mac+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'bulgogi burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/bulgogi+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'cheeseburger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/cheeseburger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double big mac meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/double+big+mac+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double bulgogi burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/double+bulgogi+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double cheeseburger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/double+cheeseburger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'double quater pounder with cheese meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/double+quater+pounder+with+cheese+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'egg bulgogi burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/egg+bulgogi+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mcchicken meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/mcchicken+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mcchicken mozzarella meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/mcchicken+mozzarella+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mccrispy classic burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/mccrispy+classic+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mccrispy deluxe burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/mccrispy+deluxe+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'mcspicy shanghai burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/mcspicy+shanghai+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'quater pounder with cheese meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/quater+pounder+with+cheese+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'shrimp beef burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/shrimp+beef+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'supreme shrimp burger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/supreme+shrimp+burger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO kiosk.products (product_id, name, image_url, price, product_option, product_type, created_at, updated_at) VALUES (0, 'triple cheeseburger meal', 'https://kocket.s3.ap-northeast-2.amazonaws.com/burgers/burgers/set/triple+cheeseburger+meal.png', FLOOR(RAND() * (15001 - 10000)) + 10000, 'SET', 'BURGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
