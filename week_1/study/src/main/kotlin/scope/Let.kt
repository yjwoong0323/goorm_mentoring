package me.james.scope

fun main() {
  // 1. 하나 이상의 함수를 call chain 결과로 호출 할 때
  val strs = listOf("APPLE", "CAR", "BANANA")
  strs.map { it.length }
    .filter { it > 3 }
    .let { lengths -> println(lengths) }

  // 2. non-null 값에 대해서만 code block 실행시킬 때
  val str = "This is String"
  val length = str.let {
    println(it.uppercase())
    it.length
  }

  // 3. 일회성으로 제한된 영역에 지역 변수를 만들 때
  val numbers = listOf("one", "two", "three", "four")
  val modifiedFirstItem = numbers.first()
    .let { firstItem ->
      if (firstItem.length >= 5) firstItem else "!${firstItem}"
    }.uppercase()
  println(modifiedFirstItem)
}