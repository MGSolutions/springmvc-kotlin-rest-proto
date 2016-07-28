package com.mybmg;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class JavaTest {

    @BeforeAll
    public static void beforeAll() {
        System.err.println("==================BEFORE ALL");
    }

    @BeforeEach
    public void before() {
        System.err.println("==================BEFORE");
    }


    @AfterEach
    public void after() {
        System.err.println("==================AFTER");
    }


    @AfterAll
    public static void afterAll() {
        System.err.println("==================AFTER ALL");
    }

    @Test
    void aa() {
        Some some = new Some();
        some.print("Java");
//        fail("Blah");
    }
}
