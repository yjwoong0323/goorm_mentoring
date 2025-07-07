package com.practice.dto

import java.math.BigDecimal

data class CalculatorRequest(
  val userId: Int,
  val number1: BigDecimal,
  val number2: BigDecimal,
  val operatorSymbol: String,
)