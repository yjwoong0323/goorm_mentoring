package me.james.`interface`

class Penguin(
  species: String, // 주 생성자 파라미터
  val wingCount: Int = 2 // 프로퍼티
) : Animal(species, 2), Swimmable, Flyable {

  override fun move() {
    println("A penguin walks")
  }

  // 프로퍼티처럼 getLegCount (in java, getLegCount())
  override val legCount: Int
    get() = super.legCount + this.wingCount

  override fun act() {
    super<Swimmable>.act()
    super<Flyable>.act()
  }

  override fun dive() {
    println("Go dive")
  }
}