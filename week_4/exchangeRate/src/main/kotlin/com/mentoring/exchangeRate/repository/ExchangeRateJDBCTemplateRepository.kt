package com.mentoring.exchangeRate.repository

import com.mentoring.exchangeRate.dto.DailyExchangeRateStats
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class ExchangeRateJDBCTemplateRepository (
  val jdbcTemplate: JdbcTemplate
) {
  fun getDailyExchangeStats(currencyCode: String, targetDate: LocalDate): DailyExchangeRateStats? {
    val sql = """
      SELECT
        MAX(base_rate) AS max_rate,
        MIN(base_rate) AS min_rate,
        ROUND(AVG(base_rate), 2) AS avg_rate
      FROM exchange_rate
      WHERE currency_code = ? AND notice_date = ?
    """.trimIndent()

    return jdbcTemplate.queryForObject(sql, { rs, _ ->
        DailyExchangeRateStats(
          rs.getBigDecimal("max_rate"),
          rs.getBigDecimal("min_rate"),
          rs.getBigDecimal("avg_rate")
        )
    }, currencyCode, targetDate)
  }
}