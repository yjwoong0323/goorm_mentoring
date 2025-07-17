package com.mentoring.exchangeRate.service

import com.mentoring.exchangeRate.domain.ExchangeRate
import com.mentoring.exchangeRate.dto.ShinhanResponse
import com.mentoring.exchangeRate.repository.ExchangeRateJDBCTemplateRepository
import com.mentoring.exchangeRate.repository.ExchangeRateRepository
import jakarta.annotation.PostConstruct
import jakarta.transaction.Transactional
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
  private val jdbcRepository: ExchangeRateJDBCTemplateRepository,
  builder: WebClient.Builder,
  private val exchangeRateSaveService: ExchangeRateSaveService
) {
  private val log = LoggerFactory.getLogger(javaClass)
  private val client: WebClient = builder
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .build()

  @PostConstruct
  fun runOnceAtStartup() {
    getAndSaveUSD()
    logDailyUSDStats()
  }

  @Scheduled(initialDelay = 10 * 60 * 1000, fixedDelay = 10 * 60 * 1000)
  fun getAndSaveUSD() {
    val entity = client.post()
      .uri("https://bank.shinhan.com/serviceEndpoint/httpDigital")
      .bodyValue(requestPayload())
      .retrieve()
      .bodyToMono(ShinhanResponse::class.java)
      .mapNotNull { response ->
        response.dataBody?.rates
          ?.firstOrNull { it.currencyCode == "USD" }
          ?.let { usd ->
            ExchangeRate(
              currencyCode = "USD",
              baseRate = usd.baseRate.setScale(2),
              noticeDate = LocalDate.parse(response.dataBody.noticeDate, DateTimeFormatter.BASIC_ISO_DATE),
              noticeTime = LocalTime.parse(response.dataBody.noticeTime, DateTimeFormatter.ofPattern("HHmmss"))
            )
          }
      }
      .block() // 동기 처리

      entity?.let { exchangeRateSaveService.saveUSD(it) }
        ?: log.warn("USD 환율 데이터 찾을 수 없음")
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

  @Scheduled(initialDelay = 10 * 60 * 1000, fixedDelay = 10 * 60 * 1000)
  fun logDailyUSDStats() {
    val today = LocalDate.now()
    val stats = jdbcRepository.getDailyExchangeStats("USD", today)

    if (stats != null) {
      log.info("USD 통계 - Date: {} | Avg: {} | Max: {} | Min: {}",
        today, stats.avgRate, stats.maxRate, stats.minRate)
    } else {
      log.warn("USD 환율 통계 없음 - Date: {}", today)
    }
  }
}