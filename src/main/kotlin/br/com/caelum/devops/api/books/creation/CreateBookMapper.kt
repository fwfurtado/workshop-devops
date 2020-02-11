package br.com.caelum.devops.api.books.creation

import br.com.caelum.devops.domain.Author
import br.com.caelum.devops.domain.Book

fun mapToBook(input: CreateBookRequest, author: Author): Book {
    return Book(title=input.title, releaseDate = input.releaseDate, author = author)
}

