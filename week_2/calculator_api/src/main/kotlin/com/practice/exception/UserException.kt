package com.practice.exception

import org.springframework.http.HttpStatus

abstract class UserException(
  override val message: String,
  val status: HttpStatus
) : RuntimeException()