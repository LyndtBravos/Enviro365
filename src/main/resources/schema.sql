create table investor(
    ID int auto_increment,
    name varchar(65) NOT NULL,
    age int NOT NULL,
    amount double NOT NULL,
    PRIMARY KEY (ID)
);

create table product(
    productID int auto_increment,
    name varchar(65) NOT NULL,
    type varchar(65) NOT NULL,
    balance double NOT NULL,
    investorID int NOT NULL,
    PRIMARY KEY (productID),
    FOREIGN KEY (investorid) REFERENCES investor(ID)
);

create table address(
    addressID int auto_increment,
    street varchar(65) NOT NULL,
    city varchar(65) NOT NULL,
    province varchar(65) NOT NULL,
    country varchar(65) NOT NULL,
    investorId int,
    PRIMARY KEY (addressID),
    FOREIGN KEY (investorId) REFERENCES investor(ID)
);

create table contact(
    contactID int auto_increment,
    contactType varchar(65) NOT NULL,
    contactValue varchar(65) NOT NULL,
    investorID int,
    PRIMARY KEY (contactID),
    FOREIGN KEY (investorID) REFERENCES investor(ID)
);

create table notice(
   noticeID int auto_increment,
   name varchar(65) NOT NULL,
   productID int NOT NULL,
   withdrawalAmount double NOT NULL,
   withdrawalDate date NOT NULL,
   statementDate date NOT NULL,
   investorID int,
   PRIMARY KEY (noticeID),
   FOREIGN KEY (productID) REFERENCES product(productID),
   FOREIGN KEY (investorID) REFERENCES investor(ID)
);

insert into investor(name, age, amount) values ('Brian Mthembu', 24, 15000);
insert into investor(name, age, amount) values ('Future X', 23, 17000);