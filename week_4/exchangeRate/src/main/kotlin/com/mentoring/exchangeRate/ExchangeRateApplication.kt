package com.mentoring.exchangeRate

import com.mentoring.exchangeRate.service.ExchangeRateService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
@EnableScheduling
class ExchangeRateApplication(
	private val service: ExchangeRateService
): CommandLineRunner {
	override fun run(vararg args: String?) {
		service.getAndSaveUSD()
	}
}

fun main(args: Array<String>) {
	runApplication<ExchangeRateApplication>(*args)
}