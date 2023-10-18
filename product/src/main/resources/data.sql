drop table if exists product;

create table product
(
    id       int    not null auto_increment,
    name     text   not null,
    price    double not null,
    quantity int    not null,
    primary key (id)
);

insert into product (id, name, price, quantity)
values (1, 'Product A', 2.0, 5),
       (2, 'Product B', 3.0, 4),
       (3, 'Product B', 0.5, 1),
       (4, 'Product B', 11.0, 0),
       (5, 'Product B', 15.0, 12),
       (6, 'Product B', 2.4, 4);
