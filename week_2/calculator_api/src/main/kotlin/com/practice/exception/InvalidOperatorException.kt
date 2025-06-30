package com.practice.exception

import org.springframework.http.HttpStatus

class InvalidOperatorException(symbol: String) : UserException(
  message = "지원하지 않는 연산자입니다 : ${symbol}",
  status = HttpStatus.BAD_REQUEST
)