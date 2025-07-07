package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.enums.Operator
import com.practice.repository.CalculatorRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CalculatorService(
  val calculatorRepository: CalculatorRepository
) {
  fun calculate(request: CalculatorRequest): CalculatorResponse {
    val userid = request.userId
    val number1 = request.number1
    val number2 = request.number2
    val operator = Operator.symbolToEnum(request.operatorSymbol)
    val result = operator.apply(number1, number2)

    calculatorRepository.insertCalculation(
      userid, number1, number2, operator, result
    )
    return CalculatorResponse(
      userId = userid,
      number1 = number1,
      number2 = number2,
      operator = operator,
      result = result,
      operatedAt = LocalDateTime.now()
    )
  }

  fun findByUserIdAndDate(userId: Int, date: LocalDate): List<CalculatorResponse> {
    return calculatorRepository.findByUserIdAndDate(userId, date)
  }
}