package com.mentoring.exchangeRate.exception

import org.springframework.http.HttpStatus

class ExchangeException (
  val status: HttpStatus,
  val errorCode: ErrorCode
) : RuntimeException(errorCode.message)