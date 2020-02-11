package br.com.caelum.devops.api.authors.creation

import br.com.caelum.devops.domain.Author


fun mapToAuthor(source: CreateAuthorRequest): Author {
    return Author(name=source.name)
}

