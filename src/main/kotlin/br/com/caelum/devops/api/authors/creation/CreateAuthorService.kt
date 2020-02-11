package br.com.caelum.devops.api.authors.creation

import org.springframework.stereotype.Service

@Service
class CreateAuthorService(private val repository: CreateAuthorRepository) {
    fun createBy(input: CreateAuthorRequest): Long? {
        val author = mapToAuthor(input)

        repository.save(author)

        return author.id
    }
}
