package com.practice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.practice.enums.Operator
import java.math.BigDecimal
import java.time.LocalDateTime

data class CalculatorResponse(
  val userId: Int,
  val number1: BigDecimal,
  val number2: BigDecimal,
  val operator: Operator,
  val result: BigDecimal,

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  val operatedAt: LocalDateTime
)