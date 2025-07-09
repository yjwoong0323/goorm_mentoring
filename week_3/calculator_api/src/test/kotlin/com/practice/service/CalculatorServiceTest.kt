package com.practice.service

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.exception.UserException
import com.practice.repository.CalculatorRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

fun dummyCals(): List<CalculatorResponse> {
  return listOf(
    CalculatorResponse(1, BigDecimal("20"), BigDecimal("10"),
      "+", BigDecimal("30"), LocalDateTime.now()),
    CalculatorResponse(1, BigDecimal("20"), BigDecimal("10"),
      "-", BigDecimal("10"), LocalDateTime.now()),
    CalculatorResponse(2, BigDecimal("20"), BigDecimal("10"),
      "/", BigDecimal("2"), LocalDateTime.now()),
    CalculatorResponse(3, BigDecimal("20"), BigDecimal("10"),
      "*", BigDecimal("200"), LocalDateTime.now())
  )
}

class CalculatorServiceTest : BehaviorSpec({

  val repository = mockk<CalculatorRepository>(relaxed = true)
  val calculatorService = CalculatorService(repository)

  Given("createCalculation 메서드 호출"){
    When("정상적인 요청이 들어오면"){
      val request = CalculatorRequest(
        userId = 1,
        number1 = BigDecimal("10"),
        number2 = BigDecimal("20"),
        operatorSymbol = "+"
      )
      Then("계산 결과가 올바르게 저장되어야 한다"){
        // insertCalculation : Unit
        every { repository.insertCalculation(
          any(),any(),any(),any(),any(),any()
        )} just Runs

        val response = calculatorService.createCalculation(request)

        response.userId shouldBe 1
        response.result shouldBe BigDecimal("30")
      }
    }
    When("음수값이 변수로 들어오면"){
      val request = CalculatorRequest(
        userId = 1,
        number1 = BigDecimal("-10"),
        number2 = BigDecimal("20"),
        operatorSymbol = "+"
      )
      Then("UserException(INPUT_INVALID_NUMBER)이 발생한다"){
        val exception = shouldThrow<UserException> {
          calculatorService.createCalculation(request)
        }
        exception.errorCode.code shouldBe -101
      }
    }
    When("결과가 음수라면"){
      val request = CalculatorRequest(
        userId = 1,
        number1 = BigDecimal("10"),
        number2 = BigDecimal("20"),
        operatorSymbol = "-"
      )
      Then("UserException(NEGATIVE_RESULT)이 발생한다"){
        val exception = shouldThrow<UserException> {
          calculatorService.createCalculation(request)
        }
        exception.errorCode.code shouldBe -103
      }
    }
    When("0으로 나누면"){
      val request = CalculatorRequest(
        userId = 1,
        number1 = BigDecimal("10"),
        number2 = BigDecimal("0"),
        operatorSymbol = "/"
      )
      Then("UserException(DIVIDE_BY_ZERO)이 발생한다"){
        val exception = shouldThrow<UserException> {
          calculatorService.createCalculation(request)
        }
        exception.errorCode.code shouldBe -102
      }
    }
    When("유효하지 않은 연산자를 받으면"){
      val request = CalculatorRequest(
        userId = 1,
        number1 = BigDecimal("10"),
        number2 = BigDecimal("20"),
        operatorSymbol = "%"
      )
      Then("UserException(INPUT_INVALID_OPERATOR)이 발생한다"){
        val exception = shouldThrow<UserException> {
          calculatorService.createCalculation(request)
        }
        exception.errorCode.code shouldBe -100
      }
    }
  }

  Given("findAllCalculations 호출"){
    When("정상적인 요청이 들어오면"){
      every { repository.selectAll() } returns dummyCals()

      Then("계산 결과들이 올바르게 반환되어야 한다"){
        val response = calculatorService.findAllCalculations()
        response.size shouldBe 4
        response[0].userId shouldBe 1
        response[1].result shouldBe BigDecimal("10")
      }
    }
    When("DB에 아무 기록이 없으면"){
      every { repository.selectAll() } returns emptyList()

      Then("빈 리스트가 반환되어야 한다"){
        val response = calculatorService.findAllCalculations()
        response shouldBe emptyList()
      }
    }
  }

  Given("findCalsByUserIdAndDate 호출"){
    val userId = 1
    val date = LocalDate.now()

    When("해당 조건과 맞는 계산 기록이 DB에 있다면"){
      val filtered = dummyCals().filter {
        it.userId == userId && it.operatedAt.toLocalDate() == date}

      every { repository.selectCalsByUserIdAndDate(userId, date) } returns filtered

      Then("해당 계산 결과가 반환되어야 한다"){
        val response = calculatorService.findCalsByUserIdAndDate(userId, date)

        response.size shouldBe filtered.size
        response.all { it.userId == userId } shouldBe true
        response.all { it.operatedAt.toLocalDate() == date } shouldBe true
      }
    }
    When("해당 조건과 맞는 계산 기록이 DB에 없다면"){
      every { repository.selectCalsByUserIdAndDate(userId, date) } returns emptyList()

      Then("UserException(CALS_NOT_FOUND)이 발생한다"){
        val exception = shouldThrow<UserException> {
          calculatorService.findCalsByUserIdAndDate(userId, date)
        }
        exception.errorCode.code shouldBe -105
      }
    }
  }
})