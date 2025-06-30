package com.practice.exception

import org.springframework.http.HttpStatus

class DivideByZeroException() : UserException(
  message = "0으로 나눌 수 없습니다",
  status = HttpStatus.BAD_REQUEST
)