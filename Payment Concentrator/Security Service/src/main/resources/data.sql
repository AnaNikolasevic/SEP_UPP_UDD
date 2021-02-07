
insert into seller ( name, merchant_password, merchant_id) values ( 'LU1', 'abc', 'abc');

INSERT INTO public.payment_type(
	 error_url, failed_url, name, success_url)
	VALUES ( '', '', 'paypal', '');
INSERT INTO public.payment_type(
	 error_url, failed_url, name, success_url)
	VALUES ( '', '', 'bitcoin', '');
INSERT INTO public.payment_type(
	 error_url, failed_url, name, success_url)
	VALUES ( 'http://localhost:8083/aikBankError', 'http://localhost:8083/aikBankFailed', 'card', 'http://localhost:8083/aikBankSuccess');



insert into seller_payment_types (seller_id, payment_type_id) values (1, 1);
insert into seller_payment_types (seller_id, payment_type_id) values (1, 3);

