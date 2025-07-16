package com.mentoring.exchangeRate.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class ShinhanResponse (
  @JsonProperty("dataBody")
  val dataBody: DataBody?
) {
  data class DataBody(
    @JsonProperty("고시일자") val noticeDate: String,
    @JsonProperty("고시시간") val noticeTime: String,
    @JsonProperty("R_RIBF3733_1") val rates: List<Rate>
  )
  data class Rate(
    @JsonProperty("통화CODE") val currencyCode: String,
    @JsonProperty("매매기준환율") val baseRate: BigDecimal
  )
}