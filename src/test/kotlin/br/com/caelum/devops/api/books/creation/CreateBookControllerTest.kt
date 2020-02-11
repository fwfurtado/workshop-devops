package br.com.caelum.devops.api.books.creation

import br.com.caelum.devops.domain.Author
import br.com.caelum.devops.domain.Book
import br.com.caelum.devops.infra.AuthorRepository
import br.com.caelum.devops.infra.BookRepository
import com.nhaarman.mockitokotlin2.any
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.then
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@ExtendWith(MockitoExtension::class)
@AutoConfigureJsonTesters
internal class CreateBookControllerTest {

    @Autowired
    private lateinit var jsonCreator: JacksonTester<CreateBookRequest>

    @MockBean
    private lateinit var bookRepository: BookRepository

    @MockBean
    private lateinit var authorRepository: AuthorRepository

    @Captor
    private lateinit var bookCaptor: ArgumentCaptor<Book>

    @Autowired
    private lateinit var service: CreateBookService

    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders.standaloneSetup(BookCreateController(service)).build()
    }

    @Test
    fun shouldCreateANewBookWithoutReleaseDate() {
        val request = CreateBookRequest(title = "Spring", authorId = 1)

        val authorId = 1L
        val author = Author(name = "Fernando")
        author.id = authorId

        given(authorRepository.findById(authorId)).willReturn(author)

        given(bookRepository.save(any())).will {
            val book = it.arguments[0] as Book

            book.id = 1L

            return@will book
        }

        mvc.perform(post("/books")
                    .content(jsonCreator.write(request).json)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.LOCATION, Matchers.matchesRegex("http://.*?/books/\\d+$")))

        then(authorRepository).should().findById(authorId)
        then(bookRepository).should().save(any())
    }
}