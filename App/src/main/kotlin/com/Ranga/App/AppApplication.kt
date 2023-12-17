package com.Ranga.App

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.Ranga.App.*", "org.example.common")
class AppApplication

fun main(args: Array<String>) {
	runApplication<AppApplication>(*args)
}
