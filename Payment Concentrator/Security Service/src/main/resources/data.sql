
insert into seller (seller_id, name, merchant_password, merchant_id) values (1, 'LU1', 'abc', 'abc');
insert into payment_type (payment_type_id, name, seller_id) values (1, 'paypal', 1);
insert into payment_type (payment_type_id, name, seller_id) values (2, 'bitcoin', 1);
insert into payment_type (payment_type_id, name, seller_id) values (3, 'card', 1);

insert into seller (name) values ('LU1');
insert into payment_type (name) values ('paypal');
insert into payment_type (name) values ('bitcoin');
insert into payment_type (name) values ('card');
insert into seller_payment_types (seller_id, payment_type_id) values (1, 1);
insert into seller_payment_types (seller_id, payment_type_id) values (1, 2);


