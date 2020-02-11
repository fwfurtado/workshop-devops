package br.com.caelum.devops.api.books.list

import br.com.caelum.devops.api.books.show.BookResponse
import br.com.caelum.devops.api.books.show.QueryBookRepository
import br.com.caelum.devops.api.books.show.mapToResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class ListBooksController(private val repository: QueryBookRepository) {

    @GetMapping
    fun list() : List<BookResponse> {
        return repository.findAll().map(::mapToResponse)
    }
}