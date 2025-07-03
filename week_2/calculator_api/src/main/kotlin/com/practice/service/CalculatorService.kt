package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.enums.Operator
import com.practice.exception.ErrorCode
import com.practice.exception.UserException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CalculatorService {

  fun calculate(request: CalculatorRequest): CalculatorResponse {

    val (number1, number2, operatorSymbol) = request
    require(number1 >= BigDecimal.ZERO) { throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.INPUT_INVALID_NUMBER) }
    require(number2 >= BigDecimal.ZERO) { throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.INPUT_INVALID_NUMBER) }

    val operator = Operator.symbolToEnum(operatorSymbol)
    val result = operator.apply(number1, number2)

    return CalculatorResponse(result)
  }
}