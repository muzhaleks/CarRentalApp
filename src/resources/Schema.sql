CREATE TABLE role
(
    id int PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX role_id_uindex ON role (id);
CREATE UNIQUE INDEX role_role_uindex ON role (role);

CREATE TABLE user
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id int DEFAULT 1 NOT NULL,
    active_status int DEFAULT 1 NOT NULL
);
CREATE UNIQUE INDEX user_id_uindex ON user (id);
CREATE UNIQUE INDEX user_login_uindex ON user (login);

ALTER TABLE user ADD first_name VARCHAR(255) NULL;
ALTER TABLE user ADD last_name VARCHAR(255) NULL;
ALTER TABLE user ADD date_of_registration DATE NOT NULL;
ALTER TABLE user ADD email VARCHAR(255) NOT NULL;
CREATE UNIQUE INDEX user_email_uindex ON user (email);
ALTER TABLE user ADD passport_serial_number VARCHAR(255) NOT NULL;
CREATE UNIQUE INDEX user_passport_serrial_number_uindex ON user (passport_serial_number);
ALTER TABLE user ADD driver_licence_number VARCHAR(255) NOT NULL;
CREATE UNIQUE INDEX user_driver_licennce_number_uindex ON user (driver_licence_number);
ALTER TABLE user ADD phone_number VARCHAR(13) NOT NULL;
CREATE UNIQUE INDEX user_phone_number_uindex ON user (phone_number);

CREATE TABLE car
(
    id int PRIMARY KEY AUTO_INCREMENT,
    mark_id int NOT NULL,
    model_id int NOT NULL,
    millage int NOT NULL,
    price FLOAT NOT NULL,
    car_status_id int NOT NULL
);
CREATE UNIQUE INDEX car_id_uindex ON car (id);


CREATE TABLE country_of_manufacture
(
    id int PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX country_of_manufacture_id_uindex ON country_of_manufacture (id);
CREATE UNIQUE INDEX country_of_manufacture_country_uindex ON country_of_manufacture (country);

CREATE TABLE car_mark
(
    id int PRIMARY KEY AUTO_INCREMENT,
    mark VARCHAR(50) NOT NULL,
    country_of_manufacture_id INT NOT NULL
);
CREATE UNIQUE INDEX car_mark_id_uindex ON car_mark (id);

CREATE TABLE car_model
(
    id int PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX car_model_id_uindex ON car_model (id);
CREATE UNIQUE INDEX car_model_model_uindex ON car_model (model);

CREATE TABLE car_status
(
    id int PRIMARY KEY AUTO_INCREMENT,
    car_status varchar(50) NOT NULL
);
CREATE UNIQUE INDEX car_status_id_uindex ON car_status (id);
CREATE UNIQUE INDEX car_status_car_status_uindex ON car_status (car_status);

CREATE TABLE `order`
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    car_id int NOT NULL,
    rent_hours int NOT NULL,
    total_price DOUBLE NOT NULL,
    payment_status BOOLEAN DEFAULT false  NOT NULL,
    confirm_status BOOLEAN DEFAULT false  NOT NULL,
    date_of_reg_order DATETIME NOT NULL,
    order_status BOOLEAN DEFAULT true  NOT NULL,
    notes VARCHAR(255)
);
CREATE UNIQUE INDEX order_id_uindex ON `order` (id);

ALTER TABLE user MODIFY active_status BOOLEAN NOT NULL DEFAULT TRUE ;

ALTER TABLE user
ADD CONSTRAINT user_role_id_fk
FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE car
ADD CONSTRAINT car_car_mark_id_fk
FOREIGN KEY (mark_id) REFERENCES car_mark (id);
ALTER TABLE car
ADD CONSTRAINT car_car_model_id_fk
FOREIGN KEY (model_id) REFERENCES car_model (id);
ALTER TABLE car
ADD CONSTRAINT car_car_status_id_fk
FOREIGN KEY (car_status_id) REFERENCES car_status (id);

ALTER TABLE car_mark
ADD CONSTRAINT car_mark_country_of_manufacture_id_fk
FOREIGN KEY (country_of_manufacture_id) REFERENCES country_of_manufacture (id);

ALTER TABLE `order`
ADD CONSTRAINT order_user_id_fk
FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE `order`
ADD CONSTRAINT order_car_id_fk
FOREIGN KEY (car_id) REFERENCES car (id);

ALTER TABLE car MODIFY price DECIMAL NOT NULL;

ALTER TABLE `order` MODIFY total_price DECIMAL NOT NULL;

INSERT INTO `car_rental_db`.`country_of_manufacture` (`id`, `country`) VALUES (1, 'germany');
INSERT INTO `car_rental_db`.`country_of_manufacture` (`id`, `country`) VALUES (2, 'france');
INSERT INTO `car_rental_db`.`country_of_manufacture` (`id`, `country`) VALUES (3, 'usa');
INSERT INTO `car_rental_db`.`car_mark` (`id`, `mark`, `country_of_manufacture_id`) VALUES (1, 'volkswagen', 1);
INSERT INTO `car_rental_db`.`car_mark` (`id`, `mark`, `country_of_manufacture_id`) VALUES (2, 'citroen', 2);
INSERT INTO `car_rental_db`.`car_mark` (`id`, `mark`, `country_of_manufacture_id`) VALUES (3, 'renault', 2);
INSERT INTO `car_rental_db`.`car_mark` (`id`, `mark`, `country_of_manufacture_id`) VALUES (4, 'gmc', 3);
INSERT INTO `car_rental_db`.`car_model` (`id`, `model`) VALUES (1, 'polo');
INSERT INTO `car_rental_db`.`car_model` (`id`, `model`) VALUES (2, 'c4');
INSERT INTO `car_rental_db`.`car_model` (`id`, `model`) VALUES (3, 'c5');
INSERT INTO `car_rental_db`.`car_model` (`id`, `model`) VALUES (4, 'vesta');
INSERT INTO `car_rental_db`.`car_model` (`id`, `model`) VALUES (5, 'terrain');
INSERT INTO `car_rental_db`.`car_model` (`id`, `model`) VALUES (6, 'logan');
INSERT INTO `car_rental_db`.`car_status` (`id`, `car_status`) VALUES (1, 'free');
INSERT INTO `car_rental_db`.`car_status` (`id`, `car_status`) VALUES (2, 'busy');
INSERT INTO `car_rental_db`.`car_status` (`id`, `car_status`) VALUES (3, 'service');
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (1, 1, 1, 2563, 10, 1);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (2, 1, 1, 9564, 10, 2);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (3, 2, 2, 7896, 7, 1);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (4, 2, 2, 10269, 7, 1);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (5, 2, 3, 5697, 15, 1);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (6, 4, 4, 56897, 10, 2);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (7, 4, 4, 2356, 10, 1);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (8, 4, 5, 12131, 5, 1);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (9, 4, 5, 12345, 5, 3);
INSERT INTO `car_rental_db`.`car` (`id`, `mark_id`, `model_id`, `millage`, `price`, `car_status_id`) VALUES (10, 3, 6, 1564, 8, 1);
INSERT INTO `car_rental_db`.`role` (`id`, `role`) VALUES (1, 'user');
INSERT INTO `car_rental_db`.`role` (`id`, `role`) VALUES (2, 'admin');