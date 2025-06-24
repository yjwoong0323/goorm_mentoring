package me.james.`interface`

interface Swimmable {

  val swimAbility: Int // backing field 없는 프로퍼티를 인터페이스 생성
    get() = 1

  fun act() {
    println("swim!")
  }

  fun dive() // 추상 메서드
}