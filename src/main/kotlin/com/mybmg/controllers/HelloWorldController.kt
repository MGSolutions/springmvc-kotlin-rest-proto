package com.mybmg.controllers

import com.mybmg.entity.Demo
import com.mybmg.services.DemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 *
 */
@RestController
class HelloWorldController {

    @Autowired
    lateinit var demoService: DemoService

    @RequestMapping("/hello", produces = arrayOf("application/json"))
    @ResponseBody
    fun hello(): Demo {
        demoService.doIt()
        return Demo("John", 35)
    }
}
