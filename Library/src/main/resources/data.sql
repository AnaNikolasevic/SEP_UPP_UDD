insert into genre (genre_id, name) values (1, 'thriller');
insert into genre (genre_id, name) values(2, 'romance');
insert into genre (genre_id, name) values(3, 'horror');
insert into genre (genre_id, name) values(4, 'action');
insert into genre (genre_id, name) values(5, 'drama');

insert into editor (id, city, country, email, enabled, first_name, last_name, password, username)
    values (112, 'Novi Sad', 'Serbia', 'mc@gmail.com', 'true', 'Milica', 'Culibrk', 'mc', 'mc');

insert into book_prototype (book_status, plagiarism, synopsis, title, beta_reader_id, editor_id, genre_genre_id, lector_id, writer_id)
    values (0, 'false', 'Book about justice and revenge.', 'The Count of Monte Cristo', null, 112, 5, null, null);