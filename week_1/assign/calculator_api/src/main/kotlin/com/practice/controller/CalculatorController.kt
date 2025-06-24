package com.practice.controller

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.service.CalculatorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/calculator")
class CalculatorController(
  private val calculatorService: CalculatorService
) {

  @PostMapping
  fun calculate(@RequestBody request: CalculatorRequest): ResponseEntity<CalculatorResponse> {
    return ResponseEntity.ok(calculatorService.calculate(request))
  }
}