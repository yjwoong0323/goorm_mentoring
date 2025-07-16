package com.mentoring.exchangeRate.repository

import com.mentoring.exchangeRate.domain.ExchangeRate
import org.springframework.data.jpa.repository.JpaRepository

interface ExchangeRateRepository : JpaRepository<ExchangeRate, Long> {
}