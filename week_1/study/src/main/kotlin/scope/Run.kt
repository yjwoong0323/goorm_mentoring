package me.james.scope

fun main() {
  // 1. 객체 초기화와 반환 값의 계산을 동시에 해야 할 때
  // -> 객체를 만들어 DB에 바로 저장하고, 그 인스턴스를 활용 할 때
  //val person = Person("이재웅", 27).run { personRepository::save }
  // 위 코드 보다는
  // val person = personRepository.save(Person(name, age)) 선호
  // 반복되는 생성 후처리는 생성자, 프로퍼티, init block으로 넣는 것 추천
}