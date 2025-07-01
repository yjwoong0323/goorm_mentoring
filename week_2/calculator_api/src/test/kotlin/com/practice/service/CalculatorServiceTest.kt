package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.exception.DivideByZeroException
import com.practice.exception.InvalidNumberException
import com.practice.exception.InvalidOperatorException
import com.practice.exception.NegativeResultException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.math.BigDecimal

class CalculatorServiceTest : BehaviorSpec({

  val calculatorService = CalculatorService()

  Given("두 양수와 유효한 연산자가 주어졌을 때") {
    When("덧셈 연산을 수행하면") {
     Then("두 숫자의 합을 반환해야 한다") {
       val request = CalculatorRequest(BigDecimal("20"), BigDecimal("10"), "+")
       calculatorService.calculate(request).result shouldBe BigDecimal("30")
     }
    }
    When("뺄셈 연산을 수행하면") {
      Then("두 숫자의 차를 반환한다") {
        val request = CalculatorRequest(BigDecimal("20"), BigDecimal("10"), "-")
        calculatorService.calculate(request).result shouldBe BigDecimal("10")
      }
    }
    When("곱셈 연산을 수행하면") {
      Then("두 숫자의 곱을 반환한다") {
        val request = CalculatorRequest(BigDecimal("20"), BigDecimal("10"), "*")
        calculatorService.calculate(request).result shouldBe BigDecimal("200")
      }
    }
    When("나눗셈 연산을 수행하면") {
      Then("나누어 떨어지면, 두 숫자의 몫을 반환한다") {
        val request = CalculatorRequest(BigDecimal("20"), BigDecimal("10"), "/")
        calculatorService.calculate(request).result shouldBe BigDecimal("2")
      }
      Then("나누어 떨어지지 않으면, 두 숫자의 몫을 구한 뒤, 반올림 후 정수로 반환한다") {
        val request = CalculatorRequest(BigDecimal("10"), BigDecimal("3"), "/")
        calculatorService.calculate(request).result shouldBe BigDecimal("3")
      }
    }
  }

  Given("사용자가 잘못된 입력값을 넣었을 때") {
    When("0으로 나누면") {
      Then("DivideByZeroException 발생한다") {
        val request = CalculatorRequest(BigDecimal("20"), BigDecimal("0"), "/")
        val exception = shouldThrow<DivideByZeroException> {
          calculatorService.calculate(request)
        }
        exception.message shouldContain "0으로 나눌 수 없습니다"
      }
    }
    When("음수를 전달 받으면") {
      Then("InvalidNumberException 발생한다") {
        val request = CalculatorRequest(BigDecimal("-10"), BigDecimal("20"), "+")
        val exception = shouldThrow<InvalidNumberException> {
          calculatorService.calculate(request)
        }
        exception.message shouldContain "음수"
      }
    }
    When("유효하지 않은 연산자를 받으면") {
      Then("InvalidOperatorException 발생한다") {
        val request = CalculatorRequest(BigDecimal("10"), BigDecimal("20"), "?")
        val exception = shouldThrow<InvalidOperatorException> {
          calculatorService.calculate(request)
        }
        exception.message shouldContain "지원하지 않는 연산자"
      }
    }
    When("덧셈 연산의 결과가 음수면") {
      Then("NegativeResultException 발생한다") {
        val request = CalculatorRequest(BigDecimal("10"), BigDecimal("20"), "-")
        val exception = shouldThrow< NegativeResultException> {
          calculatorService.calculate(request)
        }
        exception.message shouldContain "결과 값이 음수"
      }
    }
  }
})