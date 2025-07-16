package com.mentoring.exchangeRate.service

import com.mentoring.exchangeRate.domain.ExchangeRate
import com.mentoring.exchangeRate.dto.ShinhanResponse
import com.mentoring.exchangeRate.repository.ExchangeRateRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class ExchangeRateService(
  private val repository: ExchangeRateRepository,
  builder: WebClient.Builder
) {
  private val log = LoggerFactory.getLogger(javaClass)
  private val client: WebClient = builder
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .build()

  @Scheduled(fixedRate = 10 * 60 * 1000)
  fun getAndSaveUSD() {
    client.post()
      .uri("https://bank.shinhan.com/serviceEndpoint/httpDigital")
      .bodyValue(requestPayload())
      .retrieve()
      .bodyToMono(ShinhanResponse::class.java)
      .mapNotNull { response ->
        response.dataBody.rates
          .firstOrNull { it.currencyCode == "USD" }
          ?.let { usd ->
            ExchangeRate(
              currencyCode = "USD",
              baseRate = usd.baseRate.setScale(6),
              noticeDate = LocalDate.parse(response.dataBody.noticeDate, DateTimeFormatter.BASIC_ISO_DATE),
              noticeTime = LocalTime.parse(response.dataBody.noticeTime, DateTimeFormatter.ofPattern("HHmmss"))
            )
          }
      }
      .doOnNext { entity ->
        val saved = repository.save(entity)
        log.info(
          "saved! USD={} date={} time={}", saved.baseRate, saved.noticeDate, saved.noticeTime
        )
      }
      .block()
  }

  fun requestPayload(): Map<String, Any> = mapOf(
    "dataBody" to mapOf(
      "ricInptRootInfo" to mapOf(
        "serviceType" to "GU",
        "serviceCode" to "F3733",
        "language"    to "ko",
        "callBack"    to "shbObj.fncF3733Callback",
        "isRule"      to "N",
        "webUri"      to "/index.jsp"
      ),
      "조회구분" to "",
      "조회일자" to LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
      "고시회차" to ""
    ),
    "dataHeader" to mapOf(
      "trxCd"      to "RSRFO0100A01",
      "language"   to "ko",
      "subChannel" to "49",
      "channelGbn" to "D0"
    )
  )
}