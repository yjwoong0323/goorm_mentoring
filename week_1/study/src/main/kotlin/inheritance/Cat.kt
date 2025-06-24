package me.james.`interface`

class Cat(
  species: String // 주 생성자 파라미터
) : Animal(species, 4) { // 상위 클래스의 생성자 호출

  override fun move() {
    println("A cat walks")
  }
}