package com.practice.week_1.dto

import java.math.BigDecimal

data class CalculatorRequest(
  val number1: BigDecimal,
  val number2: BigDecimal,
  val operator: String,
)