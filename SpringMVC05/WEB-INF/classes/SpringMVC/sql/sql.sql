create table book
(
       id int not null primary key,
       name varchar(16) not null,
       category_id int default null,
       category_name varchar(16) default null,
       author varchar(16) default null,
       price float not null
);