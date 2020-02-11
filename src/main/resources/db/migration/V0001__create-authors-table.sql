CREATE TABLE authors (
    id BIGSERIAL,
    name VARCHAR(100) NOT NULL,

    CONSTRAINT pk_authors PRIMARY KEY(id)
);

CREATE INDEX author_name ON authors(name);