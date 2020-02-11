package br.com.caelum.devops.api.books.creation

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@RestController
@RequestMapping("books")
class BookCreateController(private val service: CreateBookService) {

    @PostMapping
    fun create(@RequestBody @Valid input: CreateBookRequest, uriBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        val showURL = uriBuilder.path("/books/{id}")

        val url = service.createBy(input)?.let { it -> showURL.build(it) }

        val response = url?.let(::created) ?: throw IllegalArgumentException("Cannot parse id")

        return response.build()
    }
}

data class CreateBookRequest(
        @field:NotBlank
        var title: String,


        @field:NotNull
        @field:Positive
        var authorId: Long,

        var releaseDate: LocalDate? = null
)
