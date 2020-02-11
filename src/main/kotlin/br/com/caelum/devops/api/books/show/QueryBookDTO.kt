package br.com.caelum.devops.api.books.show

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

data class AuthorResponse(val name: String)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BookResponse(val title: String, val author: AuthorResponse, val releaseDate: LocalDate? = null)