-- Insert Publishers
INSERT INTO publisher (id, name, archived) VALUES
('1e7d1e7d-1e7d-1e7d-1e7d-1e7d1e7d1e7d', 'Publisher One', false),
('2e7d2e7d-2e7d-2e7d-2e7d-2e7d2e7d2e7d', 'Publisher Two', false);

-- Insert Authors
INSERT INTO author (id, name, archived) VALUES
('3e7d3e7d-3e7d-3e7d-3e7d-3e7d3e7d3e7d', 'Author One', false),
('4e7d4e7d-4e7d-4e7d-4e7d-4e7d4e7d4e7d', 'Author Two', false);

-- Insert Genres
INSERT INTO genre (id, name, archived) VALUES
('5e7d5e7d-5e7d-5e7d-5e7d-5e7d5e7d5e7d', 'Genre One', false),
('6e7d6e7d-6e7d-6e7d-6e7d-6e7d6e7d6e7d', 'Genre Two', false);

-- Insert Topics
INSERT INTO topic (id, name, archived) VALUES
('7e7d7e7d-7e7d-7e7d-7e7d-7e7d7e7d7e7d', 'Topic One', false),
('8e7d8e7d-8e7d-8e7d-8e7d-8e7d8e7d8e7d', 'Topic Two', false);

-- Insert Books
INSERT INTO book (id, name, archived, edition, language, year, pages, observation, donation, asset_number, isbn, url_cover, publisher_id) VALUES
('9e7d9e7d-9e7d-9e7d-9e7d-9e7d9e7d9e7d', 'Book One', false, 'First Edition', 'English', 2021, 300, 'Observation One', true, 'AN123', 'ISBN123', 'http://example.com/cover1', '1e7d1e7d-1e7d-1e7d-1e7d-1e7d1e7d1e7d'),
('ae7dae7d-ae7d-ae7d-ae7d-ae7dae7dae7d', 'Book Two', false, 'Second Edition', 'Spanish', 2022, 400, 'Observation Two', false, 'AN456', 'ISBN456', 'http://example.com/cover2', '2e7d2e7d-2e7d-2e7d-2e7d-2e7d2e7d2e7d');

-- Insert Book-Author relationships
INSERT INTO book_authors (book_id, authors_id) VALUES
('9e7d9e7d-9e7d-9e7d-9e7d-9e7d9e7d9e7d', '3e7d3e7d-3e7d-3e7d-3e7d-3e7d3e7d3e7d'),
('ae7dae7d-ae7d-ae7d-ae7d-ae7dae7dae7d', '4e7d4e7d-4e7d-4e7d-4e7d-4e7d4e7d4e7d');

-- Insert Book-Genre relationships
INSERT INTO book_genres (book_id, genres_id) VALUES
('9e7d9e7d-9e7d-9e7d-9e7d-9e7d9e7d9e7d', '5e7d5e7d-5e7d-5e7d-5e7d-5e7d5e7d5e7d'),
('ae7dae7d-ae7d-ae7d-ae7d-ae7dae7dae7d', '6e7d6e7d-6e7d-6e7d-6e7d-6e7d6e7d6e7d');

-- Insert Book-Topic relationships
INSERT INTO book_topics (book_id, topics_id) VALUES
('9e7d9e7d-9e7d-9e7d-9e7d-9e7d9e7d9e7d', '7e7d7e7d-7e7d-7e7d-7e7d-7e7d7e7d7e7d'),
('ae7dae7d-ae7d-ae7d-ae7d-ae7dae7dae7d', '8e7d8e7d-8e7d-8e7d-8e7d-8e7d8e7d8e7d');
