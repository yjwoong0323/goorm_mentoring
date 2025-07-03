package com.practice.enums

import com.practice.exception.ErrorCode
import com.practice.exception.UserException
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import java.math.RoundingMode

enum class Operator (val symbol: String) {
  PLUS("+") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.plus(number2)
  },
  MINUS("-") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal {
      if (number1 < number2) { throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.NEGATIVE_RESULT) }
      return number1.minus(number2)
    }
  },
  MULTIPLY("*") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.multiply(number2)
  },
  DIVIDE("/") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal {
      require(number2 != BigDecimal.ZERO) {throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.DIVIDE_BY_ZERO) }
      return number1.divide(number2,0,  RoundingMode.HALF_UP)
    }
  };

  abstract fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal

  companion object {
    fun symbolToEnum(symbol: String): Operator {
      return entries.find { it.symbol == symbol } ?: throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.INPUT_INVALID_OPERATOR)
    }
  }
}