package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.enums.Operator
import com.practice.exception.InvalidNumberException
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CalculatorService {

  fun calculate(request: CalculatorRequest): CalculatorResponse {

    val (number1, number2, operatorSymbol) = request
    require(number1 >= BigDecimal.ZERO) { throw InvalidNumberException(number1) }
    require(number2 >= BigDecimal.ZERO) { throw InvalidNumberException(number2) }

    val operator = Operator.symbolToEnum(operatorSymbol)
    val result = operator.apply(number1, number2)

    return CalculatorResponse(result)
  }
}