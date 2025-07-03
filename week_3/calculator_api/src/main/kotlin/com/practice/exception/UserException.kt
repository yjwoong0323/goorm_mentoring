package com.practice.exception

import org.springframework.http.HttpStatus

class UserException constructor(
  val status: HttpStatus,
  val errorCode: ErrorCode
) : RuntimeException(errorCode.message)