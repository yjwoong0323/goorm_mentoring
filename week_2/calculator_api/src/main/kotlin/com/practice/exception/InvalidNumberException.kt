package com.practice.exception

import org.springframework.http.HttpStatus
import java.math.BigDecimal

class InvalidNumberException(number: BigDecimal) : UserException(
  message = "피연산자로 음수가 올 수 없습니다 : ${number}",
  status = HttpStatus.BAD_REQUEST
)