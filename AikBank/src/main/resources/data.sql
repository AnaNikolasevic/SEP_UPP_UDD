-- INSERT INTO public.customer(
-- 	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
-- 	VALUES (1, '656 555 666', 1000.0,  'Marko Markovic', 222, 'USD', '01-02-2023', 'mc', 'mc', 0.0);
--
-- INSERT INTO public.customer(
-- 	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
-- 	VALUES (2, '656 555 111', 2000.0, 'LU1', 292, 'USD', '01-12-2021', 'abc', 'abc', 0.0);
--
-- INSERT INTO public.customer(
-- 	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
-- 	VALUES (3, '656 555 222', 35.0,  'Pera Peric', 111, 'USD', '01-02-2023', 'pp', 'pp', 0.0);


--PAN: 666 555 444 SC: 222
INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (1, '9b44c0d8db1872fddce41cbee765dc396a5b199be5747d4a380e02a372d2e003', 1000.0,  'Marko Markovic', '9b871512327c09ce91dd649b3f96a63b7408ef267c8cc5710114e629730cb61f', 'USD', '01-02-2023', 'mc', 'mc', 0.0);
--PAN: 666 555 333 SC: 111
INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (2, 'd8acb10f1532909c443293810c5f223cbb54b2bea848a254dcddac51367da5fe', 2000.0, 'LU1', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', 'USD', '01-12-2021', 'abc', 'abc', 0.0);
--PAN: 666 555 111 SC: 333
INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (3, '2639962c2e6effa91201ed4a705ded4b3358b750c3f51623a6ecbc40c9b8fa82', 35.0,  'Pera Peric', '556d7dc3a115356350f1f9910b1af1ab0e312d4b3e4fc788d2da63668f36d017', 'USD', '01-02-2023', 'pp', 'pp', 0.0);