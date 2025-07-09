package com.practice.repository

import com.practice.dto.CalculatorResponse
import com.practice.enums.Operator
import com.practice.exception.ErrorCode
import com.practice.exception.UserException
import org.apache.catalina.mapper.Mapper
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
class CalculatorRepository(
  private val jdbcTemplate: JdbcTemplate
) {
  private val calculationRowMapper = RowMapper { rs, _ ->
    CalculatorResponse(
      userId = rs.getInt("userid"),
      number1 = rs.getBigDecimal("number1"),
      number2 = rs.getBigDecimal("number2"),
      operator = rs.getString("operator"),
      result = rs.getBigDecimal("result"),
      operatedAt = rs.getTimestamp("operatedAt").toLocalDateTime()
    )
  }

  fun insertCalculation(
    userId: Int,
    number1: BigDecimal,
    number2: BigDecimal,
    operator: Operator,
    result: BigDecimal,
    operatedAt: LocalDateTime
  ) {
    val sql = """
      INSERT INTO calculation(userid, number1, number2, operator, result, operatedAt)
      VALUES (?, ?, ?, ?, ?, ?)
    """.trimIndent()
    jdbcTemplate.update(sql,
      userId, number1, number2, operator.symbol, result, operatedAt)
  }

  fun selectAll(): List<CalculatorResponse> {
    val sql = """
      SELECT userid, number1, number2, operator, result, operatedAt
      FROM calculation
    """.trimIndent()

    return jdbcTemplate.query(sql, calculationRowMapper)
  }

  fun selectCalsByUserIdAndDate(userId: Int, date: LocalDate): List<CalculatorResponse> {
    val sql = """
      SELECT id, userid, number1, number2, operator, result, operatedAt
      FROM calculation
      WHERE userid = ? AND DATE(operatedAt) = ?
    """.trimIndent()

    return jdbcTemplate.query(sql, calculationRowMapper, userId, date)
  }
}