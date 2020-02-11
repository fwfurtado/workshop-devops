package br.com.caelum.devops.api.books.creation

import br.com.caelum.devops.domain.Book

interface CreateBookRepository {
    fun save(book: Book)
}
