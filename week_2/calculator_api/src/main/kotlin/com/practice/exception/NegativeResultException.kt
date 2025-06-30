package com.practice.exception

import org.springframework.http.HttpStatus
import java.math.BigDecimal

class NegativeResultException(result: BigDecimal) : UserException(
  message = "결과 값이 음수입니다 : ${result}",
  status = HttpStatus.BAD_REQUEST
)