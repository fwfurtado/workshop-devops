package br.com.caelum.devops.infra

import br.com.caelum.devops.api.authors.creation.CreateAuthorRepository
import br.com.caelum.devops.api.books.creation.FindAuthorRepository
import br.com.caelum.devops.domain.Author
import org.springframework.data.repository.Repository

interface AuthorRepository : Repository<Author, Long>, CreateAuthorRepository, FindAuthorRepository {
}