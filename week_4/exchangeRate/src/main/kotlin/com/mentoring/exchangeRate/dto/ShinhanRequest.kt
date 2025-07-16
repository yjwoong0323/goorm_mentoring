package com.mentoring.exchangeRate.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ShinhanRequest(
  val dataBody: DataBody,
  val dataHeader: DataHeader
) {
  data class DataBody(
    val ricInptRootInfo: RicInptRootInfo,

    @JsonProperty("조회구분") val queryType: String = "",
    @JsonProperty("조회일자") val noticeDate: String,
    @JsonProperty("고시회차") val round: String = ""
  )
  data class RicInptRootInfo(
    val serviceType: String = "GU",
    val serviceCode: String = "F3733",
    val language: String = "ko",
    val callBack: String = "shbObj.fncF3733Callback"
  )
  data class DataHeader(
    val trxCd: String = "RSRFO0100A01",
    val language: String = "ko",
    val subChannel: String = "49",
    val channelGbn: String = "D0"
  )
}