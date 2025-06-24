package me.james.scope

fun main() {
  // 1. 객체 설정 시, 객체를 수정하는 로직이 call chain 중간에 필요할 때
}

fun createPerson(
  name: String,
  age: Int,
  hobby: String
): Person {
  return Person(
    name = name,
    age = age,
  ).apply {
    this.hobby = hobby // Test Fixture 만들 때
  }
}