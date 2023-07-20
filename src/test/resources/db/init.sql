create database products_system

/connect products_system;

create table products(
 id bigint primary key generated always as identity;
 name character varying(255) not null;
 availability boolean not null;
 price decimal(5,2) unsigned;
 productSimilar integer[];


CONSTRAINT products_name_uniq UNIQUE (name)
);

insert into product values (1, 'manzana',TRUE,4.3,ARRAY[1,2,3]);
insert into product values (2, 'fresa',TRUE,1.2,ARRAY[2,1,3]);
insert into product values (3, 'pera',TRUE,2.8,ARRAY[3,2,1]);
