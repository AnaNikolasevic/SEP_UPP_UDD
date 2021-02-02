insert into genre (genre_id, name) values (1, 'thriller');
insert into genre (genre_id, name) values(2, 'romance');
insert into genre (genre_id, name) values(3, 'horror');
insert into genre (genre_id, name) values(4, 'action');
insert into genre (genre_id, name) values(5, 'drama');
insert into genre (genre_id, name) values(6, 'fiction');

insert into editor (id, city, country, email, enabled, first_name, last_name, password, username)
    values (112, 'Novi Sad', 'Serbia', 'mc@gmail.com', 'true', 'Milica', 'Culibrk', 'mc', 'mc');

insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (223, 'Novi Sad', 'Serbia', 'sv@gmail.com', 'true', 'Sara', 'Vukoje', 'sv', 'sv');
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (224, 'Novi Sad', 'Serbia', 'mg@gmail.com', 'true', 'Milana', 'Galin', 'mg', 'mg');
<<<<<<< HEAD

insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
    values (113, 'Novi Sad', 'Serbia', 'mm@gmail.com', 'true', 'Masa', 'Matovic', 'mm', 'mm');
insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
    values (114, 'Novi Sad', 'Serbia', 'an@gmail.com', 'true', 'Ana', 'Nikolasevic', 'an', 'an');
insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
    values (115, 'Novi Sad', 'Serbia', 'ss@gmail.com', 'true', 'Stanislava', 'Skoric', 'ss', 'ss');
=======
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (225, 'Novi Sad', 'Serbia', 'it@gmail.com', 'true', 'Isidora', 'Tepic', 'it', 'it');
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (226, 'Novi Sad', 'Serbia', 'sdj@gmail.com', 'true', 'Sara', 'Djokovic', 'sdj', 'sdj');    
--
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (113, 'Novi Sad', 'Serbia', 'mm@gmail.com', 'true', 'Masa', 'Matovic', 'mm', 'mm');
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (114, 'Novi Sad', 'Serbia', 'an@gmail.com', 'true', 'Ana', 'Nikolasevic', 'an', 'an');
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (115, 'Novi Sad', 'Serbia', 'ss@gmail.com', 'true', 'Stanislava', 'Skoric', 'ss', 'ss');
>>>>>>> origin/master

insert into writer ( id, city, country, email, enabled, first_name, last_name, password, username)
    values (215, 'Novi Sad', 'Serbia', 'sm@gmail.com', 'true', 'Stevan', 'Matovic', 'sm', 'sm');
insert into writer ( id, city, country, email, enabled, first_name, last_name, password, username)
    values (216, 'Novi Sad', 'Serbia', 'bm@gmail.com', 'true', 'Biljana', 'Matovic', 'bm', 'bm');

insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id)
    values (0, 'false', 'The classic story of an innocent man wrongly, but deliberately imprisoned and his brilliant strategy for revenge against those who betrayed him. Dashing young sailor Edmond Dantes  is a guileless and honest young man whose plans are suddenly destroyed.', 'The Count of Monte Cristo', null, 112, 5, null, null);

insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id)
    values (0, 'false', 'The classic story of an innocent man wrongly, but deliberately imprisoned and his brilliant strategy for revenge against those who betrayed him. Dashing young sailor Edmond Dantes  is a guileless and honest young man whose plans are suddenly destroyed.', 'The song of ice and fire', null, 112, 6, null, null);

insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id)
    values (0, 'false', 'The classic story of an innocent man wrongly, but deliberately imprisoned and his brilliant strategy for revenge against those who betrayed him. Dashing young sailor Edmond Dantes  is a guileless and honest young man whose plans are suddenly destroyed.', 'Les Miserables', null, 112, 5, null, null);