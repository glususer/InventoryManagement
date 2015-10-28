create database InventoryManagement

CREATE TABLE Customer(
id varchar(255) PRIMARY KEY,
name varchar(255) UNIQUE,
address varchar(255)
);

CREATE TABLE Seller(
id varchar(255) PRIMARY KEY,
name varchar(255),
address varchar(255)
);

CREATE TABLE CustomerItem(
CustomerId varchar(255),
ItemName varchar(255),
ItemCode varchar(255),
Quantity int,
PRIMARY KEY(ItemCode, CustomerId),
FOREIGN KEY (CustomerId) REFERENCES Customer (id)
);




