package com.mentoring.exchangeRate.exception

enum class ErrorCode (
  val code: Int,
  val message: String
) {
  DUPLICATE_EXCHANGE_RATE(-200, "exchange rate exists already")
}