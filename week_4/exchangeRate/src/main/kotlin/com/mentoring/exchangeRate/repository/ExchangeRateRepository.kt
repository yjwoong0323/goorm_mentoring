package com.mentoring.exchangeRate.repository

import com.mentoring.exchangeRate.domain.ExchangeRate
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.time.LocalTime

interface ExchangeRateRepository : JpaRepository<ExchangeRate, Long> {
  fun existsByCurrencyCodeAndNoticeDateAndNoticeTime(
    currencyCode: String,
    noticeDate: LocalDate,
    noticeTime: LocalTime
  ): Boolean
}