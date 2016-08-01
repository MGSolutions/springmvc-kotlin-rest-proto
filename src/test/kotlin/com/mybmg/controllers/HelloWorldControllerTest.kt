package com.mybmg.controllers

import com.mybmg.services.DemoService
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@WebMvcTest(HelloWorldController::class, secure = false)
@WebAppConfiguration
@AutoConfigureRestDocs("target/generated-snippets")
class HelloWorldControllerTest {

    @MockBean
    private lateinit var demoService: DemoService

    @Autowired
    private lateinit var mockMvc: MockMvc

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

        Mockito.verify(demoService).doIt()
    }
}
