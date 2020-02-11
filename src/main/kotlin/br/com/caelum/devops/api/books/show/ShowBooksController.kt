package br.com.caelum.devops.api.books.show

import br.com.caelum.devops.infra.ResourceNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class ShowBooksController(private val repository: QueryBookRepository) {

    @GetMapping("{id}")
    fun show(@PathVariable id: Long) : BookResponse {
        return  repository.findById(id)?.let(::mapToResponse) ?: throw ResourceNotFoundException()
    }
}



