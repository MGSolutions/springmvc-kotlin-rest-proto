package com.mybmg.services

import org.springframework.stereotype.Service

/**
 * Notice 'open' !!!
 */
@Service
open class DemoService {

    open fun doIt() {
        println("In a service!!!")
    }
}
