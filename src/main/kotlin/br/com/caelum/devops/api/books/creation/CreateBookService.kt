package br.com.caelum.devops.api.books.creation

import br.com.caelum.devops.infra.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class CreateBookService(private val authors: FindAuthorRepository, private val repository: CreateBookRepository) {

    fun createBy(input: CreateBookRequest): Long? {
        val author = authors.findById(input.authorId) ?: throw ResourceNotFoundException()

        val book = mapToBook(input, author)

        repository.save(book)

        return book.id
    }

}
