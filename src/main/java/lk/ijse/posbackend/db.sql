create database if not exists POS_system;

use POS_system;
    create table customer(
            custId VARCHAR(100) PRIMARY KEY ,
            custName VARCHAR(50),
            custAddress VARCHAR(100),
            custSalary VARCHAR(30)

    );

create table item(
                     itemId VARCHAR(100) PRIMARY KEY ,
                     itemName VARCHAR(50),
                     itemQty VARCHAR(100),
                     itemPrice VARCHAR(30)

);
