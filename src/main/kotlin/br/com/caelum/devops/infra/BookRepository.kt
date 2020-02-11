package br.com.caelum.devops.infra

import br.com.caelum.devops.api.books.creation.CreateBookRepository
import br.com.caelum.devops.api.books.show.QueryBookRepository
import br.com.caelum.devops.domain.Book
import org.springframework.data.repository.Repository

interface BookRepository : Repository<Book, Long>,  CreateBookRepository, QueryBookRepository {
}