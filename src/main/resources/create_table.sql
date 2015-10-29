create database InventoryManagement

CREATE TABLE Customer(
id varchar(255) PRIMARY KEY,
name varchar(255) UNIQUE NOT NULL,
address varchar(255)
);

CREATE TABLE Seller(
id varchar(255) PRIMARY KEY,
name varchar(255) UNIQUE NOT NULL,
address varchar(255)
);

CREATE TABLE CustomerItem(
CustomerName varchar(255) NOT NULL,
ItemName varchar(255) NOT NULL,
ItemCode varchar(255) NOT NULL,
Quantity int NOT NULL,
PRIMARY KEY(CustomerName, ItemName),
CONSTRAINT cust_name FOREIGN KEY (CustomerName) REFERENCES Customer(name) ON DELETE CASCADE
);




