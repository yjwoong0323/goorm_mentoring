package me.james.scope

fun main() {
  val person: Person = Person("이재웅", 27)
}

fun printPerson(person: Person?) {
//  if (person != null) {
//    println(person.name)
//    println(person.age)
//  }
  person?.let {
    println(it.name)
    println(it.age)
  }
  val value1 = person.let { it?.age }
  val value2 = person.run { this?.age }
  val value3 = person.also { it?.age }
  val value4 = person.apply { this?.age }
}

class Person(
  val name: String,
  val age: Int,
) {
  lateinit var hobby: String
}