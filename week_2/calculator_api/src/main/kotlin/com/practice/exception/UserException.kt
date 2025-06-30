package com.practice.exception

import org.springframework.http.HttpStatus

abstract class UserException protected constructor(
  override val message: String,
  val status: HttpStatus
) : RuntimeException(message)