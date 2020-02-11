package br.com.caelum.devops.api.authors.creation

import br.com.caelum.devops.domain.Author

interface CreateAuthorRepository {
    fun save(author: Author)
}
