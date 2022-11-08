


use Blaps;



create table if not exists Customer(
	customerId int primary key auto_increment,
    firstName varchar(20) not null,
    lastName varchar(20),
    emailAddress tinytext not null,
    userName varchar(30) not null,
    password varchar(150),
    role varchar(10)
);



Create table if not exists CustomerAddress(
	customerId int,
    street varchar(50),
    city varchar(50),
    country varchar(50),
    postalcode varchar(15),
    foreign key (customerId) references Customer(customerId) on delete cascade
    
);





Create table if not exists CustomerPhone(
	customerId int,
    phoneNumber varchar(15),
    foreign key (customerId) references Customer(customerId) on delete cascade

);

create table if not exists Category(
	categoryId int primary key auto_increment,
    title varchar(15)

);

describe category;



create table if not exists Product(
	productId int primary key auto_increment,
    categoryId int,
    title varchar(50),
    imageLink tinytext,
    description tinytext,
    unitPrice decimal(15,2),
    smallInStock int default 0,
    mediumInStock int default 0,
    largeInStock int default 0,
    
    foreign key(categoryId) references Category(categoryId) on delete cascade
);


create table if not exists Review(
    CustomerId int,
	ProductId int,
    description tinytext not null,
    rating int,
    foreign key(productId) references Product(productId) on delete cascade,
    foreign key(customerId) references Customer(customerId) on delete cascade
    
);



create table if not exists OrderItem(
    
	orderId int primary key auto_increment,
    productId int,
    quantity int,
    size varchar(10),
    customerId int,
    createdDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    foreign key(customerId) references Customer(customerId) on delete cascade,
    foreign key(productId) references Product(productId) on delete cascade
);


create table if not exists CartItem(
    customerId int,
    productId int,
    unitPrice decimal(15,2),
    quantity int,
    size varchar(10),
    foreign key(customerId) references Customer(customerId) on delete cascade,
    foreign key(productId) references Product(productId) on delete cascade
);

alter table product add fulltext(title,description);

insert into Customer(customerId,firstName,lastName,emailAddress,username,password,role) values (1,"mourya","naik","mourya@gmail.com", "mourya","$2a$10$LDytrMAHj.VZmmkmWGmwXepelQRlwhyjQbAVhYmkCidr4yBZhRu/G","admin");
select * from Customer;
insert into Category(categoryId,title) values (1,"Shirts");
insert into Category(categoryId,title) values (2,"Pants");
insert into Category(categoryId,title) values (3,"Tops");
insert into Category(categoryId,title) values (4,"Searched");

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (1,1,"Marvel:Comic Pattern","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1663569914_7401389.jpg?format=webp&w=480&dpr=1.0","Show some love to your favourite comics. Buy this official Marvel summer shirt from Blaps",999,100,100,100);

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (2,2,"Solids: Olive","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1665185873_7594390.jpg?format=webp&w=480&dpr=1.0","Buy Solids Olive Melange Men Cargo Joggers Online from Blaps",499,70,40,50);

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (3,3,"Blaps: White","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1649392126_2966792.jpg?format=webp&w=480&dpr=1.0","Pair this t-shirt with The Blaps Store Joggers to ace a casual look.",799,100,90,70);

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (4,3,"Powerpuff Girls: Bubbles","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1667812360_7071895.jpg?format=webp&w=480&dpr=1.4","By The Powerpuff Girls.",1399,56,42,21);

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (5,1,"Minions: Made For Summer","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1651763326_1113400.jpg?format=webp&w=480&dpr=1.4","By Minions",999,45,19,7);

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (6,1,"Superman: Man Of Steel","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1658240827_6706739.jpg?format=webp&w=480&dpr=1.4","By DC Comics™",1299,27,17,9);

insert into Product(productId,categoryId,title,imagelink,description,unitPrice,smallInStock,mediumInStock,largeInStock) values (7,2,"Solids: Mushroom","https://prod-img.thesouledstore.com/public/theSoul/uploads/catalog/product/1665185361_4760574.jpg?format=webp&w=480&dpr=1.4","By DC Blaps™",799,17,12,5);
