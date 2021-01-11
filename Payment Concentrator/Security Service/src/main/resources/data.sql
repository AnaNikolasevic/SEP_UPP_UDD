insert into seller (name) values ('LU1');
insert into payment_type (name) values ('paypal');
insert into payment_type (name) values ('bitcoin');
insert into payment_type (name) values ('card');
insert into seller_payment_types (seller_id, payment_type_id) values (1, 1);
insert into seller_payment_types (seller_id, payment_type_id) values (1, 2);

