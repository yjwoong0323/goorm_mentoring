package com.practice.service

import com.practice.dto.CalculatorRequest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class CalculatorServiceTest : BehaviorSpec({
  val calculatorService = CalculatorService()

  Given("두 숫자와 연산자가 주어졌을 때") {
    When("덧셈 연산을 수행하면") {
     Then("두 숫자의 합을 반환한다") {
       val request = CalculatorRequest(BigDecimal(10), BigDecimal(5), "+")
       calculatorService.calculate(request).result shouldBe BigDecimal(15)
     }
    }
    When("뺄셈 연산을 수행하면") {
      Then("두 숫자의 차를 반환한다") {
        val request = CalculatorRequest(BigDecimal(10), BigDecimal(5), "-")
        calculatorService.calculate(request).result shouldBe BigDecimal(5)
      }
    }
    When("곱셈 연산을 수행하면") {
      Then("두 숫자의 곱을 반환한다") {
        val request = CalculatorRequest(BigDecimal(10), BigDecimal(5), "*")
        calculatorService.calculate(request).result shouldBe BigDecimal(50)
      }
    }
    When("나눗셈 연산을 수행하면") {
      Then("두 숫자의 몫을 반환한다") {
        val request = CalculatorRequest(BigDecimal(10), BigDecimal(5), "/")
        calculatorService.calculate(request).result shouldBe BigDecimal(2)
      }
    }
  }
})