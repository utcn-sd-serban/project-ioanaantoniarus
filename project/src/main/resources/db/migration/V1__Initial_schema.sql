CREATE TABLE IF NOT EXISTS book(
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(64),
  author VARCHAR(64),
  isbn VARCHAR(20),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS review(
  id int NOT NULL AUTO_INCREMENT,
  id_book int NOT NULL,
  title VARCHAR(32) NOT NULL,
  text VARCHAR(64),
  username VARCHAR(32) NOT NULL,
  date VARCHAR(64),
  PRIMARY KEY(id),
  FOREIGN KEY (id_book) REFERENCES book(id)
);
CREATE TABLE IF NOT EXISTS user(
  id int NOT NULL AUTO_INCREMENT,
  username VARCHAR(32) NOT NULL,
  password VARCHAR(512 ) NOT NULL,
  type VARCHAR(10) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS genre(
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(32),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS book_genre(
  id_genre int NOT NULL,
  id_book int NOT NULL,
  FOREIGN KEY (id_genre) REFERENCES genre(id),
  FOREIGN KEY (id_book) REFERENCES book(id)
);

CREATE TABLE IF NOT EXISTS rating(
  id int NOT NULL,
  rating_sum int NOT NULL,
  total_ratings int NOT NULL,
  final_rating FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS comment(
  id int NOT NULL AUTO_INCREMENT,
  text VARCHAR(100),
  username VARCHAR(32) NOT NULL,
  date VARCHAR(64),
  review_id int NOT NULL,
  PRIMARY KEY(id)
);