package com.practice.week_1.service

import com.practice.week_1.dto.CalculatorRequest
import com.practice.week_1.dto.CalculatorResponse
import org.springframework.stereotype.Service

@Service
class CalculatorService {

  fun calculate(request: CalculatorRequest): CalculatorResponse {
    val result = when(request.operator) {
      "+" -> request.number1 + request.number2
      "-" -> request.number1 - request.number2
      "*" -> request.number1 * request.number2
      "/" -> if (request.number2.toInt() == 0) {
        throw IllegalArgumentException("0으로 나눌 수 없습니다.")
      } else request.number1 / request.number2
      else -> throw IllegalArgumentException("알맞은 연산자를 넣어주세요")
    }
    return CalculatorResponse(result)
  }
}