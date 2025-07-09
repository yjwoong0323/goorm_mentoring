package com.practice.controller

import com.practice.dto.CalculatorRequest
import com.practice.dto.CalculatorResponse
import com.practice.service.CalculatorService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/calculators")
class CalculatorController(
  private val calculatorService: CalculatorService
) {

  @PostMapping
  fun createCalculation(@RequestBody request: CalculatorRequest): ResponseEntity<CalculatorResponse> {
    return ResponseEntity.ok(calculatorService.createCalculation(request))
  }

  @GetMapping
  fun getAllCalculations(): ResponseEntity<List<CalculatorResponse>> {
    return ResponseEntity.ok(calculatorService.findAllCalculations())
  }

  @GetMapping("/{userId}/{date}")
  fun getCalsByUserIdAndDate(
    @PathVariable userId: Int,
    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
  ): ResponseEntity<List<CalculatorResponse>> {
    return ResponseEntity.ok(calculatorService.findCalsByUserIdAndDate(userId, date))
  }
}