package com.mentoring.exchangeRate.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

@Entity
class ExchangeRate (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(name = "currency_code", nullable = false, length = 3)
  val currencyCode: String,

  @Column(name = "base_rate", nullable = false, precision = 15, scale = 2)
  val baseRate: BigDecimal,

  @Column(name = "notice_date", nullable = false)
  val noticeDate: LocalDate,

  @Column(name = "notice_time", nullable = false)
  val noticeTime: LocalTime
)