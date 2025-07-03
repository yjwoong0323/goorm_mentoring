package com.practice.exception

interface CodeInterface  {
  val code: Int
  val message: String
}

enum class ErrorCode(
  override val code: Int,
  override val message: String
) : CodeInterface {
  INPUT_INVALID_OPERATOR(-100, "input invalid operator"),
  INPUT_INVALID_NUMBER(-101, "input invalid number"),
  DIVIDE_BY_ZERO(-102, "divide by zero"),
  NEGATIVE_RESULT(-103, "negative result")
}