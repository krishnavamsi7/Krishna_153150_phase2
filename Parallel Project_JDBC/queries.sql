create table customer
(mobilenumber NUMBER(10) PRIMARY KEY,
name varchar(20),
balance decimal(10));


create table transactions
(mobilenumber NUMBER(10),
transactionType varchar(100),
amount decimal(10));