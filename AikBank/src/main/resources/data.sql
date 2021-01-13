INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (1, '656 555 666', 1000.0,  'Marko Markovic', 222, 'USD', '01-02-2023', 'mc', 'mc', 0.0);

INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (2, '656 555 111', 2000.0, 'LU1', 292, 'USD', '01-12-2021', 'abc', 'abc', 0.0);

INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (3, '656 555 222', 35.0,  'Pera Peric', 111, 'USD', '01-02-2023', 'pp', 'pp', 0.0);
