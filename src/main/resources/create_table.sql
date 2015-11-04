drop database InventoryManagement;
create database InventoryManagement;
use InventoryManagement;

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

CREATE TABLE InventoryList(
ItemName varchar(255) NOT NULL PRIMARY KEY,
ItemCode varchar(255) NOT NULL UNIQUE ,
Price int NOT NULL,
Quantity int NOT NULL
);

CREATE TABLE SellerItem (
SellerName varchar(255) NOT NULL,
ItemName varchar(255) NOT NULL,
ItemCode varchar(255) NOT NULL,
Quantity int NOT NULL,
PRIMARY KEY(SellerName, ItemName),
CONSTRAINT seller_name FOREIGN KEY (SellerName) REFERENCES Seller(name) ON DELETE CASCADE,
CONSTRAINT item_name FOREIGN KEY(ItemName) REFERENCES InventoryList (ItemName) ON DELETE CASCADE
);






