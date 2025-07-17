package com.mentoring.exchangeRate.service

import com.mentoring.exchangeRate.domain.ExchangeRate
import com.mentoring.exchangeRate.repository.ExchangeRateRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.javaClass

@Service
class ExchangeRateSaveService (
  private val repository: ExchangeRateRepository
) {
  private val log = LoggerFactory.getLogger(javaClass)

  @Transactional
  fun saveUSD(entity: ExchangeRate) {
    if (!repository.existsByCurrencyCodeAndNoticeDateAndNoticeTime(entity.currencyCode, entity.noticeDate, entity.noticeTime)) {
      val saved = repository.save(entity)
      log.info("saved! USD={} date={} time={}", saved.baseRate, saved.noticeDate, saved.noticeTime)
    } else {
      log.warn("이미 저장된 환율입니다. 저장 생략: date={} time={}", entity.noticeDate, entity.noticeTime)
    }
  }
}