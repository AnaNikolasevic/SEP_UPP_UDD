
--PAN: 444 333 222 SC: 222
INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (1, 'b94184904981c06d05e05e86d9d7cfbd2018c21e9dbfd814524e74fd6a53bc15', 1000.0,  'Marko Markovic', '9b871512327c09ce91dd649b3f96a63b7408ef267c8cc5710114e629730cb61f', 'USD', '01-02-2023', 'mc', 'mc', 0.0);
--PAN: 444 333 111 SC: 111
INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (2, 'b8ebb5f2a5c989eb8f561bee8f2852ddb76b4ab2a030e099c3eda79d68acefad', 2000.0, 'LU1', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', 'USD', '01-12-2021', 'abc', 'abc', 0.0);
--PAN: 444 333 000 SC: 333
INSERT INTO public.customer(
	id, pan, available_funds, card_holder_name, credit_card_security_number, currency, expiration_date, merchant_id, password, reserved_funds)
	VALUES (3, '89094f023b6ec6a422b256b9496c054a34c6f66d9bc25adb9c3479189b1391d2', 35.0,  'Pera Peric', '556d7dc3a115356350f1f9910b1af1ab0e312d4b3e4fc788d2da63668f36d017', 'USD', '01-02-2023', 'pp', 'pp', 0.0);