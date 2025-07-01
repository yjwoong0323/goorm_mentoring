package com.practice.enums

import com.practice.exception.DivideByZeroException
import com.practice.exception.InvalidOperatorException
import com.practice.exception.NegativeResultException
import java.math.BigDecimal
import java.math.RoundingMode

enum class Operator (val symbol: String) {
  PLUS("+") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.plus(number2)
  },
  MINUS("-") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal {
      if (number1 < number2) throw NegativeResultException(number1.minus(number2))
      return number1.minus(number2)
    }
  },
  MULTIPLY("*") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal = number1.multiply(number2)
  },
  DIVIDE("/") {
    override fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal {
      require(number2 != BigDecimal.ZERO) {throw DivideByZeroException() }
      return number1.divide(number2,0,  RoundingMode.HALF_UP)
    }
  };

//  interface Applyable{
//    fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal
//  }
  abstract fun apply(number1: BigDecimal, number2: BigDecimal): BigDecimal

  companion object {
    fun symbolToEnum(symbol: String): Operator {
      return entries.find { it.symbol == symbol } ?: throw InvalidOperatorException(symbol)
    }
  }
}