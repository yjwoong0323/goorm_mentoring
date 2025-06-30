package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.enums.Operator
import org.springframework.stereotype.Service

@Service
class CalculatorService {

  fun calculate(request: CalculatorRequest): CalculatorResponse {

    val (number1, number2, operatorSymbol) = request
    val operator = Operator.symbolToEnum(operatorSymbol)
    val result = operator.apply(number1, number2)

    return CalculatorResponse(result)
  }
}