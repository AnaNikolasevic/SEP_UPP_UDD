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


-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (113, 'Novi Sad', 'Serbia', 'mm@gmail.com', 'true', 'Masa', 'Matovic', 'mm', 'mm');
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (114, 'Novi Sad', 'Serbia', 'an@gmail.com', 'true', 'Ana', 'Nikolasevic', 'an', 'an');
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (115, 'Novi Sad', 'Serbia', 'ss@gmail.com', 'true', 'Stanislava', 'Skoric', 'ss', 'ss');


--insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
--    values (225, 'Novi Sad', 'Serbia', 'it@gmail.com', 'true', 'Isidora', 'Tepic', 'it', 'it');
--insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
--    values (226, 'Novi Sad', 'Serbia', 'sdj@gmail.com', 'true', 'Sara', 'Djokovic', 'sdj', 'sdj');    
--
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (113, 'Novi Sad', 'Serbia', 'mm@gmail.com', 'true', 'Masa', 'Matovic', 'mm', 'mm');
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (114, 'Novi Sad', 'Serbia', 'an@gmail.com', 'true', 'Ana', 'Nikolasevic', 'an', 'an');
-- insert into editor ( id, city, country, email, enabled, first_name, last_name, password, username)
--     values (115, 'Novi Sad', 'Serbia', 'ss@gmail.com', 'true', 'Stanislava', 'Skoric', 'ss', 'ss');

insert into writer ( id, city, country, email, enabled, first_name, last_name, password, username, publish_enabled)
    values (215, 'Novi Sad', 'Serbia', 'sm@gmail.com', 'true', 'Stevan', 'Matovic', 'sm', 'sm', 'true');
insert into writer ( id, city, country, email, enabled, first_name, last_name, password, username, publish_enabled)
    values (216, 'Novi Sad', 'Serbia', 'bm@gmail.com', 'true', 'Biljana', 'Matovic', 'bm', 'bm', 'true');

insert into beta_reader ( id, city, country, email, enabled, first_name, last_name, password, username, penalty_points)
    values (315, 'Novi Sad', 'Serbia', 'sm@gmail.com', 'true', 'Sanja', 'Culibrk', 'sc', 'sc', 2);
insert into beta_reader ( id, city, country, email, enabled, first_name, last_name, password, username, penalty_points)
    values (316, 'Novi Sad', 'Serbia', 'bm@gmail.com', 'true', 'Branko', 'Culibrk', 'bc', 'bc', 0);
insert into beta_reader ( id, city, country, email, enabled, first_name, last_name, password, username, penalty_points)
    values (317, 'Novi Sad', 'Serbia', 'milicaculibrk7@gmail.com', 'true', 'Draginja', 'Culibrk', 'dc', 'dc', 4);

insert into lector (id, city, country, email, enabled, first_name, last_name, password, username)
    values (50, 'Novi Sad', 'Serbia', 'vm@gmail.com', 'true', 'Veljko', 'Matovic', 'vm', 'vm');


insert into reader_genre(users_id_seq, genre_id)
    values (315, 1);
insert into reader_genre(users_id_seq, genre_id)
    values (316, 1);
insert into reader_genre(users_id_seq, genre_id)
    values (317, 1);

insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (315, 1);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (315, 2);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (315, 3);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (315, 4);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (315, 5);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (315, 6);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (316, 1);
insert into reader_genre_beta(users_id_seq, genre_beta_id)
    values (317, 1);


insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id, path)
    values (8, 'false', 'The classic story of an innocent man wrongly ', 'Hamlet', null, 112, 5, null, 215, 'https://firebasestorage.googleapis.com/v0/b/uppproject-5285f.appspot.com/o/hamlet.pdf?alt=media&token=30962088-1ec5-41fd-96c2-1182454ff945');

insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id, path)
    values (8, 'false', 'The classic story of an innocent man wrongly', 'Utopia', null, 112, 6, null, 215, 'https://firebasestorage.googleapis.com/v0/b/uppproject-5285f.appspot.com/o/utopia.pdf?alt=media&token=7fb9212c-d25f-47ba-b490-f3b71bbba9a4');

insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id, path)
    values (8, 'false', 'The classic story of an innocent man wrongly,', 'The diary of a young girl ', null, 112, 5, null, null, 'https://firebasestorage.googleapis.com/v0/b/uppproject-5285f.appspot.com/o/the_diary_of_a_young_girl.pdf?alt=media&token=3432439f-781b-46e4-986f-8ffd70daf1cb');


insert into book (author, synopsis, title,  path, price)
    values ( 'William Shakespeare', 'The classic story of an innocent man wrongly ', 'Hamlet',  'https://firebasestorage.googleapis.com/v0/b/uppproject-5285f.appspot.com/o/hamlet.pdf?alt=media&token=30962088-1ec5-41fd-96c2-1182454ff945', 12);

insert into book (author, synopsis, title,  path, price)
    values ('Sir Thomas More', 'The classic story of an innocent man wrongly', 'Utopia',  'https://firebasestorage.googleapis.com/v0/b/uppproject-5285f.appspot.com/o/utopia.pdf?alt=media&token=7fb9212c-d25f-47ba-b490-f3b71bbba9a4', 23);

insert into book (author, synopsis, title,  path, price)
    values ( 'Anne Frank', 'The classic story of an innocent man wrongly,', 'The diary of a young girl ', 'https://firebasestorage.googleapis.com/v0/b/uppproject-5285f.appspot.com/o/the_diary_of_a_young_girl.pdf?alt=media&token=3432439f-781b-46e4-986f-8ffd70daf1cb', 18);