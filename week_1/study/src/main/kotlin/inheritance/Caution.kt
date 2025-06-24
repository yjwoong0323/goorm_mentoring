package me.james.inheritance

fun main() {
  Base(200)
  println()
  Derived(300)
}

open class Base(
  open val number: Int = 100
) {
  init {
    println("Base Class")
    println(number)
    // 상위 클래스에 cons, init 블락에서 하위 클래스의 field에 접근하면 안 됨
    // 상위 클래스 설계 시, cons, init 블락에 사용되는 프로퍼티에는 open을 피해야 함
  }
}

class Derived(
  override val number: Int
) : Base(number) {
  init {
    println("Derived Class")
  }
}