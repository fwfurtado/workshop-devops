CREATE TABLE books (
    id BIGSERIAL,
    title VARCHAR(100) NOT NULL,
    release_date DATE,
    author_id BIGINT NOT NULL,

    CONSTRAINT pk_books PRIMARY KEY(id),
    CONSTRAINT fk_books_to_author FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE INDEX books_title ON books(title);