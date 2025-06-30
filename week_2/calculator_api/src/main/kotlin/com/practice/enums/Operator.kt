package com.practice.enums

import com.practice.exception.InvalidOperatorException
import java.lang.IllegalArgumentException
import java.math.BigDecimal

enum class Operator (val symbol: String) {
  PLUS("+") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.plus(number2)
  },
  MINUS("-") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.minus(number2)
  },
  MULTIPLY("*") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.multiply(number2)
  },
  DIVIDE("/") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal {
      require(number2 != BigDecimal.ZERO) { "0으로 나눌 수 없음"}
      return number1.divide(number2)
    }
  };

  abstract fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal

  companion object {
    fun fromSymbol(symbol: String): Operator {
      return entries.find { it.symbol == symbol } ?: throw InvalidOperatorException(symbol)
    }
  }
}