-- Clean up tables before inserting
DELETE FROM book_topics;
DELETE FROM book_genres;
DELETE FROM book_authors;
DELETE FROM book;
DELETE FROM topic;
DELETE FROM genre;
DELETE FROM author;
DELETE FROM publisher;
DELETE FROM customer;

-- Insert Customers
INSERT INTO customer (id, name, password, email, phone, role, archived) VALUES
('1e7d1e7d-1e7d-1e7d-1e7d-1e7d1e7d1e7d', 'Customer One', '$2a$12$u8r90IfnPDVaUWzSEMhbxezG5gnKNR2F0u2rubyuRPwPxJmVimO.C', 'one@customer.com', '123456789', 'ROLE_ADMIN', false),
('2e7d2e7d-2e7d-2e7d-2e7d-2e7d2e7d2e7d', 'Customer Two', '$2a$12$u8r90IfnPDVaUWzSEMhbxezG5gnKNR2F0u2rubyuRPwPxJmVimO.C', 'two@customer.com', '987654321', 'ROLE_LIBRARIAN', false);

-- Insert Publishers (IDs changed to avoid conflict with customers)
INSERT INTO publisher (id, name, archived) VALUES
('3e7d3e7d-3e7d-3e7d-3e7d-3e7d3e7d3e7d', 'Publisher One', false),
('4e7d4e7d-4e7d-4e7d-4e7d-4e7d4e7d4e7d', 'Publisher Two', false);

-- Insert Authors
INSERT INTO author (id, name, archived) VALUES
('5e7d5e7d-5e7d-5e7d-5e7d-5e7d5e7d5e7d', 'Author One', false),
('6e7d6e7d-6e7d-6e7d-6e7d-6e7d6e7d6e7d', 'Author Two', false);

-- Insert Genres
INSERT INTO genre (id, name, archived) VALUES
('7e7d7e7d-7e7d-7e7d-7e7d-7e7d7e7d7e7d', 'Genre One', false),
('8e7d8e7d-8e7d-8e7d-8e7d-8e7d8e7d8e7d', 'Genre Two', false);

-- Insert Topics
INSERT INTO topic (id, name, archived) VALUES
('9e7d9e7d-9e7d-9e7d-9e7d-9e7d9e7d9e7d', 'Topic One', false),
('ae7dae7d-ae7d-ae7d-ae7d-ae7dae7dae7d', 'Topic Two', false);

-- Insert Books (publisher_id updated to match new publisher IDs)
INSERT INTO book (id, name, archived, edition, language, publication_year, pages, observation, donation, asset_number, isbn, url_cover, publisher_id, available) VALUES
('be7dbe7d-be7d-be7d-be7d-be7dbe7dbe7d', 'Book One', false, 'First Edition', 'English', 2021, 300, 'Observation One', true, 'AN123', 'ISBN123', 'http://example.com/cover1', '3e7d3e7d-3e7d-3e7d-3e7d-3e7d3e7d3e7d', true),
('ce7dce7d-ce7d-ce7d-ce7d-ce7dce7dce7d', 'Book Two', false, 'Second Edition', 'Spanish', 2022, 400, 'Observation Two', false, 'AN456', 'ISBN456', 'http://example.com/cover2', '4e7d4e7d-4e7d-4e7d-4e7d-4e7d4e7d4e7d', true);

-- Insert Book-Author relationships (book and author IDs updated)
INSERT INTO book_authors (book_id, authors_id) VALUES
('be7dbe7d-be7d-be7d-be7d-be7dbe7dbe7d', '5e7d5e7d-5e7d-5e7d-5e7d-5e7d5e7d5e7d'),
('ce7dce7d-ce7d-ce7d-ce7d-ce7dce7dce7d', '6e7d6e7d-6e7d-6e7d-6e7d-6e7d6e7d6e7d');

-- Insert Book-Genre relationships
INSERT INTO book_genres (book_id, genres_id) VALUES
('be7dbe7d-be7d-be7d-be7d-be7dbe7dbe7d', '7e7d7e7d-7e7d-7e7d-7e7d-7e7d7e7d7e7d'),
('ce7dce7d-ce7d-ce7d-ce7d-ce7dce7dce7d', '8e7d8e7d-8e7d-8e7d-8e7d-8e7d8e7d8e7d');

-- Insert Book-Topic relationships
INSERT INTO book_topics (book_id, topics_id) VALUES
('be7dbe7d-be7d-be7d-be7d-be7dbe7dbe7d', '9e7d9e7d-9e7d-9e7d-9e7d-9e7d9e7d9e7d'),
('ce7dce7d-ce7d-ce7d-ce7d-ce7dce7dce7d', 'ae7dae7d-ae7d-ae7d-ae7d-ae7dae7dae7d');