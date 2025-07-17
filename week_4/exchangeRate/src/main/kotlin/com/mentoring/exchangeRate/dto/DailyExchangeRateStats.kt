package com.mentoring.exchangeRate.dto

import java.math.BigDecimal

data class DailyExchangeRateStats (
  val maxRate: BigDecimal,
  val minRate: BigDecimal,
  val avgRate: BigDecimal
)