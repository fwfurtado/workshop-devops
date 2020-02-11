package br.com.caelum.devops.api.books.show

import br.com.caelum.devops.domain.Book

fun mapToResponse(book: Book): BookResponse {
    val author = book.author
    val authorResponse = AuthorResponse(author.name)

    return BookResponse(book.title, authorResponse, book.releaseDate)
}