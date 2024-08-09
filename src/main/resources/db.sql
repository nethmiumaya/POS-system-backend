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

    create table orders(
                         order_id VARCHAR(100) PRIMARY KEY,
                         date VARCHAR(20) NOT NULL ,
                         cust_id VARCHAR(100) NOT NULL ,
                         total DOUBLE NOT NULL ,
                         discount DOUBLE NOT NULL ,
                         subTotal DOUBLE NOT NULL ,
                         cash DOUBLE NOT NULL ,
                         balance DOUBLE NOT NULL ,
        FOREIGN KEY (cust_id) references customer(custId)
                   on update cascade
                   on delete cascade
    );

    create table order_detail(
                    order_id VARCHAR(100) ,
                    item_id VARCHAR(100),
                    qty INT NOT NULL,
         FOREIGN KEY (order_id) references orders(order_id)
                   on update cascade
                   on delete cascade,
        FOREIGN KEY (item_id) references item(itemId)
                        on update cascade
                        on delete cascade
    );

