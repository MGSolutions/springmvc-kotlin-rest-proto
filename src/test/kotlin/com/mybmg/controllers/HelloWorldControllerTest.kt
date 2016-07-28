package com.mybmg.controllers

import com.mybmg.MyBmgRestInterfacesApplication
import com.mybmg.services.DemoService
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(MyBmgRestInterfacesApplication::class))
@WebAppConfiguration
class HelloWorldControllerTest {

    @get:Rule
    val restDocumentation = JUnitRestDocumentation("target/generated-snippets")

    @Autowired
    private lateinit var applicationContext: WebApplicationContext

    @MockBean
    private lateinit var demoService: DemoService

    @Autowired
    private lateinit var helloWorldController: HelloWorldController

    private lateinit var mockMvc: MockMvc

    @Before
    fun before() {
        this.mockMvc = documentingMockMvc()
    }

    private fun documentingMockMvc(): MockMvc {
        val documentationConfiguration: MockMvcRestDocumentationConfigurer = documentationConfiguration(this.restDocumentation)
        val webAppContextSetup: DefaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.applicationContext)
        val webAppContextWithDocs: DefaultMockMvcBuilder = webAppContextSetup.apply(documentationConfiguration)
        return webAppContextWithDocs.build()!!
    }

    private fun standardMockMvc() = MockMvcBuilders.standaloneSetup(helloWorldController).build()

    @Test
    @Throws(Exception::class)
    fun startAll() {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk)
                .andExpect(content().string(equalTo("""{"name":"John","age":35}""")))
                .andDo(document("hello",
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("name").description("The name of the Person"),
                                PayloadDocumentation.fieldWithPath("age").description("The age of the Person")
                        )
                ))

//        verify(demoService).doIt()
    }
}
