package br.com.caelum.devops.api.books.creation

import br.com.caelum.devops.domain.Author

interface FindAuthorRepository {
    fun findById(authorId: Long): Author?
}
