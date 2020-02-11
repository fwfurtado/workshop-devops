package br.com.caelum.devops.api.authors.creation

import br.com.caelum.devops.domain.Author
import org.hamcrest.Matchers.matchesRegex
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson
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
@AutoConfigureJsonTesters
internal class CreateAuthorControllerTest() {

    @MockBean
    lateinit var service: CreateAuthorService

    @Autowired
    private lateinit var jsonCreator: JacksonTester<CreateAuthorRequest>

    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders.standaloneSetup(CreateAuthorController(service)).build()
    }

    @Test
    fun shouldCreateANewAuthor() {

        val request = CreateAuthorRequest(name = "Fernando")
        given(service.createBy(request)).willReturn(1L)

        val json = jsonCreator.write(request).json

        mvc.perform(post("/authors")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.LOCATION, matchesRegex("http://.*?/authors/\\d+$")))

        then(service).should().createBy(request)
    }

    @Test
    fun shouldNotCreateAuthorWithEmptyName() {

        val request = CreateAuthorRequest(name = "")

        val json = jsonCreator.write(request).json

        mvc.perform(post("/authors")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
    }
}