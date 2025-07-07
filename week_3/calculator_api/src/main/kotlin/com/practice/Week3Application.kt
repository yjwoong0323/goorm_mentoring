package com.practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan("com.practice")
@EnableJpaRepositories("com.practice.repository")
@EntityScan("com.practice.domain")
@SpringBootApplication
class Week3Application

fun main(args: Array<String>) {
	runApplication<Week3Application>(*args)
}