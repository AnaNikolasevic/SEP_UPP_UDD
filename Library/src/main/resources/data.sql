insert into genre (genre_id, name) values (1, 'thriller');
insert into genre (genre_id, name) values(2, 'romance');
insert into genre (genre_id, name) values(3, 'horror');
insert into genre (genre_id, name) values(4, 'action');
insert into genre (genre_id, name) values(5, 'drama');

insert into editor (id, city, country, email, enabled, first_name, last_name, password, username)
    values (112, 'Novi Sad', 'Serbia', 'mc@gmail.com', 'true', 'Milica', 'Culibrk', 'mc', 'mc');
    
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (222, 'Novi Sad', 'Serbia', 'an@gmail.com', 'true', 'Ana', 'Nikoalsevic', 'an', 'an');
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (223, 'Novi Sad', 'Serbia', 'sv@gmail.com', 'true', 'Sara', 'Vukoje', 'sv', 'sv');
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (224, 'Novi Sad', 'Serbia', 'mg@gmail.com', 'true', 'Milana', 'Galin', 'mg', 'mg');
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (225, 'Novi Sad', 'Serbia', 'it@gmail.com', 'true', 'Isidora', 'Tepic', 'it', 'it');
insert into board_member (id, city, country, email, enabled, first_name, last_name, password, username)
    values (226, 'Novi Sad', 'Serbia', 'sdj@gmail.com', 'true', 'Sara', 'Djokovic', 'sdj', 'sdj');    
insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id)
    values (0, 'false', 'Book about justice and revenge.', 'The Count of Monte Cristo', null, 112, 5, null, null);