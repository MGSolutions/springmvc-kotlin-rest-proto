package com.mybmg

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MyBmgRestInterfacesApplicationTests {

	@Test
	fun contextLoads() {
		val some = Some()
		some.print("Kotlin")
	}

}
