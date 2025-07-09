package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.enums.Operator
import com.practice.exception.ErrorCode
import com.practice.exception.UserException
import com.practice.repository.CalculatorRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CalculatorService(
  val calculatorRepository: CalculatorRepository,
) {
  fun createCalculation(request: CalculatorRequest): CalculatorResponse = with(request) {
    val number1 = validateNoNegativeNumber(this.number1)
    val number2 = validateNoNegativeNumber(this.number2)
    val operator = Operator.symbolToEnum(request.operatorSymbol)
    val result = operator.apply(number1, number2)

    val operatedAt = LocalDateTime.now()

    calculatorRepository.insertCalculation(userId, number1, number2, operator, result, operatedAt)

    CalculatorResponse(userId, number1, number2,
      operator.symbol, result, operatedAt)
  }

  fun findAllCalculations(): List<CalculatorResponse> {
    return calculatorRepository.selectAll()
  }

  fun findCalsByUserIdAndDate(userId: Int, date: LocalDate): List<CalculatorResponse> {
    return calculatorRepository.selectCalsByUserIdAndDate(userId, date)
      .takeIf { it.isNotEmpty() }
      ?: throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.CALS_NOT_FOUND)
  }

  private fun validateNoNegativeNumber(number: BigDecimal): BigDecimal {
    return number.takeIf { it >= BigDecimal.ZERO }
      ?: throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.INPUT_INVALID_NUMBER)
  }
}