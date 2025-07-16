package com.mentoring.exchangeRate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ExchangeRateApplication

fun main(args: Array<String>) {
	runApplication<ExchangeRateApplication>(*args)
}