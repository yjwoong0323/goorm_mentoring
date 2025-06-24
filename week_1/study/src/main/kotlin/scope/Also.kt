package me.james.scope

fun main() {
  // 1. 객체를 수정하는 로직이 call chain 중간에 필요할 때

  mutableListOf("one", "two", "three")
    .also { println("four 추가 이전 현재 값: ${it}") }
    .add("four")

  val numbers = mutableListOf("one", "two", "three")
  println("four 추가 이전 현재 값: ${numbers}")
  numbers.add("four")
}