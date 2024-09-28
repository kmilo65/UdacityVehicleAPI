CREATE SEQUENCE IF NOT EXISTS  HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS Car (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_At TIMESTAMP,
    modified_At TIMESTAMP,
    condition VARCHAR(4) NOT NULL,
    body VARCHAR(20) NOT NULL,
    model VARCHAR(20) NOT NULL,
    manufacturer_code BIGINT NOT NULL,
    number_of_doors INT,
    fuel_type VARCHAR(10),
    engine VARCHAR(10),
    mileage INT,
    model_Year INT,
    production_Year INT,
    external_Color VARCHAR(10),
    lat DOUBLE,
    lon DOUBLE
);

CREATE TABLE IF NOT EXISTS Manufacturer (
    code BIGINT PRIMARY KEY AUTO_INCREMENT,
    name Varchar(20) not null
 );