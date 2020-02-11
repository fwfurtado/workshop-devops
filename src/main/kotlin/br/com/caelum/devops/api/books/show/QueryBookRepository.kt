package br.com.caelum.devops.api.books.show

import br.com.caelum.devops.domain.Book

interface QueryBookRepository {
    fun findById(id: Long): Book?
    fun findAll(): List<Book>

}
