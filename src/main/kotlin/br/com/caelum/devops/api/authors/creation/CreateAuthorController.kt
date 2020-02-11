package br.com.caelum.devops.api.authors.creation

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("authors")
class CreateAuthorController(private val service: CreateAuthorService) {

    @PostMapping
    fun create(@RequestBody @Valid input: CreateAuthorRequest, urlBuilder: UriComponentsBuilder): ResponseEntity<Any> {

        val showURL = urlBuilder.path("/authors/{id}")

        val uri = service.createBy(input)?.let { it -> showURL.build(it) }

        val response = uri?.let(::created) ?: throw IllegalArgumentException("Cannot parse id")

        return response.build()
    }
}

data class CreateAuthorRequest( @field:NotBlank var name: String)
