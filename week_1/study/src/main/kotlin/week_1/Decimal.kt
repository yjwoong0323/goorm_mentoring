package me.james.week_1

import java.math.BigDecimal

fun main() {

  // BigDecimal - 숫자는 무조건 (정수 포함)
  val decimal = BigDecimal("0.1") // 꼭 String 타입으로 작성해야 함
  println(BigDecimal("0.1") + BigDecimal("1.1") == 1.2.toBigDecimal())

  val decimal2 = "1.3".toBigDecimal()
  val decimal3 = 10.toBigDecimal()
  val decimal4 = """
    3.141592
  """.trimIndent().toBigDecimal()
  println(decimal4)

  // Null safety
  var name: String? = null
  name = "name"
  name = null
  println(name?.length)
  println(name?.length ?: 0) // ?: Elvis 연산자 -> 앞의 객체가 null이면 뒤에 명령어 실행

}